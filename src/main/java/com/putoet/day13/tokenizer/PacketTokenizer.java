package com.putoet.day13.tokenizer;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class PacketTokenizer implements Iterator<Token> {
    private final String input;
    private int offset;

    public PacketTokenizer(@NotNull String input) {
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return offset < input.length();
    }

    @Override
    public Token next() {
        if (!hasNext())
            throw new IllegalStateException("Passed end of input");

        char c = input.charAt(offset++);
        if (c == ',')
            c = input.charAt(offset++);

        if (c == '[')
            return new Token(TokenType.START_LIST, String.valueOf(c));

        if (c == ']')
            return new Token(TokenType.END_LIST, String.valueOf(c));

        final var sb = new StringBuilder();
        while (Character.isDigit(c)) {
            sb.append(c);
            c = input.charAt(offset++);
        }

        if (c != ',')
            offset--;

        return new Token(TokenType.VALUE, sb.toString());
    }
}
