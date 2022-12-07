package com.putoet.day7;

import java.util.Optional;
import java.util.function.Consumer;

public class DirectorySmallestFinder implements Consumer<Node> {
    private Optional<Directory> smallest = Optional.empty();
    private final long minSize;

    private DirectorySmallestFinder(long minSize) {
        this.minSize = minSize;
    }

    @Override
    public void accept(Node node) {
        if (node instanceof Directory dir) {
            if (smallest.isEmpty())
                smallest = Optional.of(dir);

            if (dir.size() >= minSize && dir.size() < smallest.get().size())
                smallest = Optional.of(dir);

            for (Node file : dir.files()) {
                file.visit(this);
            }
        }
    }

    public Optional<Directory> smallest() {
        return smallest;
    }

    public static Optional<Directory> find(long minSize, Directory root) {
        final DirectorySmallestFinder finder = new DirectorySmallestFinder(minSize);
        root.visit(finder);
        return finder.smallest();
    }
}
