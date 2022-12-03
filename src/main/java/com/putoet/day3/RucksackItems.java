package com.putoet.day3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RucksackItems {
    public static Set<RucksackItem> of(String items) {
        assert items != null;

        return items.chars().mapToObj(c -> new RucksackItem((char) c)).collect(Collectors.toSet());
    }

    public static List<Set<RucksackItem>> of2(String items) {
        assert items != null;
        assert items.length() % 2 == 0;

        final int size = items.length() / 2;
        return List.of( of(items.substring(0, size)), of(items.substring(size)));
    }
}
