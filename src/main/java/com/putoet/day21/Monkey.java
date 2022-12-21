package com.putoet.day21;

import java.util.function.Supplier;

public class Monkey implements MonkeyOperation {
    private final String name;
    private Supplier<Long> operation;

    public Monkey(String name, Supplier<Long> operation) {
        this.name = name;
        this.operation = operation;
    }

    public String name() {
        return name;
    }

    @Override
    public Long get() {
        return operation.get();
    }
}
