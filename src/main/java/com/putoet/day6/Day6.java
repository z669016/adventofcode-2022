package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day6 {
    public static void main(String[] args) {
        final var packet = new Packet(ResourceLines.line("/day6.txt"));

        Timer.run(() -> System.out.println("The start of packet is " + packet.startOfPacket(4)));
        Timer.run(() -> System.out.println("The start of packet is " + packet.startOfPacket(14)));
    }
}
