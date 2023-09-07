package com.putoet.day23;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Elves {
    public static final char ELF = '#';
    public static final char OPEN = '.';

    private final List<Elf> elves;

    private Elves(List<Elf> elves) {
        this.elves = elves;
    }

    public int move(@NotNull List<ValidDirection> validDirections) {
        final var proposedMoves = new HashMap<Point,List<Elf>>();

        for (var elf : elves) {
            final var proposal = elf.propose(validDirections, elves);
            if (proposal.isPresent()) {
                final var elvesProposals = proposedMoves.computeIfAbsent(proposal.get(), k -> new ArrayList<>());
                elvesProposals.add(elf);
            }
        }

        var count = 0;
        for (var entry : proposedMoves.entrySet()) {
            if (entry.getValue().size() == 1) {
                entry.getValue().get(0).move();
                count++;
            }
        }

        return count;
    }

    public static Elves of(@NotNull List<String> input) {
        assert !input.isEmpty();

        final var elves = new ArrayList<Elf>();
        for (var y = 0; y < input.size(); y++) {
            final var line = input.get(y);
            for (var x = 0; x < line.length(); x++) {
                if (ELF == line.charAt(x)) {
                    elves.add(new Elf(Point.of(x, y)));
                }
            }
        }

        return new Elves(elves);
    }

    public long emptyGrounds() {
        return toString().chars()
                .filter(c -> c == OPEN)
                .count();
    }

    @Override
    public String toString() {
        final var minx = elves.stream().map(Elf::location).mapToInt(Point::x).min().orElseThrow();
        final var maxx = elves.stream().map(Elf::location).mapToInt(Point::x).max().orElseThrow() + 1;
        final var miny = elves.stream().map(Elf::location).mapToInt(Point::y).min().orElseThrow();
        final var maxy = elves.stream().map(Elf::location).mapToInt(Point::y).max().orElseThrow() + 1;

        final var grid = new Grid(minx, maxx, miny, maxy, GridUtils.of(minx, maxx, miny, maxy, OPEN));
        elves.forEach(elf -> grid.set(elf.location().x(), elf.location().y(), ELF));

        return Arrays.stream(grid.grid())
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }
}
