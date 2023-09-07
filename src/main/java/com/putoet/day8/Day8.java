package com.putoet.day8;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day8 {
    public static void main(String[] args) {
        final Forrest forrest = new Forrest(GridUtils.of(ResourceLines.list("/day8.txt")));

        Timer.run(() -> System.out.println("The number of visible trees is " + forrest.visibleTrees()));
        Timer.run(() -> System.out.println("The max scenic score is " + forrest.highestScenicScore()));
    }
}
