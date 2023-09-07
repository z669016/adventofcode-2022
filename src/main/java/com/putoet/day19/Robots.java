package com.putoet.day19;

record Robots(int ore, int clay, int obsidian, int geode) {
    public static final Robots init = new Robots(1, 0, 0, 0);

    public Robots add(RobotType type) {
        return switch (type) {
            case ORE_ROBOT -> new Robots(ore + 1, clay, obsidian, geode);
            case CLAY_ROBOT -> new Robots(ore, clay + 1, obsidian, geode);
            case OBSIDIAN_ROBOT -> new Robots(ore, clay, obsidian + 1, geode);
            case GEODE_ROBOT -> new Robots(ore, clay, obsidian, geode + 1);
        };
    }
}
