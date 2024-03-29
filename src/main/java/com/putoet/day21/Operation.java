package com.putoet.day21;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

class Operation implements Supplier<String> {
    private final Values values;
    private final String left;
    private final Operator operator;
    private final String right;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<String> cachedValue = Optional.empty();

    public Operation(@NotNull Values values, @NotNull String left, @NotNull Operator operator, @NotNull String right) {
        this.values = values;
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public String left() {
        return left;
    }

    public Operator operator() {
        return operator;
    }

    public String right() {
        return right;
    }

    @Override
    public String get() {
        if (cachedValue.isPresent())
            return cachedValue.get();

        final var op1s = values.get(left);
        final var op1 = Values.tryParse(op1s);

        final var op2s = values.get(right);
        final var op2 = Values.tryParse(op2s);

        if (op1.isEmpty() || op2.isEmpty())
            return "(%s %s %s)".formatted(op1.isEmpty() ? op1s : op1.getAsLong(), operator, op2.isEmpty() ? op2s : op2.getAsLong());

        this.cachedValue = Optional.of(switch (operator) {
            case PLUS -> String.valueOf(op1.getAsLong() + op2.getAsLong());
            case MINUS -> String.valueOf(op1.getAsLong() - op2.getAsLong());
            case DIVIDE -> String.valueOf(op1.getAsLong() / op2.getAsLong());
            case PRODUCT -> String.valueOf(op1.getAsLong() * op2.getAsLong());
            case EQUALS -> String.valueOf(op1.getAsLong() == op2.getAsLong());
        });

        return cachedValue.get();
    }

    @Override
    public String toString() {
        return "(%s %s %s)".formatted(left, operator, right);
    }
}
