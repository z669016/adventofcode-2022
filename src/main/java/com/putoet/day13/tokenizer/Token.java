package com.putoet.day13.tokenizer;

public record Token(TokenType type, String value) {
    public int valueAsInt() {
        return Integer.parseInt(value);
    }
}
