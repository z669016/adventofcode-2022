package com.putoet.day20;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

class Value implements Supplier<Long> {
    private final int id;
    private final long value;
    private Value next;
    private Value prev;

    public Value(int id, long value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public Long get() {
        return value;
    }

    public Value prev() {
        return prev;
    }

    public void prev(@NotNull Value value) {
        this.prev = value;
    }

    public Value next() {
        return next;
    }

    public void next(@NotNull Value value) {
        this.next = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value v)) return false;
        return id == v.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "(" + id + ")" + value;
    }
}
