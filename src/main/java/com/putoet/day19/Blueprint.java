package com.putoet.day19;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public record Blueprint(int id, List<RobotCost> costs, int maxOre, int maxClay, int maxObsidian) {

    public Blueprint(int id, List<RobotCost> costs) {
        this(id, costs,
                costs.stream().mapToInt(RobotCost::ore).max().orElseThrow(),
                costs.stream().mapToInt(RobotCost::clay).max().orElseThrow(),
                costs.stream().mapToInt(RobotCost::obsidian).max().orElseThrow());
    }

    public Blueprint {
        assert costs != null;
        assert costs.size() == 4;
    }

    public List<BlueprintState> affordable(BlueprintState state, int maxMinutes) {
        if (canAfford(RobotType.GEODE_ROBOT, state.prod())) {
            return List.of(manufacture(RobotType.GEODE_ROBOT, state, maxMinutes));
        }

        final List<BlueprintState> affordable = new ArrayList<>();
        if (canAfford(RobotType.ORE_ROBOT, state.prod()) && state.robots().ore() <= maxOre)
            affordable.add(manufacture(RobotType.ORE_ROBOT, state, maxMinutes));

        if (canAfford(RobotType.CLAY_ROBOT, state.prod()) && state.robots().clay() <= maxClay )
            affordable.add(manufacture(RobotType.CLAY_ROBOT, state, maxMinutes));

        if (canAfford(RobotType.OBSIDIAN_ROBOT, state.prod()) && state.robots().obsidian() <= maxObsidian)
            affordable.add(manufacture(RobotType.OBSIDIAN_ROBOT, state, maxMinutes));

        affordable.add(next(state, maxMinutes));

        return affordable;
    }

    public boolean canAfford(RobotType type, Prod prod) {
        final RobotCost cost = costs.get(type.key());

        return prod.ore() >= cost.ore() &&
               prod.clay() >= cost.clay() &&
               prod.obsidian() >= cost.obsidian();
    }

    private BlueprintState manufacture(RobotType type, BlueprintState state, int maxMinutes) {
        final int maxOre = (maxMinutes - state.minutes()) * this.maxOre;
        final int maxClay = (maxMinutes - state.minutes()) * this.maxClay;
        final int maxObsidian = (maxMinutes - state.minutes()) * this.maxObsidian;

        return new BlueprintState(
                state.blueprint(),
                state.minutes() + 1,
                state.prod().add(state.robots(), maxOre, maxClay, maxObsidian).sub(costs.get(type.key())),
                state.robots().add(type)
        );
    }

    public BlueprintState next(BlueprintState state, int maxMinutes) {
        final int maxOre = (maxMinutes - state.minutes()) * this.maxOre;
        final int maxClay = (maxMinutes - state.minutes()) * this.maxClay;
        final int maxObsidian = (maxMinutes - state.minutes()) * this.maxObsidian;

        return new BlueprintState(state.blueprint(),
                state.minutes() + 1,
                state.prod().add(state.robots(), maxOre, maxClay, maxObsidian),
                state.robots());
    }

    public static final Pattern INPUT_PATTERN = Pattern.compile(
            "Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");

    public static Blueprint from(String line) {
        assert line != null;

        final Matcher matcher = INPUT_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid blueprint description: " + line);

        return new Blueprint(Integer.parseInt(matcher.group(1)),
                List.of(
                        new RobotCost(Integer.parseInt(matcher.group(2)), 0, 0),
                        new RobotCost(Integer.parseInt(matcher.group(3)), 0, 0),
                        new RobotCost(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), 0),
                        new RobotCost(Integer.parseInt(matcher.group(6)), 0, Integer.parseInt(matcher.group(7)))
                ));
    }

    public static Map<Integer, Blueprint> from(List<String> lines) {
        return lines.stream()
                .map(Blueprint::from)
                .collect(Collectors.toMap(Blueprint::id, blueprint -> blueprint));
    }

    public Optional<BlueprintState> max() {
        return max(24);
    }

    public Optional<BlueprintState> max(int maxMinutes) {
        final Set<BlueprintState> history = new HashSet<>();
        final Map<Integer,BlueprintState> maxGeodes = new HashMap<>();
        final Map<Integer, Integer> geodeBots = new HashMap<>();
        final PriorityQueue<BlueprintState> queue = new PriorityQueue<>(
                Comparator.comparing((BlueprintState state) -> state.prod().geode())
                        .thenComparing((BlueprintState state) -> state.prod().obsidian())
                        .reversed()
        );

        var init = BlueprintState.init(this);
        maxGeodes.put(0, init);
        geodeBots.put(0, 0);

        queue.offer(init);
        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.prod().geode() > maxGeodes.computeIfAbsent(current.minutes(), k -> current).prod().geode())
                maxGeodes.put(current.minutes(), current);

            if (current.minutes() == maxMinutes) {
                continue;
            }

            if (current.robots().geode() > geodeBots.computeIfAbsent(current.minutes(), k -> 0))
                geodeBots.put(current.minutes(), current.robots().geode());

            if (current.robots().geode() < geodeBots.get(current.minutes()) - 1)
                continue;

            var affordable = affordable(current, maxMinutes);
            affordable.stream()
                    .filter(state -> state.prod().geode() >= maxGeodes.computeIfAbsent(state.minutes(), k -> state).prod().geode())
                    .filter(history::add)
                    .forEach(queue::offer);
        }

        if (maxGeodes.containsKey(maxMinutes))
            System.out.println("MAX: " + maxGeodes.get(maxMinutes));

        return maxGeodes.containsKey(maxMinutes) ? Optional.of(maxGeodes.get(maxMinutes)) : Optional.empty();
    }
}
