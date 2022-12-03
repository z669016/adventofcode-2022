package com.putoet.day3;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RucksackItemsTest {
    private final static RucksackItem a = new RucksackItem('a');
    private final static  RucksackItem b = new RucksackItem('b');
    private final static  RucksackItem c = new RucksackItem('c');
    private final static  RucksackItem A = new RucksackItem('A');
    private final static  RucksackItem B = new RucksackItem('B');
    private final static  RucksackItem C = new RucksackItem('C');

    @Test
    void of() {
        final Set<RucksackItem> items = RucksackItems.of("abc");
        assertEquals(3, items.size());
        assertTrue(items.containsAll(Set.of(a,b,c)));
    }

    @Test
    void of2() {
        final var items = RucksackItems.of2("abcABC");
        assertEquals(2, items.size());

        assertEquals(3, items.get(0).size());
        assertTrue(items.get(0).containsAll(Set.of(a,b,c)));

        assertEquals(3, items.get(1).size());
        assertTrue(items.get(1).containsAll(Set.of(A,B,C)));
    }
}