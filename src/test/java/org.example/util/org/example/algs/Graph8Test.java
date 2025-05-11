package org.example.algs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Graph8Test {

    @Test
    void getWeightForTwoVertexes() {
        Graph8 graph8 = new Graph8();
        int expected = 1;
        int actual = graph8.getWeightForTwoVertexes(1,2);
        Assertions.assertEquals(expected, actual);
    }
}