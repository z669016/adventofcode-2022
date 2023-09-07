package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.*;
import java.util.stream.IntStream;

public class Day13 {
    public static void main(String[] args) {
        final var pairs = SignalPacketPair.of(ResourceLines.list("/day13.txt"));

        Timer.run(() -> System.out.println("The sum of the indices of those pairs is: " + rightOrdered(pairs)));
        Timer.run(() -> System.out.println("The decoder key is: " + decoderKey(pairs)));
    }

    static int rightOrdered(List<SignalPacketPair> pairs) {
        return IntStream.range(0, pairs.size())
                .filter(i -> pairs.get(i).compared() != Order.NOT_IN_ORDER)
                .map(i -> i + 1)
                .sum();
    }

    static int decoderKey(List<SignalPacketPair> pairs) {
        final var packets = new ArrayList<>(pairs.stream()
                .map(SignalPacketPair::asList)
                .flatMap(List::stream)
                .toList()
        );

        final var two = SignalPacket.from("[[2]]");
        final var six = SignalPacket.from("[[6]]");
        packets.add(two);
        packets.add(six);

        packets.sort(Comparator.naturalOrder());

        final var twoIndex = indexOf(packets, two);
        final var sixIndex = indexOf(packets, six);

        return twoIndex * sixIndex;
    }

    private static int indexOf(List<SignalPacket> packets, SignalPacket packet) {
        return IntStream.range(0, packets.size())
                .filter(i -> packet.equals(packets.get(i)))
                .map(i -> i + 1)
                .findAny()
                .orElseThrow();
    }
}
