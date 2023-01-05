package org.example.lesson8filter.javacore;

public interface Driveable {

    default void drive() {
        System.out.println("prototype is about to move");
    }
}

