package com.putoet.day7;

import java.util.function.Consumer;

public abstract class Node {
    private final String name;
    private final Node parent;

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public String name() {
        return name;
    }

    public Node parent() {
        return parent;
    }

    public abstract void visit(Consumer<Node> visitor);

    public abstract long size();

    public abstract String print(String indent);
}
