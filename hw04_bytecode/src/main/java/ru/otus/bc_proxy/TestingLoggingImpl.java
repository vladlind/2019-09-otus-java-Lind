package ru.otus.bc_proxy;


public class TestingLoggingImpl implements TestingLoggingInterface {

    @Log
    public void calculation(int param) {
    }
    @Log
    public void newcalculation(int param) {
        System.out.println("New calculation: "+param);
    }
}
