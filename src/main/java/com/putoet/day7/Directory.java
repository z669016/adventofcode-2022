package com.putoet.day7;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public class Directory extends Node {
    public static final Directory ROOT = new Directory("/", null);
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Long> size = Optional.empty();

    private final List<Node> files = new ArrayList<>();
    public Directory(String name, Node parent) {
        super(name, parent);
    }

    @Override
    public long size() {
        if (size.isEmpty())
            size = Optional.of(files.stream().mapToLong(Node::size).sum());

        return size.get();
    }

    @Override
    public void visit(Consumer<Node> visitor) {
        visitor.accept(this);
        files.forEach(file -> file.visit(visitor));
    }

    public Directory add(Node node) {
        files.add(node);
        return this;
    }

    public Directory cd(String name) {
        return (Directory) files.stream().filter(node -> node.name().equals(name)).findFirst().orElseThrow();
    }

    public List<Node> files() {
        return files;
    }

    public String print(String indent) {
        final StringBuilder sb = new StringBuilder();

        sb.append("%s - %s (di)%n".formatted(indent, name()));
        for (var file : files()) {
            sb.append(file.print(indent + "  "));
        }
        return sb.toString();
    }
}
