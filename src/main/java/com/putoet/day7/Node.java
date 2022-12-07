package com.putoet.day7;

import java.util.function.Consumer;

public abstract class Node {
    private final String name;
    private final FileType type;
    private final Node parent;

    public Node(String name, FileType type, Node parent) {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public String name() {
        return name;
    }

    public FileType type() {
        return type;
    }

    public Node parent() {
        return parent;
    }

    public void visit(Consumer<Node> visitor) {
        visitor.accept(this);
    }

    public abstract long size();

    public abstract String print(String indent);
}
