package com.putoet.day17;

import com.putoet.grid.GridUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Rocks implements Supplier<Rock> {
    public static final Rock BAR = new Rock("bar", GridUtils.of(List.of("####")));
    public static final Rock CROSS = new Rock("cross", GridUtils.of(List.of(".#.", "###", ".#.")));
    public static final Rock HOOK = new Rock("hook", GridUtils.of(List.of("###", "..#", "..#")));
    public static final Rock LINE = new Rock("line", GridUtils.of(List.of("#", "#", "#", "#")));
    public static final Rock BLOCK = new Rock("block", GridUtils.of(List.of("##", "##")));

    private static final Rock[] ROCKS = {BAR, CROSS, HOOK, LINE, BLOCK};
    private int offset = 0;

    @Override
    public Rock get() {
        if (offset >= ROCKS.length)
            offset = 0;

        return ROCKS[offset++];
    }

    public static int maxHeight() {
        return Arrays.stream(ROCKS).mapToInt(Rock::height).max().orElseThrow();
    }
}
