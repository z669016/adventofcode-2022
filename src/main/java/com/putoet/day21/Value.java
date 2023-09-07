package com.putoet.day21;

import java.util.function.Supplier;

class Value implements Supplier<String> {

    private final String value;

    public Value(long value) {
        this.value = String.valueOf(value);
    }

    public Value(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
