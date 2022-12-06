package com.putoet.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacketTest {
    @Test
    void startOfPacket() {
        assertEquals(7, new Packet("mjqjpqmgbljsphdztnvjfqwrcgsmlb").startOfPacket(4));
        assertEquals(5, new Packet("bvwbjplbgvbhsrlpgdmjqwftvncz").startOfPacket(4));
        assertEquals(6, new Packet("nppdvjthqldpwncqszvftbrmjlhg").startOfPacket(4));

        assertEquals(19, new Packet("mjqjpqmgbljsphdztnvjfqwrcgsmlb").startOfPacket(14));
        assertEquals(29, new Packet("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").startOfPacket(14));
    }
}