package ru.otus.annotations;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AnnotationsValidator {

    public AnnotationsValidator(String s) {
        System.out.println("Constructor: " + s);
    }

    public void Process() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        int passed = 0, failed = 0, count = 0;
        Class<?> clazz = HumanClassTest.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method beforeMethod = null, afterMethod = null, afterAllMethod = null;
        Object newinstance = clazz.getDeclaredConstructor().newInstance();
        for (Method method : declaredMethods) {
            try {
                method.setAccessible(true);
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethod = method;

                } else if (method.isAnnotationPresent(BeforeAll.class)) {
                    method.invoke(newinstance);
                } else if (method.isAnnotationPresent(After.class)) {
                    afterMethod = method;
                } else if (method.isAnnotationPresent(AfterAll.class)) {
                    afterAllMethod = method;
                } else if (method.isAnnotationPresent(Test.class)) {
                    try {
                        beforeMethod.invoke(newinstance);
                        method.invoke(newinstance);
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        afterMethod.invoke(newinstance);
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }
                }
            } finally {
                method.setAccessible(false);
            }
        }
        afterAllMethod.invoke(newinstance);
        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d %n", count, passed, failed);
    }
}