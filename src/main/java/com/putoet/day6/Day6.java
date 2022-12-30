package com.putoet.day6;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

public class Day6 extends Day {
    private final Packet packet;

    public Day6() {
        packet = new Packet(ResourceLines.line("/day6.txt"));
    }

    public static void main(String[] args) {
        final var day = new Day6();
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The start of packet is " + packet.startOfPacket(4));
    }

    @Override
    public void part2() {
        System.out.println("The start of packet is " + packet.startOfPacket(14));
    }
}
