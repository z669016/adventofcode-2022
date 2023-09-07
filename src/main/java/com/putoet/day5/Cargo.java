package com.putoet.day5;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Cargo {
    private final List<Crates> stock;

    public Cargo(List<Crates> stock) {
        this.stock = new ArrayList<>(stock);

    }

    public Crates take(int from, int count, @NotNull Crane crane) {
        assert from > 0 && from <= stock.size();
        assert count > 0 && count <= stock.get(from - 1).size();

        return crane.take(stock.get(from - 1), count);
    }

    public void add(int to, @NotNull Crates stack) {
        assert to > 0 && to <= stock.size();

        stock.get(to - 1).add(stack);
    }

    public String top() {
        return stock.stream().map(Crates::top).collect(Collectors.joining());
    }

    public String toString() {
        return stock.stream()
                .map(Crates::toString)
                .collect(Collectors.joining("\n"));
    }
}
