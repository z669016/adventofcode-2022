package com.putoet.day24;

import com.putoet.grid.Point;

import java.util.List;

public record ValleyRoutes(Valley valley, List<Point> points, Integer steps) {
}
