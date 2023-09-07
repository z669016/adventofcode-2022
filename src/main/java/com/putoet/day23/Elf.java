package com.putoet.day23;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class Elf {
    private Point location;
    private Point proposal;

    public Elf(@NotNull Point location) {
        this.location = location;
        this.proposal = null;
    }

    public Point location() {
        return location;
    }

    public void move() {
        this.location = proposal;
        this.proposal = null;
    }

    public Optional<Point> propose(@NotNull List<ValidDirection> validDirections, @NotNull List<Elf> elves) {
        this.proposal = null;

        final Set<Point> neighbours = elves.stream()
                .filter(e -> !e.equals(this))
                .filter(e -> Math.abs(location.x() - e.location.x()) < 2 && Math.abs(location.y() - e.location.y()) < 2 )
                .map(Elf::location)
                .collect(Collectors.toSet());

        // If no other Elves are in one of those eight positions, the Elf does not do anything during this round.
        if (neighbours.isEmpty())
            return Optional.empty();

        for (var proposal : validDirections) {
            if (!neighbours.contains(proposal.options().get(0).add(location)) &&
                !neighbours.contains(proposal.options().get(1).add(location)) &&
                !neighbours.contains(proposal.options().get(2).add(location))
                ) {
                this.proposal = location.add(proposal.move());
                break;
            }
        }

        return Optional.ofNullable(proposal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Elf elf)) return false;
        return location.equals(elf.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
