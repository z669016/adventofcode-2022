package com.putoet.day13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignalPacketTest {
    @Test
    void compareTo() {
        assertEquals(Order.IN_ORDER, new SignalPacket(1, 1, 3, 1, 1).test(new SignalPacket(1, 1, 5, 1, 1)));
        assertEquals(Order.IN_ORDER, new SignalPacket(List.of(1), List.of(2, 3, 4)).test(new SignalPacket(List.of(1), 4)));
        assertEquals(Order.NOT_IN_ORDER, new SignalPacket(9).test(new SignalPacket(List.of(List.of(8, 7, 6)))));
        assertEquals(Order.IN_ORDER, new SignalPacket(List.of(4, 4), 4, 4).test(new SignalPacket(List.of(4, 4), 4, 4, 4)));
        assertEquals(Order.NOT_IN_ORDER, new SignalPacket(7, 7, 7, 7).test(new SignalPacket(7, 7, 7)));
        assertEquals(Order.IN_ORDER, new SignalPacket(List.of()).test(new SignalPacket(3)));
        assertEquals(Order.NOT_IN_ORDER, new SignalPacket(List.of(List.of(List.of()))).test(new SignalPacket(List.of(List.of()))));

        assertEquals(Order.NOT_IN_ORDER,
                new SignalPacket(1, List.of(2, List.of(3, List.of(4, List.of(5, 6, 7)))), 8, 9).test(
                        new SignalPacket(1, List.of(2, List.of(3, List.of(4, List.of(5, 6, 0)))), 8, 9))
                );
    }

    @Test
    void from() {
        assertEquals(new SignalPacket(1), SignalPacket.from("[1]"));
        assertEquals(new SignalPacket(1, 1, 3, 1, 1), SignalPacket.from("[1,1,3,1,1]"));
        assertEquals(new SignalPacket(List.of(1), List.of(2, 3, 4)), SignalPacket.from("[[1],[2,3,4]]"));
        assertEquals(new SignalPacket(1, List.of(2, List.of(3, List.of(4, List.of(5, 6, 7)))), 8, 9), SignalPacket.from("[1,[2,[3,[4,[5,6,7]]]],8,9]"));
    }
}