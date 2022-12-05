package com.putoet.day5;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.ArrayList;
import java.util.List;

public class Day5 extends Day {
    //    [P]     [L]         [T]
    //    [L]     [M] [G]     [G]     [S]
    //    [M]     [Q] [W]     [H] [R] [G]
    //    [N]     [F] [M]     [D] [V] [R] [N]
    //    [W]     [G] [Q] [P] [J] [F] [M] [C]
    //    [V] [H] [B] [F] [H] [M] [B] [H] [B]
    //    [B] [Q] [D] [T] [T] [B] [N] [L] [D]
    //    [H] [M] [N] [Z] [M] [C] [M] [P] [P]
    //     1   2   3   4   5   6   7   8   9

    private Cargo cargo;
    private final List<Instruction> instructions = new ArrayList<>();


    public Day5(String[] args, Cargo cargo) {
        super(args);

        this.cargo = cargo;

        boolean layout = true;
        final var input = ResourceLines.list("/day5.txt");
        for (var line : input) {
            if (layout) {
                if (line.length() == 0)
                    layout = false;
            } else {
                instructions.add(Instruction.of(line));
            }
        }
    }

    public static void main(String[] args) {
        final var day = new Day5(args, initCargo());
        day.challenge();
    }

    public static Cargo initCargo() {
        return new Cargo(List.of(
                new CrateStack("HBVWNMLP"),
                new CrateStack("MQH"),
                new CrateStack("NDBGFQML"),
                new CrateStack("ZTFQMWG"),
                new CrateStack("MTHP"),
                new CrateStack("CBMJDHGT"),
                new CrateStack("NMBFVR"),
                new CrateStack("PLHMRGS"),
                new CrateStack("PDBCN")
        ));
    }

    @Override
    public void part1() {
        System.out.println("Top crates after processing are: " + move());
    }

    public String move() {
        instructions.forEach(instruction -> cargo.move(instruction.from(), instruction.to(), instruction.count()));
        return cargo.top();
    }

    @Override
    public void part2() {
        System.out.println("Top crates after processing with the 9001 crane are: " + move2());
    }

    private String move2() {
        cargo = initCargo();
        instructions.forEach(instruction -> cargo.move2(instruction.from(), instruction.to(), instruction.count()));
        return cargo.top();
    }
}
