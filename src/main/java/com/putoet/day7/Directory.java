package com.putoet.day7;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Directory extends Node {
    public static final Directory ROOT = new Directory("/", null);
    private Optional<Long> size = Optional.empty();

    private final List<Node> files = new ArrayList<>();
    public Directory(String name, Node parent) {
        super(name, FileType.DIRECTORY, parent);
    }

    public long size() {
        if (size.isEmpty())
            size = Optional.of(files.stream().mapToLong(Node::size).sum());

        return size.get();
    }

    public Directory add(Node node) {
        final Optional<Node> dir = files.stream().filter(n -> n.name().equals(node.name())).findFirst();
        if (dir.isEmpty())
            files.add(node);

        return this;
    }

    public Directory cd(String name) {
        final Optional<Node> dir = files.stream().filter(node -> node.name().equals(name)).findFirst();
        if (dir.isEmpty()) {
            final Directory newDir = new Directory(name, this);
            files.add(newDir);
            return newDir;
        }

        return (Directory) dir.get();
    }

    public List<Node> files() {
        return files;
    }

    public String print(String indent) {
        final StringBuilder sb = new StringBuilder();

        sb.append("%s- %s (%s)%n".formatted(indent, name(), type()));
        for (var file : files()) {
            sb.append(file.print(indent + "  "));
        }
        return sb.toString();
    }

}
