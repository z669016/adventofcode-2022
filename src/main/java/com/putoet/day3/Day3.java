package com.putoet.day3;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day3 extends Day {
    private final List<Rucksack> rucksacks;

    public Day3(String[] args) {
        super(args);

        rucksacks = ResourceLines.stream("/day3.txt")
                .map(Rucksack::of)
                .toList();
    }

    public static void main(String[] args) {
        final var day = new Day3(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The sum of shared priorities is " + sharedPrioritySum());
    }

    public int sharedPrioritySum() {
        return rucksacks.stream()
                .map(Rucksack::shared)
                .mapToInt(RucksackItem::priority)
                .sum();
    }

    @Override
    public void part2() {
        System.out.println("The sum of batch priorities is " + batchPrioritySum());
    }

    public int batchPrioritySum() {
       var sum = 0;

       for (var i = 0; i < rucksacks.size() - 2; i += 3) {
           sum += rucksacks.get(i).shared(rucksacks.get(i + 1), rucksacks.get(i + 2)).priority();
       }

       return sum;
    }
}
