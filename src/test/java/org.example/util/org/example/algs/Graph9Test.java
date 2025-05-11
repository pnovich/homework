package org.example.algs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Graph9Test {

    @Test
    void getWeightForTwoVertexes() {
        Graph9 graph9 = new Graph9();
        int first = 1;
        int second = 2;
        int expected = 1;
        int actual = graph9.getWeightForTwoVertexes(first, second);
        assertEquals(expected, actual);
    }
}