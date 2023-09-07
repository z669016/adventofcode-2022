package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

class DirectorySmallestFinder implements Consumer<Node> {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Directory> smallest = Optional.empty();
    private final long minSize;

    private DirectorySmallestFinder(long minSize) {
        this.minSize = minSize;
    }

    @Override
    public void accept(@NotNull Node node) {
        if (node instanceof Directory dir) {
            if (smallest.isEmpty())
                smallest = Optional.of(dir);

            if (dir.size() >= minSize && dir.size() < smallest.get().size())
                smallest = Optional.of(dir);
        }
    }

    public Optional<Directory> smallest() {
        return smallest;
    }

    public static Optional<Directory> find(long minSize, @NotNull Directory root) {
        final var finder = new DirectorySmallestFinder(minSize);
        root.visit(finder);
        return finder.smallest();
    }
}
