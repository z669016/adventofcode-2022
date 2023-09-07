package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

abstract class Node {
    private final String name;
    private final Node parent;

    public Node(@NotNull String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public String name() {
        return name;
    }

    public Node parent() {
        return parent;
    }

    public abstract void visit(@NotNull Consumer<Node> visitor);

    public abstract long size();

    public abstract String print(@NotNull String indent);
}
