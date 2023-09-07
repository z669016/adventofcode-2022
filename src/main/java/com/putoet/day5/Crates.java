package com.putoet.day5;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Crates {
    private final Stack<Crate> stack = new Stack<>();

    public Crates() {
        this(Collections.emptyList());
    }

    public Crates(@NotNull List<Crate> stack) {
        stack.forEach(this.stack::push);
    }

    public Crates(@NotNull String stack) {
        stack.chars().forEach(c -> this.stack.push(new Crate((char) c)));
    }

    public Crates take(int n) {
        final var taken = new Stack<Crate>();
        while (n-- > 0) {
            taken.push(stack.pop());
        }
        return new Crates(taken);
    }

    public void add(@NotNull Crates taken) {
        taken.stack.forEach(stack::push);
    }

    public Crates reverse() {
        return new Crates(new StringBuilder(this.toString()).reverse().toString());
    }

    public int size() {
        return stack.size();
    }

    public String top() {
        return stack.isEmpty() ? " " : String.valueOf(stack.peek().id());
    }

    public String toString() {
        final var sb = new StringBuilder();
        stack.forEach(c -> sb.append(c.id()));
        return sb.toString();
    }
}
