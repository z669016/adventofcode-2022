package com.putoet.day21;

enum Operator {
    PLUS, MINUS, PRODUCT, DIVIDE, EQUALS;

    @Override
    public String toString() {
        return switch (this) {
            case PLUS -> "+";
            case MINUS -> "-";
            case DIVIDE -> "/";
            case PRODUCT -> "*";
            case EQUALS -> "=";
        };
    }

    public static Operator from(String operator) {
        return switch (operator) {
            case "+" -> PLUS;
            case "-" -> MINUS;
            case "/" -> DIVIDE;
            case "*" -> PRODUCT;
            case "=" -> EQUALS;
            default -> throw new IllegalArgumentException("oops ..." + operator);
        };
    }
}
