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

    public List<BlueprintState> affordable(BlueprintState state) {
        if (canAfford(RobotType.GEODE_ROBOT, state.prod())) {
            return List.of(manufacture(RobotType.GEODE_ROBOT, state));
        }

        final List<BlueprintState> affordable = new ArrayList<>();
        if (state.robots().ore() <= maxOre && canAfford(RobotType.ORE_ROBOT, state.prod()))
            affordable.add(manufacture(RobotType.ORE_ROBOT, state));

        if (state.robots().clay() <= maxClay && canAfford(RobotType.CLAY_ROBOT, state.prod()))
            affordable.add(manufacture(RobotType.CLAY_ROBOT, state));

        if (state.robots().obsidian() <= maxObsidian && canAfford(RobotType.OBSIDIAN_ROBOT, state.prod()))
            affordable.add(manufacture(RobotType.OBSIDIAN_ROBOT, state));

        affordable.add(next(state));

        return affordable;
    }

    public boolean canAfford(RobotType type, Prod prod) {
        final RobotCost cost = costs.get(type.key());

        return prod.ore() >= cost.ore() &&
               prod.clay() >= cost.clay() &&
               prod.obsidian() >= cost.obsidian();
    }

    private BlueprintState manufacture(RobotType type, BlueprintState state) {
        final int maxOre = (24 - state.minutes()) * this.maxOre;
        final int maxClay = (24 - state.minutes()) * this.maxClay;
        final int maxObsidian = (24 - state.minutes()) * this.maxObsidian;

        return new BlueprintState(
                state.blueprint(),
                state.minutes() + 1,
                state.prod().add(state.robots(), maxOre, maxClay, maxObsidian).sub(costs.get(type.key())),
                state.robots().add(type)
        );
    }

    public BlueprintState next(BlueprintState state) {
        final int maxOre = (24 - state.minutes()) * this.maxOre;
        final int maxClay = (24 - state.minutes()) * this.maxClay;
        final int maxObsidian = (24 - state.minutes()) * this.maxObsidian;

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
        final Set<BlueprintState> history = new HashSet<>();
        final Map<Integer,BlueprintState> max = new HashMap<>();
        final Deque<BlueprintState> queue = new LinkedList<>();

        var init = BlueprintState.init(this);
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

            var affordable = affordable(current);
            affordable.stream()
                    .filter(state -> state.prod().geode() >= max.computeIfAbsent(state.minutes(), k -> state).prod().geode())
                    .filter(history::add)
                    .forEach(queue::offer);
        }

        if (max.containsKey(24))
            System.out.println("MAX: " + max.get(24));

        return max.containsKey(24) ? Optional.of(max.get(24)) : Optional.empty();
    }

}
