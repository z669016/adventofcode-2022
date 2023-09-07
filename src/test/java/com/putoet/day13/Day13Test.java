package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {
    @Test
    void rightOrdered() {
        final var pairs = SignalPacketPair.of(ResourceLines.list("/day13.txt"));
        assertEquals(13, Day13.rightOrdered(pairs));
    }

    @Test
    void decoderKey() {
        final var pairs = SignalPacketPair.of(ResourceLines.list("/day13.txt"));
        assertEquals(140, Day13.decoderKey(pairs));
    }
}