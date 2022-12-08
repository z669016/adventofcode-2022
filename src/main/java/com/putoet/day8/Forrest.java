package com.putoet.day8;

import java.util.Arrays;

public class Forrest {
    private final char[][] forrest;

    public Forrest(char[][] forrest) {
        assert forrest != null;

        this.forrest = forrest;
    }

    public int visibleTrees() {
        int count = 0;
        for (int y = 0; y < forrest.length; y++) {
            for (int x = 0; x < forrest[y].length; x++) {
                count += isVisible(x, y) ? 1 : 0;
            }
        }
        return count;
    }

    private boolean isVisible(int x, int y) {
        final int height = forrest[y][x];

        boolean higherUp = false;
        for (int dy = y - 1; dy >= 0; dy--) {
            if (forrest[dy][x] >= height) {
                higherUp = true;
                break;
            }
        }

        boolean higherDown = false;
        for (int dy = y + 1; dy < forrest.length; dy++) {
            if (forrest[dy][x] >= height) {
                higherDown = true;
                break;
            }
        }

        boolean higherLeft = false;
        for (int dx = x - 1; dx >= 0; dx--) {
            if (forrest[y][dx] >= height) {
                higherLeft = true;
                break;
            }
        }

        boolean higherRight = false;
        for (int dx = x + 1; dx < forrest[y].length; dx++) {
            if (forrest[y][dx] >= height) {
                higherRight = true;
                break;
            }
        }

        return !(higherUp && higherDown && higherLeft && higherRight);
    }

    public long highestScenicScore() {
        final long[][] score = new long[forrest.length][forrest[0].length];

        for (int y = 0; y < forrest.length; y++) {
            for (int x = 0; x < forrest[y].length; x++) {
                if (y == 2 && x == 3)
                    System.out.println("found");

                score[y][x] = scenicScoreFor(x, y);
            }
        }

        return Arrays.stream(score)
                .flatMapToLong(Arrays::stream)
                .max()
                .orElseThrow();
    }

    private long scenicScoreFor(int x, int y) {
        final int height = forrest[y][x];

        long lowerUp = 0L;
        for (int dy = y - 1; dy >= 0; dy--) {
            lowerUp++;
            if (forrest[dy][x] >= height) {
                break;
            }
        }

        long lowerDown = 0L;
        for (int dy = y + 1; dy < forrest.length; dy++) {
            lowerDown++;
            if (forrest[dy][x] >= height) {
                break;
            }
        }

        long lowerLeft = 0L;
        for (int dx = x - 1; dx >= 0; dx--) {
            lowerLeft++;
            if (forrest[y][dx] >= height) {
                break;
            }
        }

        long lowerRight = 0L;
        for (int dx = x + 1; dx < forrest[y].length; dx++) {
            lowerRight++;
            if (forrest[y][dx] >= height) {
                break;
            }
        }

        return lowerLeft * lowerRight * lowerUp * lowerDown;
    }
}
