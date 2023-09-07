package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class DirectorySizeFinder implements Consumer<Node> {
    private final List<Directory> folders = new ArrayList<>();
    private final long maxSize;

    private DirectorySizeFinder(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void accept(@NotNull Node node) {
        if (node instanceof Directory dir) {
            if (dir.size() <= maxSize)
                folders.add(dir);
        }
    }

    public List<Directory> folders() {
        return folders;
    }

    public static List<Directory> find(long maxSize, @NotNull Directory root) {
        final var finder = new DirectorySizeFinder(maxSize);
        root.visit(finder);
        return finder.folders();
    }
}
