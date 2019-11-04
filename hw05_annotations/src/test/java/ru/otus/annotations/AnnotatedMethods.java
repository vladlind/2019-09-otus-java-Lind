package ru.otus.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotatedMethods {
    Method beforeMethod = null, afterMethod = null, afterAllMethod = null, beforAllMethod = null;
    List<Method> testMethods = new ArrayList<Method>();
    protected void getAnnotatedMethods (Method[] declaredMethods) {

        for (Method method : declaredMethods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(Before.class)) {
                this.beforeMethod = method;
            } else if (method.isAnnotationPresent(BeforeAll.class)) {
                this.beforAllMethod = method;
            } else if (method.isAnnotationPresent(After.class)) {
                this.afterMethod = method;
            } else if (method.isAnnotationPresent(AfterAll.class)) {
                afterAllMethod = method;
            } else if (method.isAnnotationPresent(Test.class)) {
                this.testMethods.add(method);
            }
        }
    }
}
