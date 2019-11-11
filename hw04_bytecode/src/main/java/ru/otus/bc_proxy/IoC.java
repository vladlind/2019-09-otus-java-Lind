package ru.otus.bc_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


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

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (myClass.getClass().getMethod(method.getName(), method.getParameterTypes()).isAnnotationPresent(Log.class)) {
                System.out.println("executed method: " + method.getName()+", "+ "param: " + args[0]);
            }
            return method.invoke(myClass, args);
        }
    }
}
