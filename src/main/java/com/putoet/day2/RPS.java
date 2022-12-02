package com.putoet.day2;

public enum RPS {
    ROCK, PAPER, SCISSORS;

    public int score() {
        return switch (this) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
        };
    }

    public static RPS of(String code) {
        return switch (code) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> throw new IllegalArgumentException("Invalid RPS code: " + code);
        };
    }

    public static RPS of2(String code, RPS other) {
        return switch (code) {
            case "X" -> switch (other) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
            case "Y" -> other;
            case "Z" -> switch (other) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
            default -> throw new IllegalArgumentException("Invalid RPS code: " + code);
        };
    }
}
