package ru.otus.bc_proxy;

import java.time.Duration;
import java.time.Instant;

public class Demo {
    public static void main(String[] args) {

        Instant start = Instant.now();

        TestingLoggingInterface myClass = IoC.createMyClass();
        myClass.calculation(6);
        myClass.newcalculation(7);

        myClass.calculation(8);
        myClass.newcalculation(9);

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
    }
}



