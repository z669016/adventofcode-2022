package com.putoet.day19;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

record Robot(@NotNull RobotType type) implements Supplier<Prod> {
    public static final Prod PROD_ORE = new Prod(1, 0, 0, 0);
    public static final Prod PROD_CLAY = new Prod(0, 1, 0, 0);
    public static final Prod PROD_OBSIDIAN = new Prod(0, 0, 1, 0);
    public static final Prod PROD_GEODE = new Prod(0, 0, 0, 1);

    @Override
    public Prod get() {
        return switch (type) {
            case ORE_ROBOT -> PROD_ORE;
            case CLAY_ROBOT -> PROD_CLAY;
            case OBSIDIAN_ROBOT -> PROD_OBSIDIAN;
            case GEODE_ROBOT -> PROD_GEODE;
        };
    }
}
