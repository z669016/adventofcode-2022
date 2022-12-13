package com.putoet.day13;

import java.util.ArrayList;
import java.util.List;

public record SignalPacketPair(SignalPacket first, SignalPacket second) {
    public SignalPacketPair {
        assert first != null;
        assert second != null;
    }

    public static List<SignalPacketPair> from(List<String> input) {
        final List<SignalPacketPair> pairs = new ArrayList<>();
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
