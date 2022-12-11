package com.putoet.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Monkey {
    public static boolean verbose = false;
    public static int round = 0;
    private final int id;
    private List<Long> items;
    private final Function<Long,Long> operation;
    private final Function<Long,Long> bored;
    private final Function<Long,Boolean> test;

    private long inspected;

    private Monkey ifTrue, ifFalse;

    public Monkey(int id,
                  List<Long> items,
                  Function<Long,Long> operation,
                  Function<Long,Long> bored,
                  Function<Long,Boolean> test
    ) {
        assert items != null;
        assert items != operation;
        assert items != bored;
        assert items != test;

        this.id = id;
        this.items = new ArrayList<>(items);
        this.operation = operation;
        this.bored = bored;
        this.test = test;

        ifTrue = ifFalse = null;
    }

    public Monkey next(boolean whatIf, Monkey to) {
        assert to != null;

        if (whatIf)
            ifTrue = to;
        else
            ifFalse = to;

        return this;
    }

    public Monkey accept (Long value) {
        items.add(value);

        return this;
    }

    public Monkey round() {
        assert ifTrue != null;
        assert ifFalse != null;

        for (var value : items) {
            if (verbose)
                System.out.println(round + " - " + id);

            inspected++;

            value = operation.apply(value);
            value = bored.apply(value);
            if (test.apply(value)) {
                ifTrue.accept(value);
            } else {
                ifFalse.accept(value);
            }
        }

        items = new ArrayList<>();

        round++;
        return this;
    }

    public long inspected() {
        return inspected;
    }

    public List<Long> items() {
        return items;
    }

    public Monkey items(List<Long> items) {
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
