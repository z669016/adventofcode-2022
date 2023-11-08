package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Supplier;

class Values {
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

    public static Values of(@NotNull List<String> lines) {
        final var values = new Values();
        lines.forEach(line -> {
            final var entry = values.of(line);
            values.map.put(entry.getKey(), entry.getValue());
        });

        return values;
    }

    public Map.Entry<String, Supplier<String>> of(@NotNull String line) {
        final var splitLine = line.split(": ");
        final var splitOperation = splitLine[1].split(" ");

        if (splitOperation.length == 1) {
            final long value = Long.parseLong(splitOperation[0]);
            return Map.entry(splitLine[0], new Value(value));
        }

        final var left = splitOperation[0];
        final var operator = Operator.from(splitOperation[1]);
        final var right = splitOperation[2];

        return Map.entry(splitLine[0], new Operation(this, left, operator, right));
    }

    public static OptionalLong tryParse(@NotNull String value) {
        try {
            return OptionalLong.of(Long.parseLong(value));
        } catch (RuntimeException ignored) {}

        return OptionalLong.empty();
    }
}
