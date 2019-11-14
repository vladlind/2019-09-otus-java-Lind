package ru.otus.bc_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;

class IoC {

    static TestingLoggingInterface createMyClass() {

        InvocationHandler handler = new DemoInvocationHandler(new TestingLoggingImpl());
        return (TestingLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{TestingLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private Object myClass;

        DemoInvocationHandler(Object myClass) {
            this.myClass = myClass;
        }

        HashMap<Object, Boolean> annotatedMethodMap = new HashMap<>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!annotatedMethodMap.containsKey(method)) {
                if (myClass.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Log.class)) {
                    System.out.println("executed method: " + method.getName() + ", " + "param: " + args[0]);
                    annotatedMethodMap.put(method, true);
                } else {
                    annotatedMethodMap.put(method, false);
                }
            } else {
                if (annotatedMethodMap.get(method)) {
                    System.out.println("executed method (cached): " + method.getName() + ", " + "param: " + args[0]);
                }

            }
            return method.invoke(myClass, args);
        }
    }
}
