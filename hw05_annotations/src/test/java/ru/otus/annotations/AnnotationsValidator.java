package ru.otus.annotations;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class AnnotationsValidator {

    public AnnotationsValidator(String s) {
        System.out.println("Constructor: " + s);
    }

    public void Process() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        int failed = 0, count = 0;
        Class<?> clazz = HumanClassTest.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        AnnotatedMethods annotatedMethods = new AnnotatedMethods();
        annotatedMethods.getAnnotatedMethods(declaredMethods);
        annotatedMethods.beforAllMethod.invoke(null);
        for (Method method : annotatedMethods.testMethods){
            try {
                Object newinstance = clazz.getDeclaredConstructor().newInstance();
                annotatedMethods.beforeMethod.invoke(newinstance);
                method.invoke(newinstance);
                System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                annotatedMethods.afterMethod.invoke(newinstance);
            } catch (Throwable ex) {
                System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                failed++;
            } finally {
                method.setAccessible(false);
            }
        }
        annotatedMethods.afterAllMethod.invoke(null);
        System.out.printf("%nResult : Total : %d, Failed: %d, Passed %d %n", count, failed, (count-failed));
    }

}