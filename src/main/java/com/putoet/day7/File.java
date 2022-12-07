package com.putoet.day7;

public class File extends Node {
    private final long size;

    public File(String name, long size, Node parent) {
        super(name, FileType.FILE, parent);
        this.size = size;
    }
    @Override
    public long size() {
        return size;
    }

    @Override
    public String print(String indent) {
        return "%s- %s (%s, size=%d)%n".formatted(indent, name(), type(), size);
    }
}
