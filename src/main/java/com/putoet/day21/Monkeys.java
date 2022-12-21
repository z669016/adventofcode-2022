package com.putoet.day21;

import org.javatuples.Pair;

import java.util.*;
import java.util.function.Supplier;

public class Monkeys implements Supplier<Long> {
    private final Map<String, Monkey> map = new HashMap<>();

    @Override
    public Long get() {
        return map.get("root").get();
    }

    public void put(String name, Monkey monkey) {
        map.put(name, monkey);
    }

    public Monkey get(String name) {
        return map.get(name);
    }

    public static Monkeys from(List<String> lines) {
        final Monkeys monkeys = new Monkeys();
        lines.forEach(line -> {
            final var pair = monkeys.from(line);
            monkeys.map.put(pair.getValue0(), pair.getValue1());
        });

        return monkeys;
    }

    public Pair<String,Monkey> from(String line) {
        assert line != null;

        final String[] splitLine = line.split(": ");
        final String[] splitOperation = splitLine[1].split(" ");

        if (splitOperation.length == 1) {
            final Long value = Long.parseLong(splitOperation[0]);
            return Pair.with(splitLine[0], new Monkey(splitLine[0], () -> value));
        }

        final String left = splitOperation[0];
        final String right = splitOperation[2];
        return Pair.with(splitLine[0], new Monkey(splitLine[0], switch (splitOperation[1]) {
            case "+" -> new MonkeyOperation() {
                OptionalLong value = OptionalLong.empty();

                @Override
                public Long get() {
                    if (value.isEmpty())
                        value = OptionalLong.of(map.get(left).get() + map.get(right).get());

                    return value.getAsLong();
                }

                @Override
                public Optional<String> left() { return Optional.of(left); }
                @Override
                public Optional<String> right() { return Optional.of(left); }
            };
            case "-" -> new MonkeyOperation() {
                OptionalLong value = OptionalLong.empty();

                @Override
                public Long get() {
                    if (value.isEmpty())
                        value = OptionalLong.of(map.get(left).get() - map.get(right).get());

                    return value.getAsLong();
                }
                @Override
                public Optional<String> left() { return Optional.of(left); }
                @Override
                public Optional<String> right() { return Optional.of(left); }
            };
            case "/" -> new MonkeyOperation() {
                OptionalLong value = OptionalLong.empty();

                @Override
                public Long get() {
                    if (value.isEmpty())
                        value = OptionalLong.of(map.get(left).get() / map.get(right).get());

                    return value.getAsLong();
                }
                @Override
                public Optional<String> left() { return Optional.of(left); }
                @Override
                public Optional<String> right() { return Optional.of(left); }
            };
            case "*" -> new MonkeyOperation() {
                OptionalLong value = OptionalLong.empty();

                @Override
                public Long get() {
                    if (value.isEmpty())
                        value = OptionalLong.of(map.get(left).get() * map.get(right).get());

                    return value.getAsLong();
                }
                @Override
                public Optional<String> left() { return Optional.of(left); }
                @Override
                public Optional<String> right() { return Optional.of(left); }
            };
            default -> throw new IllegalArgumentException("Invalid monkey spec: " + line);
        }));
    }
}
