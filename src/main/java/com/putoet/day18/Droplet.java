package com.putoet.day18;

import com.putoet.grid.Point3D;
import com.putoet.grid.Points3D;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Droplet {
    private static final char STEAM = '@';
    private static final char LAVA = '#';
    public static final char AIR = '.';

    private final char[][][] cubes;
    private final List<Point3D> points;

    public Droplet(@NotNull List<Point3D> input) {
        this.points = input.stream()
                .map(p -> Point3D.of(p.x() + 1, p.y() + 1, p.z() + 1))
                .toList();

        var square = Stream.of(
                this.points.stream().mapToInt(Point3D::x).max().orElseThrow() + 2,
                this.points.stream().mapToInt(Point3D::y).max().orElseThrow() + 2,
                this.points.stream().mapToInt(Point3D::z).max().orElseThrow() + 2
        ).mapToInt(i -> i).max().orElseThrow();
        final var size = (square % 2 == 1) ? square + 1 : square;

        cubes = new char[size][size][size];
        for (var z = 0; z < size; z++)
            for (var y = 0; y < size; y++)
                for (var x = 0; x < size; x++) {
                    cubes[z][y][x] = AIR;
                }

        this.points.forEach(p -> cubes[p.z()][p.y()][p.x()] = LAVA);

        colorExterior(cubes);
    }

    private void colorExterior(char[][][] cubes) {
        final var history = new HashSet<Point3D>();
        final var queue = new LinkedList<Point3D>();
        queue.offer(Point3D.ORIGIN);
        history.add(Point3D.ORIGIN);

        while (!queue.isEmpty()) {
            final var current = queue.poll();

            if (cubes[current.z()][current.y()][current.x()] == AIR) {
                cubes[current.z()][current.y()][current.x()] = STEAM;

                Points3D.directionsSquare().stream()
                        .map(current::add)
                        .filter(p -> p.x() >= 0 && p.x() < cubes.length)
                        .filter(p -> p.y() >= 0 && p.y() < cubes.length)
                        .filter(p -> p.z() >= 0 && p.z() < cubes.length)
                        .filter(history::add)
                        .forEach(queue::offer);
            }
        }
    }

    public static Droplet of(@NotNull List<String> input) {
        return new Droplet(input.stream()
                .map(line -> {
                            final var split = line.split(",");
                            return Point3D.of(
                                    Integer.parseInt(split[0]),
                                    Integer.parseInt(split[1]),
                                    Integer.parseInt(split[2]));
                        }
                )
                .toList()
        );
    }

    public int size() {
        return points.size();
    }

    public int connected() {
        return count(c -> c != LAVA);
    }

    public int exterior() {
        return count(c -> c == STEAM);
    }

    public int count(@NotNull Predicate<Character> filter) {
        var count = 0;
        for (var p : points) {
            if (filter.test(cubes[p.z()][p.y()][p.x() + 1])) count++;
            if (filter.test(cubes[p.z()][p.y()][p.x() - 1])) count++;
            if (filter.test(cubes[p.z()][p.y() + 1][p.x()])) count++;
            if (filter.test(cubes[p.z()][p.y() - 1][p.x()])) count++;
            if (filter.test(cubes[p.z() + 1][p.y()][p.x()])) count++;
            if (filter.test(cubes[p.z() - 1][p.y()][p.x()])) count++;
        }

        return count;
    }
}
