package ru.otus.appcontainer;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        processConfig(initialConfigClass);
    }

    public AppComponentsContainerImpl(Class<?>... initialConfigClass) {
        scanClasses(initialConfigClass);
    }

    public AppComponentsContainerImpl(String packageName) {
        Reflections reflections = new Reflections(packageName, new TypeAnnotationsScanner());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(AppComponentsContainerConfig.class, true);
        scanClasses(classes.toArray(new Class[0]));
    }


    private void processConfig(Class<?> configClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        checkConfigClass(configClass);
        Object newinstance = configClass.getDeclaredConstructor().newInstance();

        List<Method> methods = Arrays.stream(configClass.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(AppComponent.class)).
                sorted(Comparator.comparingInt(m -> m.getAnnotation(AppComponent.class).order())).collect(Collectors.toList());

        List<Object> arguments = new ArrayList<>();
        methods.forEach(method -> {
            Arrays.stream(method.getParameterTypes()).forEach(clazz -> arguments.add(this.getAppComponent(clazz)));
            Object beanInstance = null;
            try {
                beanInstance = method.invoke(newinstance, arguments.toArray());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            appComponents.add(beanInstance);
            appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), beanInstance);
            arguments.clear();
        });
    }


    private void scanClasses(Class<?>... initialConfigClass) {
        List<Class> classes = Arrays.stream(initialConfigClass).
                sorted(Comparator.comparingInt(c -> c.getAnnotation(AppComponentsContainerConfig.class).order())).collect(Collectors.toList());
        classes.forEach(c -> {
            try {
                processConfig(c);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }


    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponents.stream().filter(componentClass::isInstance).
                findFirst().orElse(false);

    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
