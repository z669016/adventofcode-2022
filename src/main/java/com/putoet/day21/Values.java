package com.putoet.day21;

import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Supplier;

public class Values {
    public static final String HUMN = "humn";
    public static final String ROOT = "root";

    private final Map<String, Supplier<String>> map = new HashMap<>();
    public String get(String name) {
        return map.get(name).get();
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }

    public Supplier<String> getOperation(String name) {
        return map.get(name);
    }

    public void setOperation(String name, Supplier<String> operation) {
        map.put(name, operation);
    }

    public static Values from(List<String> lines) {
        final Values values = new Values();
        lines.forEach(line -> {
            final var pair = values.from(line);
            values.map.put(pair.getValue0(), pair.getValue1());
        });

        return values;
    }

    public Pair<String, Supplier<String>> from(String line) {
        assert line != null;

        final String[] splitLine = line.split(": ");
        final String[] splitOperation = splitLine[1].split(" ");

        if (splitOperation.length == 1) {
            final long value = Long.parseLong(splitOperation[0]);
            return Pair.with(splitLine[0], new Value(value));
        }

        final String left = splitOperation[0];
        final Operator operator = Operator.from(splitOperation[1]);
        final String right = splitOperation[2];

        return Pair.with(splitLine[0], new Operation(this, left, operator, right));
    }

    public static OptionalLong tryParse(String value) {
        try {
            return OptionalLong.of(Long.parseLong(value));
        } catch (RuntimeException ignored) {}

        return OptionalLong.empty();
    }
}
