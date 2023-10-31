package com.putoet.day12;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import org.jetbrains.annotations.NotNull;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.util.List;
import java.util.Objects;

public class Day12 {
    public static void main(String[] args) {
        final var heights = new HeightMap(createGraph(ResourceLines.list("/day12.txt")));

        Timer.run(() -> part1(heights));
        Timer.run(() -> part2(heights));
    }

    @NotNull
    static Graph<HeightPoint, DefaultEdge> createGraph(List<String> list) {
        final HeightPoint[][] grid = createGrid(list);

        final Graph<HeightPoint,DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);
        for (var line : grid)
            for (var point : line)
                graph.addVertex(point);

        for (var y = 0; y < grid.length; y++) {
            final var line = grid[y];
            for (var x = 0; x < line.length; x++) {
                if (x < line.length - 1) {
                    if (grid[y][x].distance(grid[y][x + 1]) < 2)
                        graph.addEdge(grid[y][x], grid[y][x + 1]);
                    if (grid[y][x+1].distance(grid[y][x]) < 2)
                        graph.addEdge(grid[y][x+ 1], grid[y][x]);
                }

                if (y < grid.length - 1) {
                    if (grid[y][x].distance(grid[y + 1][x]) < 2)
                        graph.addEdge(grid[y][x], grid[y + 1][x]);
                    if (grid[y+1][x].distance(grid[y][x]) < 2)
                        graph.addEdge(grid[y+1][x], grid[y][x]);
                }
            }
        }

        return graph;
    }

    @NotNull
    private static HeightPoint[][] createGrid(List<String> list) {
        final HeightPoint[][] grid = new HeightPoint[list.size()][list.get(0).length()];
        for (var y = 0; y < grid.length; y++)
            for (var x = 0; x < grid[y].length; x++)
                grid[y][x] = new HeightPoint(Point.of(x, y), list.get(y).charAt(x));
        return grid;
    }

    static void part1(HeightMap heights) {
        final var found = BFSShortestPath.findPathBetween(heights.graph(), heights.start(), heights.end());
        System.out.println("The fewest steps required to move from your current position to the location that should get the best signal is " + found.getLength());
    }

    static void part2(HeightMap heights) {
        final var starts = heights.findAllLowest();
        final var shortest = starts.stream()
                .parallel()
                .map(s -> BFSShortestPath.findPathBetween(heights.graph(), s, heights.end()))
                .filter(Objects::nonNull)
                .mapToInt(GraphPath::getLength)
                .min()
                .orElseThrow();

        System.out.println("The fewest steps required to move from any 'a' to the location that should get the best signal is " + shortest);
    }
}
