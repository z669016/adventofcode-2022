package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.*;
import java.util.stream.Stream;

public class Day19  {
    public static void main(String[] args) {
        final var blueprints = Blueprint.of(ResourceLines.list("/day19.txt"));

        Timer.run(() -> part1(blueprints));
        Timer.run(() -> part2(blueprints));
    }

    static void part1(Map<Integer, Blueprint> blueprints) {
        final var max = blueprints.values().stream()
                .parallel()
                .map(Blueprint::max)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println("The sum of all blueprint quality levels is " + max.stream().mapToInt(BlueprintState::qualityLevel).sum());
    }

    static void part2(Map<Integer, Blueprint> blueprints) {
        var mul = Stream.of(1, 2, 3)
                .parallel()
                .map(i -> blueprints.get(i).max(32).orElseThrow())
                .mapToInt(state -> state.prod().geode())
                .reduce(1, (a,b) -> a * b);

        System.out.println("The max number of geodes of the first three blueprints multiplied is " + mul);
    }
}
