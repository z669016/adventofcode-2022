package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;
import java.util.Optional;

public class Day7 {
    private static final long TOTAL_DISK_SPACE = 70_000_000;
    private static final long NEEDED_DISK_SPACE = 30_000_000;

    public static void main(String[] args) {
        final var root = parseLog(ResourceLines.list("/day7.txt"));

        Timer.run(() -> System.out.println("Total size of directories not bigger than 100.000 is: " + totalSize(root)));
        Timer.run(() -> System.out.println("Smallest directory to delete is: " + smallestToDelete(root).orElseThrow().size()));
    }

    static Directory parseLog(List<String> input) {
        final var root = Directory.ROOT;
        var cwd = root;

        for (var line : input) {
            if (line.charAt(0) == 'd') { // directory
                final var split = line.split(" ");
                assert split.length == 2;

                cwd.add(new Directory(split[1], cwd));
            } else if (Character.isDigit(line.charAt(0))) { // file
                final var split = line.split(" ");
                assert split.length == 2;

                cwd.add(new File(split[1], Integer.parseInt(split[0]), cwd));
            } else { // command
                final var split = line.split(" ");
                if (split[1].equals("cd")) {
                    switch (split[2]) {
                        case "/" -> cwd = root;
                        case ".." -> cwd = (Directory) cwd.parent();
                        default -> cwd = cwd.cd(split[2]);
                    }
                }
            }
        }

        return root;
    }

    static long totalSize(Directory root) {
        final List<Directory> folders = DirectorySizeFinder.find(100_000, root);
        return folders.stream().mapToLong(Directory::size).sum();
    }

    static Optional<Directory> smallestToDelete(Directory root) {
        return DirectorySmallestFinder.find(freeUp(root), root);
    }

    static long freeUp(Directory root) {
        return NEEDED_DISK_SPACE - (TOTAL_DISK_SPACE - root.size());
    }
}
