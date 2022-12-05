package com.putoet.day5;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CrateStack {
    private final Stack<Crate> stack = new Stack<>();

    public CrateStack() {
        this(Collections.emptyList());
    }

    public CrateStack(List<Crate> stack) {
        stack.forEach(this.stack::push);
    }

    public CrateStack(String stack) {
        stack.chars().forEach(c -> this.stack.push(new Crate((char) c)));
    }

    public CrateStack take(int n) {
        final Stack<Crate> taken = new Stack<>();
        while (n-- > 0) {
            taken.push(stack.pop());
        }
        return new CrateStack(taken);
    }

    public void add(CrateStack taken) {
        taken.stack.forEach(stack::push);
    }

    public int size() {
        return stack.size();
    }

    public String top() {
        return stack.isEmpty() ? " " : String.valueOf(stack.peek().letter());
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        stack.forEach(c -> sb.append(c.letter()));
        return sb.toString();
    }
}
