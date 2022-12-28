package com.putoet.day25;

public enum SnafuDigit {
    TWO, ONE, ZERO, MINUS, DOUBLE_MINUS;

    public int asDecimal() {
        return switch (this) {
            case TWO -> 2;
            case ONE -> 1;
            case ZERO -> 0;
            case MINUS -> -1;
            case DOUBLE_MINUS -> -2;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case TWO -> "2";
            case ONE -> "1";
            case ZERO -> "0";
            case MINUS -> "-";
            case DOUBLE_MINUS -> "=";
        };
    }

    public static SnafuDigit from(int decimal) {
        return switch (decimal) {
            case 2 -> TWO;
            case 1 -> ONE;
            case 0 -> ZERO;
            case -1 -> MINUS;
            case -2 -> DOUBLE_MINUS;
            default -> throw new IllegalArgumentException("Invalid decimal for snafu digit: " + decimal);
        };
    }

    public static SnafuDigit from(char snafuDigit) {
        return switch (snafuDigit) {
            case '2' -> TWO;
            case '1' -> ONE;
            case '0' -> ZERO;
            case '-' -> MINUS;
            case '=' -> DOUBLE_MINUS;
            default -> throw new IllegalArgumentException("Invalid snafu character: " + snafuDigit);
        };
    }
}
