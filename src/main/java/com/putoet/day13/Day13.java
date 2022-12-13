package com.putoet.day13;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.*;
import java.util.stream.IntStream;

public class Day13 extends Day {
    private final List<SignalPacketPair> pairs;

    public Day13(String[] args) {
        super(args);

        pairs = SignalPacketPair.from(ResourceLines.list("/day13.txt"));
    }

    public static void main(String[] args) {
        final var day = new Day13(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The sum of the indices of those pairs is: " + rightOrdered());
    }

    public int rightOrdered() {
        return IntStream.range(0, pairs.size())
                .filter(i -> pairs.get(i).compared() != Order.NOT_IN_ORDER)
                .map(i -> i + 1)
                .sum();
    }

    @Override
    public void part2() {
        System.out.println("The decoder key is: " + decoderKey());
    }

    public int decoderKey() {
        final List<SignalPacket> packets = new ArrayList<>(pairs.stream()
                .map(SignalPacketPair::asList)
                .flatMap(List::stream)
                .toList()
        );

        final var two = SignalPacket.from("[[2]]");
        final var six = SignalPacket.from("[[6]]");
        packets.add(two);
        packets.add(six);

        packets.sort(Comparator.naturalOrder());

        int twoIndex = indexOf(packets, two);
        int sixIndex = indexOf(packets, six);

        return twoIndex * sixIndex;
    }

    private static int indexOf(List<SignalPacket> packets, SignalPacket packet) {
        return IntStream.range(0, packets.size())
                .filter(i -> packet.equals(packets.get(i)))
                .map(i -> i + 1)
                .findAny().orElseThrow();
    }
}
