package com.putoet.day6;

import java.util.Arrays;
import java.util.stream.Collectors;

public record Packet(String sequence) {
    public Packet {
        assert sequence != null;
    }

    public int startOfPacket(int size) {
        assert sequence.length() > size;

        Integer[] c = new Integer[size];
        for (var i = 0; i < size - 1; i++) {
            c[i] = (int) sequence.charAt(i);
        }

        for (var i = size - 1; i < sequence.length(); i++) {
            c[size - 1] = (int) sequence.charAt(i);

            if (Arrays.stream(c).collect(Collectors.toSet()).size() == size) {
                return i + 1;
            }

            System.arraycopy(c, 1, c, 0, size - 1);
        }

        throw new IllegalStateException("No start of packet found on '" + sequence + "'");
    }
}
