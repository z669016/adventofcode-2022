package com.putoet.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Blueprint {
    private final int id;
    private final List<RobotCost> costs;

    private final Function<BlueprintState, BlueprintState> optimize;

    public Blueprint(int id, List<RobotCost> costs) {
        assert costs != null;
        assert costs.size() == 4;

        this.id = id;
        this.costs = costs;

        final int maxOre = costs.stream().mapToInt(RobotCost::ore).max().orElseThrow();
        final int maxClay = costs.stream().mapToInt(RobotCost::clay).max().orElseThrow();
        final int maxObsidian = costs.stream().mapToInt(RobotCost::obsidian).max().orElseThrow();

        optimize = state -> new BlueprintState(
                state.blueprint(),
                state.minutes(),
                new Prod(
                        state.prod().ore() >= state.minutes() * maxOre ? state.minutes() * maxOre : state.prod().ore(),
                        state.prod().clay() >= state.minutes() * maxClay ? state.minutes() * maxClay : state.prod().clay(),
                        state.prod().obsidian() >= state.minutes() * maxObsidian ? state.minutes() * maxObsidian : state.prod().obsidian(),
                        state.prod().geode()
                ),
                state.robots());
    }


    public int id() {
        return id;
    }

    public int geodeCost() {
        return costs.get(RobotType.GEODE_ROBOT.key()).obsidian();
    }

    public List<BlueprintState> affordable(BlueprintState state) {
        if (canAfford(RobotType.GEODE_ROBOT, state.prod())) {
            return List.of(optimize.apply(manufacture(RobotType.GEODE_ROBOT, state)));
        }
//
//        if (canAfford(RobotType.OBSIDIAN_ROBOT, prod)) {
//            return List.of(optimize.apply(manufacture(RobotType.OBSIDIAN_ROBOT, state)));
//        }

        final List<BlueprintState> affordable = new ArrayList<>();
        if (state.robots().obsidian() == 0 ||
            ((24 - state.minutes()) * state.robots().obsidian() >= (geodeCost() - state.prod().obsidian()))) {
            for (var type : RobotType.values()) {
                if (canAfford(type, state.prod())) {
                    affordable.add(optimize.apply(manufacture(type, state)));
                }
            }
        }
        if (affordable.size() < 4)
            affordable.add(optimize.apply(state.next()));

        return affordable;
    }

    public boolean canAfford(RobotType type, Prod prod) {
        final RobotCost cost = costs.get(type.key());

        return prod.ore() >= cost.ore() &&
               prod.clay() >= cost.clay() &&
               prod.obsidian() >= cost.obsidian();
    }

    private BlueprintState manufacture(RobotType type, BlueprintState state) {
        return new BlueprintState(
                state.blueprint(),
                state.minutes() + 1,
                state.prod().add(state.robots()).sub(costs.get(type.key())),
                state.robots().add(type)
        );
    }

    public static final Pattern INPUT_PATTERN = Pattern.compile(
            "Blueprint (\\d+): Each ore robot costs (\\d+) ore. Each clay robot costs (\\d+) ore. Each obsidian robot costs (\\d+) ore and (\\d+) clay. Each geode robot costs (\\d+) ore and (\\d+) obsidian.");

    public static final Blueprint from(String line) {
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

    public static final Map<Integer,Blueprint> from(List<String> lines) {
        return lines.stream()
                .map(Blueprint::from)
                .collect(Collectors.toMap(Blueprint::id, blueprint -> blueprint));
    }
}
