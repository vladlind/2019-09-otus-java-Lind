package ru.otus.annotations;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AnnotationsValidator {

    public AnnotationsValidator(String s) {
        System.out.println("Constructor: " + s);
    }

    public void Process() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
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
                    if (annotatedMethods.getBeforeMethod() != null) {
                        annotatedMethods.getBeforeMethod().invoke(newinstance);
                    }
                    method.invoke(newinstance);
                    System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                    if (annotatedMethods.getAfterMethod() != null) {
                        annotatedMethods.getAfterMethod().invoke(newinstance);
                    }
                } catch (Throwable ex) {
                    System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                    failed++;
                } finally {
                    method.setAccessible(false);
                }
            }
        } catch (Throwable ex) {
            System.out.printf("BeforeAll method has thrown an exception - %s", ex.getCause());
        } finally {
            System.out.printf("%nResult : Total : %d, Failed: %d, Passed %d %n", count, failed, (count - failed));
            try {
                annotatedMethods.getAfterAllMethod().invoke(null);
            } catch (Throwable ex) {
                System.out.printf("AfterAll method has thrown an exception - %s", ex.getCause());
            }
        }
    }
}