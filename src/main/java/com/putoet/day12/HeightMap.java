package com.putoet.day12;

import org.jetbrains.annotations.NotNull;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

record HeightMap(@NotNull Graph<HeightPoint, DefaultEdge> graph) {
    public HeightPoint start() {
        return graph.vertexSet().stream()
                .filter(vertex -> vertex.label() == 'S')
                .findFirst()
                .orElseThrow();
    }

    public HeightPoint end() {
        return graph.vertexSet().stream()
                .filter(vertex -> vertex.label() == 'E')
                .findFirst()
                .orElseThrow();
    }

    public List<HeightPoint> findAllLowest() {
        return graph.vertexSet().stream()
                .filter(vertex -> vertex.label() == 'S' || vertex.label() == 'a')
                .toList();
    }
}
