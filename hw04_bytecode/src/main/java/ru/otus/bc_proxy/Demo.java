package ru.otus.bc_proxy;

public class Demo {
    public static void main(String[] args) {
        TestingLoggingInterface myClass = IoC.createMyClass();
        myClass.calculation(6);
    }
}



