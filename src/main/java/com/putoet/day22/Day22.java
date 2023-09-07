package com.putoet.day22;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class Day22 {
    public static void main(String[] args) {
        Timer.run(Day22::part1);
        Timer.run(Day22::part2);
    }

    static void part1() {
        final var grid = GridUtils.of(boardInput());
        final var board = new StrangeBoard(grid);
        final var password = PasswordResolver.resolve(board, new BoardStrategy(grid), pathInput());
        System.out.println("The final password is " + password);
    }

    static void part2() {
        final var grid = GridUtils.of(boardInput());
        final var board = new StrangeBoard(grid);
        final var password = PasswordResolver.resolve(board, new CubeStrategy(grid), pathInput());
        System.out.println("The final password on the cube is " + password);
    }

    static List<String> boardInput() {
        var maxLength = 0;
        final var board = new ArrayList<String>();
        for (var line : ResourceLines.list("/day22.txt")) {
            if (line.isEmpty())
                break;

            maxLength = Math.max(maxLength, line.length());
            board.add(line);
        }

        for (var i = 0; i < board.size(); i++)
            board.set(i, board.get(i) + " ".repeat(maxLength - board.get(i).length()));

        return board;
    }

    static String pathInput() {
        final var input = ResourceLines.list("/day22.txt");
        return input.get(input.size() - 1);
    }
}
