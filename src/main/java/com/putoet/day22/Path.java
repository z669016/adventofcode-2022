package com.putoet.day22;

import java.util.Iterator;

public class Path implements Iterator<String> {
    private final String path;
    private int offset;

    public Path(String path) {
        this.path = path;
        offset = 0;
    }

    public boolean hasNext() {
        return offset < path.length();
    }

    @Override
    public String next() {
        final char c = path.charAt(offset++);
        if (!Character.isDigit(c))
            return String.valueOf(c);

        final StringBuilder sb = new StringBuilder();
        sb.append(c);
        while (offset < path.length() && Character.isDigit(path.charAt(offset)))
            sb.append(path.charAt(offset++));

        return sb.toString();
    }
}
