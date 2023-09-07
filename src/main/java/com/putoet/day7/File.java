package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

class File extends Node {
    private final long size;

    public File(@NotNull String name, long size, @NotNull Node parent) {
        super(name, parent);
        this.size = size;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public void visit(@NotNull Consumer<Node> visitor) {
        visitor.accept(this);
    }

    @Override
    public String print(@NotNull String indent) {
        return "%s - %s (file, size=%d)%n".formatted(indent, name(), size);
    }
}
