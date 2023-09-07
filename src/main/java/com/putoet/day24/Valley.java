package com.putoet.day24;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record Valley(@NotNull Point in, @NotNull Point out, @NotNull List<Blizzard> blizzards) {
    public Valley next() {
        return new Valley(in, out, blizzards.stream().map(Blizzard::next).toList());
    }

    public boolean contains(@NotNull Point point) {
        return (point.x() >= in.x() && point.x() <= out.x() &&
                point.y() > in.y() && point.y() < out.y()) ||
               point.equals(out) ||
               point.equals(in);
    }

    public boolean isOpen(@NotNull Point point) {
        return blizzards.stream().noneMatch(b -> b.location().equals(point));
    }

    public String toString(@NotNull Point elf) {
        final var string = toString().toCharArray();
        final var length = out.x() + 3;
        string[elf.y() * length + elf.x()] = 'E';

        return String.valueOf(string);
    }

    @Override
    public String toString() {
        final var minx = in.x() - 1;
        final var maxx = out.x() + 2;
        final var miny = in.y();
        final var maxy = out.y() + 1;

        final var grid = new char[maxy][maxx];
        for (var y = miny; y < maxy; y++) {
            for (var x = minx; x < maxx; x++) {
                if (y == 0 || y == grid.length - 1)
                    grid[y][x] = '#';
                else if (x == 0 || x == grid[0].length - 1)
                    grid[y][x] = '#';
                else
                    grid[y][x] = '.';
            }
        }
        grid[in.y()][in.x()] = '.';
        grid[out.y()][out.x()] = '.';

        blizzards.forEach(blizzard -> {
            final var p = blizzard.location();
            if (grid[p.y()][p.x()] == '#')
                throw new IllegalStateException("Blizzard is in a wall " + blizzard);

            if (grid[p.y()][p.x()] == '.')
                grid[p.y()][p.x()] = blizzard.symbol();
            else {
                if (Character.isLetter(grid[p.y()][p.x()]))
                    grid[p.y()][p.x()] = (char) (grid[p.y()][p.x()] + 1);
                else if (Character.isDigit(grid[p.y()][p.x()])) {
                    if (grid[p.y()][p.x()] < '9')
                        grid[p.y()][p.x()] = (char) (grid[p.y()][p.x()] + 1);
                    else
                        grid[p.y()][p.x()] = 'A';
                } else
                    grid[p.y()][p.x()] = '2';
            }
        });

        return Arrays.stream(grid)
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }

    public static Valley of(@NotNull List<String> input) {
        assert !input.isEmpty();

        final var symbols = Set.of('^', '>', 'v', '<');
        final var maxx = input.get(0).length() - 2;
        final var maxy = input.size() - 2;

        Point in = null, out = null;

        final var blizzards = new ArrayList<Blizzard>();
        for (var y = 0; y < input.size(); y++) {
            final var line = input.get(y);
            for (var x = 0; x < line.length(); x++) {
                if (y == 0 && line.charAt(x) == '.')
                    in = Point.of(x, y);
                if (y == input.size() - 1 && line.charAt(x) == '.')
                    out = Point.of(x, y);

                final var symbol = line.charAt(x);
                if (symbols.contains(line.charAt(x))) {
                    final var location = Point.of(x, y);
                    blizzards.add(new Blizzard(
                                    location,
                                    symbol,
                                    symbol == '<' || symbol == '>' ? maxx : maxy,
                                    switch (symbol) {
                                        case '^' -> Point.of(x,maxy);
                                        case '>' -> Point.of(1,y);
                                        case 'v' -> Point.of(x,1);
                                        case '<' -> Point.of(maxx, y);
                                        default -> throw new IllegalStateException("Cannot get here");
                                    }
                            )
                    );
                }
            }
        }

        return new Valley(in, out, blizzards);
    }
}
