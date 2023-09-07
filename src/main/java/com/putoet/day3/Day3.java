package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day3  {
    public static void main(String[] args) {
        final var rucksacks = ResourceLines.list("/day3.txt", Rucksack::of);

        Timer.run(() -> System.out.println("The sum of shared priorities is " + sharedPrioritySum(rucksacks)));
        Timer.run(() -> System.out.println("The sum of batch priorities is " + batchPrioritySum(rucksacks)));
    }

    static int sharedPrioritySum(List<Rucksack> rucksacks) {
        return rucksacks.stream()
                .map(Rucksack::shared)
                .mapToInt(RucksackItem::priority)
                .sum();
    }

    static int batchPrioritySum(List<Rucksack> rucksacks) {
       var sum = 0;

       for (var i = 0; i < rucksacks.size() - 2; i += 3) {
           sum += rucksacks.get(i).shared(rucksacks.get(i + 1), rucksacks.get(i + 2)).priority();
       }

       return sum;
    }
}
