package com.putoet.day3;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class RucksackItems {
    public static Set<RucksackItem> of(@NotNull String items) {
        return items.chars().mapToObj(c -> new RucksackItem((char) c)).collect(Collectors.toSet());
    }

    public static List<Set<RucksackItem>> of2(@NotNull String items) {
        assert items.length() % 2 == 0;

        final var size = items.length() / 2;
        return List.of( of(items.substring(0, size)), of(items.substring(size)));
    }
}
