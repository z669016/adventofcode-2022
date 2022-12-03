package com.putoet.day3;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public record Rucksack(List<Set<RucksackItem>> items) {
    public Rucksack {
        assert items != null;
        assert items.size() == 2;
    }

    public RucksackItem shared() {
        final var shared = Sets.intersection(items.get(0), items.get(1));
        return shared.stream().findFirst().orElseThrow();
    }

    public RucksackItem shared(Rucksack r2, Rucksack r3) {
        var shared = Sets.intersection(this.allItems(), r2.allItems());
        shared = Sets.intersection(shared, r3.allItems());
        return shared.stream().findFirst().orElseThrow();
    }

    public Set<RucksackItem> allItems() {
        return Sets.union(items.get(0), items.get(1));
    }

    public static Rucksack of(String items) {
        return new Rucksack(RucksackItems.of2(items));
    }
}
