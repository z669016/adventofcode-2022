package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day18 {
    public static void main(String[] args) {
        final var droplet = Droplet.of(ResourceLines.list("/day18.txt"));
        Timer.run(() ->
                System.out.println("The surface area of your scanned lava droplet is " + droplet.connected())
        );

        Timer.run(() ->
                System.out.println("The exterior surface area of your scanned lava droplet is " + droplet.exterior())
        );
    }
}
