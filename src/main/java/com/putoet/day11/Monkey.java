package com.putoet.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Monkey {
    private final int id;
    private List<BigInteger> items;
    private final Function<BigInteger,BigInteger> operation;
    private final Function<BigInteger,BigInteger> bored;
    private final Function<BigInteger,Boolean> test;

    private long inspected;

    private Monkey ifTrue, ifFalse;

    public Monkey(int id,
                  List<BigInteger> items,
                  Function<BigInteger,BigInteger> operation,
                  Function<BigInteger,BigInteger> bored,
                  Function<BigInteger,Boolean> test
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

    public Monkey accept (BigInteger value) {
        items.add(value);

        return this;
    }

    public Monkey round() {
        assert ifTrue != null;
        assert ifFalse != null;

        for (var value : items) {
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

        return this;
    }

    public long inspected() {
        return inspected;
    }

    public List<BigInteger> items() {
        return items;
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
