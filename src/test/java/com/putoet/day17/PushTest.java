package com.putoet.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {
    @Test
    void get() {
        final var push = new Push("<><");
        assertEquals(Push.LEFT, push.get());
        assertEquals(Push.RIGHT, push.get());
        assertEquals(Push.LEFT, push.get());
        assertEquals(Push.LEFT, push.get());
        assertEquals(Push.RIGHT, push.get());
        assertEquals(Push.LEFT, push.get());
        assertEquals(Push.LEFT, push.get());
        assertEquals(Push.RIGHT, push.get());
    }
}