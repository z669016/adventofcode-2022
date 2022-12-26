package com.putoet.day22;

public class PasswordResolver {
    public static int resolve(StrangeBoard board, MoveStrategy strategy, String instructions) {
        final Path path = new Path(instructions);
        board.start();

        while (path.hasNext()) {
            final String action = path.next();
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
