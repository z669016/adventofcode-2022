package com.putoet.day19;

public record Prod(int ore, int clay, int obsidian, int geode) {
    public static final Prod init = new Prod(0, 0, 0, 0);

    public Prod add(Robots robots, int maxOre, int maxClay, int maxObsidian) {
        return new Prod(
                Math.min(ore + robots.ore(), maxOre),
                Math.min(clay + robots.clay(), maxClay),
                Math.min(obsidian + robots.obsidian(), maxObsidian),
                geode + robots.geode()
        );
    }

    public Prod sub(RobotCost cost) {
        return new Prod(ore - cost.ore(), clay - cost.clay(), obsidian - cost.obsidian(), geode);
    }
}
