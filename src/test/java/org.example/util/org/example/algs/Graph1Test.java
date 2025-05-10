package org.example.algs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Graph1Test {

    @Test
    void testGetData() {
        int [][] inputArray = {
                {0,1},
                {0,4},
                {0,6},
                {2,3},
                {2,4},
                {3,4},
                {5,1},
                {5,6}
        };
        Map<Integer, List<Integer>> expectedMap = new HashMap<Integer, List<Integer>>();
        expectedMap.put(0, Arrays.asList(1,4,6));
        expectedMap.put(1, Arrays.asList(0,5));
        expectedMap.put(2, Arrays.asList(3,4));
        expectedMap.put(3, Arrays.asList(2,4));
        expectedMap.put(4, Arrays.asList(0,2,3));
        expectedMap.put(5, Arrays.asList(1,6));
        expectedMap.put(6, Arrays.asList(0,5));
        System.out.println(expectedMap);
        Graph1 g = new Graph1();
        Map<Integer,List<Integer>> actual = g.getData(inputArray);
        assertEquals(expectedMap, actual);
    }
}