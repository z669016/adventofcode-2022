package com.putoet.day18;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

public class Day18 extends Day {
    private final Droplet droplet;

    public Day18(String[] args) {
        super(args);

        final var input = ResourceLines.list("/day18.txt");
        droplet = Droplet.from(input);
    }

    public static void main(String[] args) {
        final var day = new Day18(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The surface area of your scanned lava droplet is " + droplet.connected());
    }

    @Override
    public void part2() {
        System.out.println("The exterior surface area of your scanned lava droplet is " + droplet.exterior());
    }
}
