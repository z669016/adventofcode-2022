package com.putoet.day8;

import java.util.Arrays;

record Forrest(char[][] forrest) {
    public int visibleTrees() {
        var count = 0;
        for (var y = 0; y < forrest.length; y++) {
            for (var x = 0; x < forrest[y].length; x++) {
                count += isVisible(x, y) ? 1 : 0;
            }
        }
        return count;
    }

    private boolean isVisible(int x, int y) {
        final var height = forrest[y][x];

        var higherUp = false;
        for (var dy = y - 1; dy >= 0; dy--) {
            if (forrest[dy][x] >= height) {
                higherUp = true;
                break;
            }
        }

        var higherDown = false;
        for (var dy = y + 1; dy < forrest.length; dy++) {
            if (forrest[dy][x] >= height) {
                higherDown = true;
                break;
            }
        }

        var higherLeft = false;
        for (var dx = x - 1; dx >= 0; dx--) {
            if (forrest[y][dx] >= height) {
                higherLeft = true;
                break;
            }
        }

        var higherRight = false;
        for (var dx = x + 1; dx < forrest[y].length; dx++) {
            if (forrest[y][dx] >= height) {
                higherRight = true;
                break;
            }
        }

        return !(higherUp && higherDown && higherLeft && higherRight);
    }

    public long highestScenicScore() {
        final var score = new long[forrest.length][forrest[0].length];

        for (var y = 0; y < forrest.length; y++) {
            for (var x = 0; x < forrest[y].length; x++) {
                score[y][x] = scenicScoreFor(x, y);
            }
        }

        return Arrays.stream(score)
                .flatMapToLong(Arrays::stream)
                .max()
                .orElseThrow();
    }

    private long scenicScoreFor(int x, int y) {
        final var height = forrest[y][x];

        var lowerUp = 0L;
        for (var dy = y - 1; dy >= 0; dy--) {
            lowerUp++;
            if (forrest[dy][x] >= height) {
                break;
            }
        }

        var lowerDown = 0L;
        for (var dy = y + 1; dy < forrest.length; dy++) {
            lowerDown++;
            if (forrest[dy][x] >= height) {
                break;
            }
        }

        var lowerLeft = 0L;
        for (var dx = x - 1; dx >= 0; dx--) {
            lowerLeft++;
            if (forrest[y][dx] >= height) {
                break;
            }
        }

        var lowerRight = 0L;
        for (var dx = x + 1; dx < forrest[y].length; dx++) {
            lowerRight++;
            if (forrest[y][dx] >= height) {
                break;
            }
        }

        return lowerLeft * lowerRight * lowerUp * lowerDown;
    }
}
