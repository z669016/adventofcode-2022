package com.putoet.day7;

import java.util.function.Consumer;

public class File extends Node {
    private final long size;

    public File(String name, long size, Node parent) {
        super(name, parent);
        this.size = size;
    }
    @Override
    public long size() {
        return size;
    }

    @Override
    public void visit(Consumer<Node> visitor) {
        visitor.accept(this);
    }

    @Override
    public String print(String indent) {
        return "%s - %s (file, size=%d)%n".formatted(indent, name(), size);
    }
}
