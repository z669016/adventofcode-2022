package com.putoet.day3;

import com.google.common.collect.Sets;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

record Rucksack(@NotNull List<Set<RucksackItem>> items) {
    public Rucksack {
        assert items.size() == 2;
    }

    public RucksackItem shared() {
        final var shared = Sets.intersection(items.get(0), items.get(1));
        return shared.stream().findFirst().orElseThrow();
    }

    public RucksackItem shared(@NotNull Rucksack r2, @NotNull Rucksack r3) {
        var shared = Sets.intersection(this.allItems(), r2.allItems());
        shared = Sets.intersection(shared, r3.allItems());
        return shared.stream().findFirst().orElseThrow();
    }

    public Set<RucksackItem> allItems() {
        return Sets.union(items.get(0), items.get(1));
    }

    public static Rucksack of(@NotNull String items) {
        return new Rucksack(RucksackItems.of2(items));
    }
}
