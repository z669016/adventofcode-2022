package com.putoet.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DirectorySizeFinder implements Consumer<Node> {
    private final List<Directory> folders = new ArrayList<>();
    private final long maxSize;

    private DirectorySizeFinder(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void accept(Node node) {
        if (node instanceof Directory dir) {
            if (dir.size() <= maxSize)
                folders.add(dir);

            for (Node file : dir.files()) {
                file.visit(this);
            }
        }
    }

    public List<Directory> folders() {
        return folders;
    }

    public static List<Directory> find(long maxSize, Directory root) {
        final DirectorySizeFinder finder = new DirectorySizeFinder(maxSize);
        root.visit(finder);
        return finder.folders();
    }
}
