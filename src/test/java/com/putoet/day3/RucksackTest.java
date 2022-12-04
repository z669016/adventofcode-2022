package com.putoet.day3;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RucksackTest {
    private final static RucksackItem a = new RucksackItem('a');
    private final static  RucksackItem b = new RucksackItem('b');
    private final static  RucksackItem c = new RucksackItem('c');
    private final static  RucksackItem A = new RucksackItem('A');
    private final static  RucksackItem C = new RucksackItem('C');

    @Test
    void shared() {
        final var rucksack = Rucksack.of("abcAbC");
        assertEquals(b, rucksack.shared());
    }

    @Test
    void allItems() {
        final var rucksack = Rucksack.of("abcAbC");
        final var allItems = rucksack.allItems();
        assertEquals(5, allItems.size());
        assertTrue(rucksack.allItems().containsAll(Set.of(a,b,c,A,C)));
    }
}