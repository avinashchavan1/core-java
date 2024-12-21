package com.avinash.employees;

public interface IChef {
    default void cooking() {
        System.out.println("Chef cooking");
    }

    default void sleep() {
        System.out.println("Chef sleeping");
    }
}
