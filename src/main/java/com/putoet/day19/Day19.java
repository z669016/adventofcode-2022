package com.putoet.day19;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.*;
import java.util.stream.Stream;

public class Day19 extends Day {
    private final Map<Integer, Blueprint> blueprints;

    public Day19(String[] args) {
        super(args);

        blueprints = Blueprint.from(ResourceLines.list("/day19.txt"));
    }

    public Collection<Blueprint> blueprints() {
        return blueprints.values();
    }

    public static void main(String[] args) {
        final var day = new Day19(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final var max = blueprints().stream()
                .parallel()
                .map(Blueprint::max)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println("The sum of all blueprint quality levels is " + max.stream().mapToInt(BlueprintState::qualityLevel).sum());
    }

    @Override
    public void part2() {
        var mul = Stream.of(1, 2, 3)
                .parallel()
                .map(i -> blueprints.get(i).max(32).orElseThrow())
                .mapToInt(state -> state.prod().geode())
                .reduce(1, (a,b) -> a * b);

        System.out.println("The max number of geodes of the first three blueprints multiplied is " + mul);
    }
}
