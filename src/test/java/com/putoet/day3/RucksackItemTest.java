package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RucksackItemTest {

    @Test
    void priority() {
        assertEquals(2, new RucksackItem('b').priority());
        assertEquals(28, new RucksackItem('B').priority());
    }
}