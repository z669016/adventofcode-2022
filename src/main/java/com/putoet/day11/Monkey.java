package com.putoet.day11;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Monkey {
    private final int id;
    private List<Long> items;
    private final Function<Long,Long> operation;
    private final int divisor;

    private long inspected;

    private Monkey ifTrue, ifFalse;

    public Monkey(int id,
                  @NotNull List<Long> items,
                  @NotNull Function<Long,Long> operation,
                  int divisor
    ) {
        assert divisor != 0;

        this.id = id;
        this.items = new ArrayList<>(items);
        this.operation = operation;
        this.divisor = divisor;

        ifTrue = ifFalse = null;
    }

    public Monkey next(boolean whatIf, @NotNull Monkey to) {
        if (whatIf)
            ifTrue = to;
        else
            ifFalse = to;

        return this;
    }

    public void accept (@NotNull Long value) {
        items.add(value);
    }

    public void round(@NotNull Function<Long,Long> bored) {
        assert ifTrue != null;
        assert ifFalse != null;

        for (var value : items) {
            inspected++;

            value = operation.apply(value);
            value = bored.apply(value);
            if (value % divisor == 0) {
                ifTrue.accept(value);
            } else {
                ifFalse.accept(value);
            }
        }

        items = new ArrayList<>();
    }

    public long inspected() {
        return inspected;
    }

    public List<Long> items() {
        return items;
    }

    public int divisor() {
        return divisor;
    }

    public Monkey items(@NotNull List<Long> items) {
        this.items = new ArrayList<>(items);

        return this;
    }

    @Override
    public String toString() {
        return "Monkey " +
               id +
               ": " +
               items.stream()
                       .map(String::valueOf)
                       .collect(Collectors.joining(", "));
    }
}
