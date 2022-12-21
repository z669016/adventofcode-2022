package com.putoet.day19;

public record Prod(int ore, int clay, int obsidian, int geode) {
    public static final Prod init = new Prod(0, 0, 0, 0);

    public Prod add(Robots robots) {
        return new Prod(
                ore + robots.ore(),
                clay + robots.clay(),
                obsidian + robots.obsidian(),
                geode + robots.geode()
        );
    }

    public Prod sub(RobotCost cost) {
        return new Prod(ore - cost.ore(), clay - cost.clay(), obsidian - cost.obsidian(), geode);
    }
}
