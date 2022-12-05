package com.putoet.day5;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Crates {
    private final Stack<Crate> stack = new Stack<>();

    public Crates() {
        this(Collections.emptyList());
    }

    public Crates(List<Crate> stack) {
        stack.forEach(this.stack::push);
    }

    public Crates(String stack) {
        stack.chars().forEach(c -> this.stack.push(new Crate((char) c)));
    }

    public Crates take(int n) {
        final Stack<Crate> taken = new Stack<>();
        while (n-- > 0) {
            taken.push(stack.pop());
        }
        return new Crates(taken);
    }

    public void add(Crates taken) {
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
        final StringBuilder sb = new StringBuilder();
        stack.forEach(c -> sb.append(c.id()));
        return sb.toString();
    }
}
