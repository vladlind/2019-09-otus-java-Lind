package ru.otus.hw15_MS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
    }
}
