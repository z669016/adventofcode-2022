package com.putoet.day19;

import org.jetbrains.annotations.NotNull;
import org.jheaps.array.BinaryArrayHeap;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

record Blueprint(int id, @NotNull List<RobotCost> costs, int maxOre, int maxClay, int maxObsidian) {

    public Blueprint(int id, @NotNull List<RobotCost> costs) {
        this(id, costs,
                costs.stream().mapToInt(RobotCost::ore).max().orElseThrow(),
                costs.stream().mapToInt(RobotCost::clay).max().orElseThrow(),
                costs.stream().mapToInt(RobotCost::obsidian).max().orElseThrow());
    }

    public Blueprint {
        assert costs.size() == 4;
    }

    public List<BlueprintState> affordable(@NotNull BlueprintState state, int maxMinutes) {
        if (canAfford(RobotType.GEODE_ROBOT, state.prod())) {
            return List.of(manufacture(RobotType.GEODE_ROBOT, state, maxMinutes));
        }

        final var affordable = new ArrayList<BlueprintState>();
        if (canAfford(RobotType.ORE_ROBOT, state.prod()) && state.robots().ore() <= maxOre)
            affordable.add(manufacture(RobotType.ORE_ROBOT, state, maxMinutes));

        if (canAfford(RobotType.CLAY_ROBOT, state.prod()) && state.robots().clay() <= maxClay)
            affordable.add(manufacture(RobotType.CLAY_ROBOT, state, maxMinutes));

        if (canAfford(RobotType.OBSIDIAN_ROBOT, state.prod()) && state.robots().obsidian() <= maxObsidian)
            affordable.add(manufacture(RobotType.OBSIDIAN_ROBOT, state, maxMinutes));

        affordable.add(next(state, maxMinutes));

        return affordable;
    }

    public boolean canAfford(@NotNull RobotType type, @NotNull Prod prod) {
        final var cost = costs.get(type.key());

        return prod.ore() >= cost.ore() &&
               prod.clay() >= cost.clay() &&
               prod.obsidian() >= cost.obsidian();
    }

    private BlueprintState manufacture(RobotType type, BlueprintState state, int maxMinutes) {
        final var maxOre = (maxMinutes - state.minutes()) * this.maxOre;
        final var maxClay = (maxMinutes - state.minutes()) * this.maxClay;
        final var maxObsidian = (maxMinutes - state.minutes()) * this.maxObsidian;

        return new BlueprintState(
                state.blueprint(),
                state.minutes() + 1,
                state.prod().add(state.robots(), maxOre, maxClay, maxObsidian).sub(costs.get(type.key())),
                state.robots().add(type)
        );
    }

    public BlueprintState next(@NotNull BlueprintState state, int maxMinutes) {
        final var maxOre = (maxMinutes - state.minutes()) * this.maxOre;
        final var maxClay = (maxMinutes - state.minutes()) * this.maxClay;
        final var maxObsidian = (maxMinutes - state.minutes()) * this.maxObsidian;

        return new BlueprintState(state.blueprint(),
                state.minutes() + 1,
                state.prod().add(state.robots(), maxOre, maxClay, maxObsidian),
                state.robots());
    }

    public static final Pattern INPUT_PATTERN = Pattern.compile(
            "Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");

    public static Blueprint of(@NotNull String line) {
        final var matcher = INPUT_PATTERN.matcher(line);
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

    public static Map<Integer, Blueprint> of(@NotNull List<String> lines) {
        return lines.stream()
                .map(Blueprint::of)
                .collect(Collectors.toMap(Blueprint::id, blueprint -> blueprint));
    }

    public Optional<BlueprintState> max() {
        return max(24);
    }

    public Optional<BlueprintState> max(int maxMinutes) {
        final var history = new HashSet<BlueprintState>();
        final var maxGeodes = new HashMap<Integer, BlueprintState>();
        final var geodeBots = new HashMap<Integer, Integer>();
        final var queue = new BinaryArrayHeap<>(
                Comparator.comparing((BlueprintState state) -> state.prod().geode())
                        .thenComparing((BlueprintState state) -> state.prod().obsidian())
                        .reversed()
        );

        var init = BlueprintState.init(this);
        maxGeodes.put(0, init);
        geodeBots.put(0, 0);

        queue.insert(init);
        while (!queue.isEmpty()) {
            var current = queue.deleteMin();

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
                    .filter(state -> state.prod().geode() >= maxGeodes.computeIfAbsent(state.minutes(), k -> state).prod().geode() - 1)
                    .filter(history::add)
                    .forEach(queue::insert);
        }

        if (maxGeodes.containsKey(maxMinutes))
            System.out.println("MAX: " + maxGeodes.get(maxMinutes));

        return maxGeodes.containsKey(maxMinutes) ? Optional.of(maxGeodes.get(maxMinutes)) : Optional.empty();
    }
}
