package com.putoet.day22;

import com.putoet.day.Day;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;

import java.util.ArrayList;
import java.util.List;

public class Day22 extends Day {

    public static void main(String[] args) {
        final var day = new Day22();
        day.challenge();
    }

    @Override
    public void part1() {
        final char[][] grid = GridUtils.of(boardInput());
        final StrangeBoard board = new StrangeBoard(grid);
        final int password = PasswordResolver.resolve(board, new BoardStrategy(grid), pathInput());
        System.out.println("The final password is " + password);
    }

    @Override
    public void part2() {
        final char[][] grid = GridUtils.of(boardInput());
        final StrangeBoard board = new StrangeBoard(grid);
        final int password = PasswordResolver.resolve(board, new CubeStrategy(grid), pathInput());
        System.out.println("The final password on the cube is " + password);
    }

    public static List<String> boardInput() {
        int maxLength = 0;
        final List<String> board = new ArrayList<>();
        for (var line : ResourceLines.list("/day22.txt")) {
            if (line.length() == 0)
                break;

            maxLength = Math.max(maxLength, line.length());
            board.add(line);
        }

        for (var i = 0; i < board.size(); i++)
            board.set(i, board.get(i) + " ".repeat(maxLength - board.get(i).length()));

        return board;
    }

    public static String pathInput() {
        final List<String> input = ResourceLines.list("/day22.txt");
        return input.get(input.size() - 1);
    }
}
