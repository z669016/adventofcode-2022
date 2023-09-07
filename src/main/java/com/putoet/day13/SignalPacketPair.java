package com.putoet.day13;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

record SignalPacketPair(@NotNull SignalPacket first, @NotNull SignalPacket second) {
    public static List<SignalPacketPair> of(@NotNull List<String> input) {
        final var pairs = new ArrayList<SignalPacketPair>();
        for (var i = 0; i < input.size(); i += 3) {
            pairs.add(new SignalPacketPair(SignalPacket.from(input.get(i)), SignalPacket.from(input.get(i+1))));
        }

        return pairs;
    }

    public List<SignalPacket> asList() {
        return List.of(first, second);
    }

    public Order compared() {
        return first.test(second);
    }
}
