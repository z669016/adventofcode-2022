package com.putoet.day22;

import org.jetbrains.annotations.NotNull;

class PasswordResolver {
    public static int resolve(@NotNull StrangeBoard board, @NotNull MoveStrategy strategy, @NotNull String instructions) {
        final var path = new Path(instructions);
        board.start();

        while (path.hasNext()) {
            final var action = path.next();
            switch (action) {
                case "L" -> board.turnLeft();
                case "R" -> board.turnRight();
                default -> board.move(strategy, Integer.parseInt(action));
            }
        }

        final var location = board.location();
        return 1000 * (location.y() + 1) + 4 * (location.x() + 1) + board.facing().score();
    }
}
