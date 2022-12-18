package com.putoet.day17;

import java.util.*;

public class CaveSequence extends Cave{
    private final int from;
    private final int size;

    private Sequence sequence;

    public record Sequence(int start, List<Integer> list) {}

    public CaveSequence(Push push, int from, int size) {
        super(push);
        this.from = from;
        this.size = size;
    }

    public Sequence sequence() {
        return sequence;
    }

    @Override
    public void run() {
        final List<Integer> list = new ArrayList<>();
        int start = -1;

        for (int i = 0; i < from + size; i++) {
            var value = drop(rocks().get(), push());
            if (i == from - 1)
                start = highestRock();

            if (i >= from)
                list.add(value);
        }

        sequence = new Sequence(start, list);
    }
}
