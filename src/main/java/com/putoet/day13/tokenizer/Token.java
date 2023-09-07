package com.putoet.day13.tokenizer;

import org.jetbrains.annotations.NotNull;

public record Token(@NotNull TokenType type, @NotNull String value) {
    public int valueAsInt() {
        return Integer.parseInt(value);
    }
}
