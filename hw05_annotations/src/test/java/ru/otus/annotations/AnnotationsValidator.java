package ru.otus.annotations;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AnnotationsValidator {

    public AnnotationsValidator(String s) {
        System.out.println("Constructor: " + s);
    }

    public void Process() throws IllegalAccessException, InvocationTargetException {
        int failed = 0, count = 0;
        Class<?> clazz = HumanClassTest.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        AnnotatedMethods annotatedMethods = new AnnotatedMethods();
        annotatedMethods.fillFromMethodsArray(declaredMethods);
        try {
            annotatedMethods.getBeforAllMethod().invoke(null);
            for (Method method : annotatedMethods.getTestMethods()) {
                try {
                    Object newinstance = clazz.getDeclaredConstructor().newInstance();
                    invokeMethodIfNotNull(annotatedMethods.getBeforeMethod(), newinstance);
                    invokeMethodIfNotNull(method, newinstance);
                    System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                    invokeMethodIfNotNull(annotatedMethods.getAfterMethod(), newinstance);
                } catch (Throwable ex) {
                    System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                    failed++;
                } finally {
                    method.setAccessible(false);
                }
            }
        } catch (Throwable ex) {
            System.out.println("Tests won't be run without beforeAll method!");
        } finally {
            System.out.printf("%nResult : Total : %d, Failed: %d, Passed %d %n", count, failed, (count - failed));
            invokeMethodIfNotNull(annotatedMethods.getAfterAllMethod(), null);
        }
    }
        private void invokeMethodIfNotNull (Method method, Object newinstance) throws
        InvocationTargetException, IllegalAccessException {
            if (method != null) {
                method.invoke(newinstance);
            } else {
                System.out.println("No method defined!");
            }
        }
    }