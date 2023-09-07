package com.putoet.day19;

enum RobotType {
    ORE_ROBOT,
    CLAY_ROBOT,
    OBSIDIAN_ROBOT,
    GEODE_ROBOT;

    public int key() {
        return switch (this) {
            case ORE_ROBOT -> 0;
            case CLAY_ROBOT -> 1;
            case OBSIDIAN_ROBOT -> 2;
            case GEODE_ROBOT -> 3;
        };
    }
}
