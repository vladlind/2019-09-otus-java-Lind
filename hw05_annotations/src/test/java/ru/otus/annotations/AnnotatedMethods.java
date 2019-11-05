package ru.otus.annotations;

import lombok.Getter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AnnotatedMethods {

    private Method beforeMethod = null;
    private Method afterMethod = null;
    private Method afterAllMethod = null;
    private Method beforAllMethod = null;
    List<Method> testMethods = new ArrayList<Method>();
    protected void fillFromMethodsArray (Method[] declaredMethods) {

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
