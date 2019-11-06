package ru.otus.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestLauncher {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<AnnotationsValidator> annotationsValidatorClass = AnnotationsValidator.class;
        Constructor<AnnotationsValidator> constructor = annotationsValidatorClass.getDeclaredConstructor(String.class);
        AnnotationsValidator annotationsValidator = constructor.newInstance("New test");
        
        annotationsValidator.Process();
    }
}
