package com.putoet.day19;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.*;

public class Day19 extends Day {
    private final Map<Integer,Blueprint> blueprints;

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
                .map(this::max)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println("The sum of all blueprint quality levels is " + max.stream().mapToInt(BlueprintState::qualityLevel).sum());

        // 943 is too low
    }

    public Optional<BlueprintState> max(Blueprint blueprint) {
        final Set<BlueprintState> history = new HashSet<>();
        final Map<Integer,BlueprintState> max = new HashMap<>();
        final Deque<BlueprintState> queue = new LinkedList<>();

        var init = BlueprintState.init(blueprint);
        max.put(0, init);
        queue.offer(init);
        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.prod().geode() > max.computeIfAbsent(current.minutes(), k -> current).prod().geode())
                max.put(current.minutes(), current);

            if (current.minutes() == 24) {
                continue;
            }

            if (history.size() % 10_000 == 0)
                System.out.println(history.size() + " - " + current);

            var affordable = blueprint.affordable(current);
            affordable.stream()
                    .filter(state -> state.prod().geode() >= max.computeIfAbsent(state.minutes(), k -> state).prod().geode())
                    .filter(history::add)
                    .forEach(queue::offer);
        }

        if (max.containsKey(24))
            System.out.println("MAX: " + max.get(24));

        return max.containsKey(24) ? Optional.of(max.get(24)) : Optional.empty();
    }

    @Override
    public void part2() {
    }
}
