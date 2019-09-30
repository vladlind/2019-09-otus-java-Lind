package ru.otus.hw01_maven;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class HelloOtus {

    private static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "green",
            "blue");

    public static void main(String[] args) {
        Foo foo = new Foo(COLOR_NAMES);
        System.out.println(foo.bars);
    }
}
class Foo<Bar> {

    final ImmutableSet<Bar> bars;

    Foo(Set<Bar> bars) {
        this.bars = ImmutableSet.copyOf(bars); // defensive copy!
    }
}