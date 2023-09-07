package com.putoet.day22;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

class Path implements Iterator<String> {
    private final String path;
    private int offset;

    public Path(@NotNull String path) {
        this.path = path;
        offset = 0;
    }

    public boolean hasNext() {
        return offset < path.length();
    }

    @Override
    public String next() {
        final var c = path.charAt(offset++);
        if (!Character.isDigit(c))
            return String.valueOf(c);

        final var sb = new StringBuilder();
        sb.append(c);
        while (offset < path.length() && Character.isDigit(path.charAt(offset)))
            sb.append(path.charAt(offset++));

        return sb.toString();
    }
}
