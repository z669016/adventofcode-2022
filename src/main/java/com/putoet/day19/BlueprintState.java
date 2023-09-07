package com.putoet.day19;

import org.jetbrains.annotations.NotNull;

record BlueprintState(int blueprint, int minutes, @NotNull Prod prod, @NotNull Robots robots) {
    public int qualityLevel() {
        return blueprint * prod().geode();
    }

    public static BlueprintState init(@NotNull Blueprint blueprint) {
        return new BlueprintState(blueprint.id(),
                0,
                Prod.init,
                Robots.init);
    }
}
