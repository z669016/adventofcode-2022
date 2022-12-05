package com.putoet.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cargo {
    private final List<CrateStack> stock;

    public Cargo(List<CrateStack> stock) {
        this.stock = new ArrayList<>(stock);

    }

    public void move(int from, int to, int count) {
        assert from > 0 && from <= stock.size();
        assert to > 0 && to <= stock.size();
        assert from != to;
        assert count > 0 && count <= stock.get(from - 1).size();

        stock.get(to - 1).add(stock.get(from - 1).take(count));
    }

    public void move2(int from, int to, int count) {
        assert from > 0 && from <= stock.size();
        assert to > 0 && to <= stock.size();
        assert from != to;
        assert count > 0 && count <= stock.get(from - 1).size();

        stock.get(to - 1).add(new CrateStack(new StringBuilder(stock.get(from - 1).take(count).toString()).reverse().toString()));
    }

    public String top() {
        return stock.stream().map(CrateStack::top).collect(Collectors.joining());
    }

    public String toString() {
        return stock.stream()
                .map(CrateStack::toString)
                .collect(Collectors.joining("\n"));
    }
}
