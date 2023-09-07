package com.putoet.day6;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

record Packet(@NotNull String sequence) {
    public int startOfPacket(int size) {
        assert sequence.length() > size;

        var c = new int[size];
        for (var i = 0; i < size - 1; i++) {
            c[i] = sequence.charAt(i);
        }

        for (var i = size - 1; i < sequence.length(); i++) {
            c[size - 1] = sequence.charAt(i);

            if (Arrays.stream(c).boxed().collect(Collectors.toSet()).size() == size) {
                return i + 1;
            }

            System.arraycopy(c, 1, c, 0, size - 1);
        }

        throw new IllegalStateException("No start of packet found on '" + sequence + "'");
    }
}
