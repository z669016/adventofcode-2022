package com.putoet.day10;

import com.putoet.grid.GridUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Device {
    private int x;
    private int cycle;
    private List<SignalStrength> signalStrengths;

    private char[][] crt;

    public Device() {
        x = 1;
    }

    public int x() {
        return x;
    }

    public void x(int value) {
        x = value;
    }

    public void cycle() {
        draw();
        cycle++;

        if ((cycle - 20) % 40 == 0)
            signalStrengths.add(signalStrength(cycle, x));
    }

    private void draw() {
        final int spriteCentre = x % 40;
        final int y = line(cycle);
        final int x = column(cycle);

        crt[y][x] = (x >= spriteCentre - 1 && x <= spriteCentre + 1) ? '#' : '.';
    }

    private static int line(int cycle) {
        return cycle / 40;
    }

    private static int column(int cycle) {
        return cycle % 40;
    }

    private void init() {
        x = 1;
        signalStrengths = new ArrayList<>();
        cycle = 0;
        crt = GridUtils.of(0, 40, 0, 6, ' ');
    }

    public List<SignalStrength> process(List<Instruction> program, Predicate<Integer> stop) {
        init();

        int ip = 0;
        while (!stop.test(cycle)) {
            ip += program.get(ip).exec(this);
            if (ip >= program.size())
                ip = 0;
        }

        return signalStrengths;
    }

    private static SignalStrength signalStrength(int cycle, long x) {
        return new SignalStrength(cycle, cycle * x);
    }

    public String crt() {
        final StringBuilder sb = new StringBuilder();
        for (var row : crt) {
            for (var c : row)
                sb.append(c);
            sb.append("\n");
        }

        return sb.toString();
    }
}
