package com.putoet.day7;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.Optional;

public class Day7 extends Day {
    private static final long TOTAL_DISK_SPACE = 70_000_000;
    private static final long NEEDED_DISK_SPACE = 30_000_000;
    private final Directory root;

    public Day7(String[] args) {
        super(args);
        root = parseLog(ResourceLines.list("/day7.txt"));
    }

    private Directory parseLog(List<String> input) {
        final Directory root = Directory.ROOT;
        Directory cwd = root;

        for (var line : input) {
            if (line.charAt(0) == 'd') { // directory
                final String[] split = line.split(" ");
                assert split.length == 2;

                cwd.add(new Directory(split[1], cwd));
            } else if (Character.isDigit(line.charAt(0))) { // file
                final String[] split = line.split(" ");
                assert split.length == 2;

                cwd.add(new File(split[1], Integer.parseInt(split[0]), cwd));
            } else { // command
                final String[] split = line.split(" ");
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

    public static void main(String[] args) {
        final var day = new Day7(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("Total size of directories not bigger than 100.000 is: " + totalSize());
    }

    public long totalSize() {
        final List<Directory> folders = DirectorySizeFinder.find(100_000, root);
        return folders.stream().mapToLong(Directory::size).sum();
    }

    @Override
    public void part2() {
        System.out.println("Smallest directoy to delete is: " + smallestToDelete().orElseThrow().size());
    }

    public long freeUp() {
        return NEEDED_DISK_SPACE - (TOTAL_DISK_SPACE - root.size());
    }

    public Optional<Directory> smallestToDelete() {
        return DirectorySmallestFinder.find(freeUp(), root);
    }
}
