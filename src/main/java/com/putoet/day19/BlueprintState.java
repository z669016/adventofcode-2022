package com.putoet.day19;

public record BlueprintState(int blueprint, int minutes, Prod prod, Robots robots) {
    public BlueprintState {
        assert prod != null;
        assert robots != null;
    }

    public BlueprintState next() {
        return new BlueprintState(blueprint,
                minutes + 1,
                prod.add(robots),
                robots);
    }

    public int qualityLevel() {
        return blueprint * prod().geode();
    }

    public static BlueprintState init(Blueprint blueprint) {
        return new BlueprintState(blueprint.id(),
                0,
                Prod.init,
                Robots.init);
    }
}
