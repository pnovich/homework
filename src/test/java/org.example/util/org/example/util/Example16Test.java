package org.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Example16Test {

    @Test
    void testComp() {
        int [] a = {121, 144, 19, 161, 19, 144, 19, 11};
        int [] b = {121, 14641, 20736, 361, 25921, 361, 20736, 361};
        boolean expected = true;
        boolean actual = Example16.comp(a, b);
        assertEquals(expected, actual);
    }

    @Test
    void testBetween() {
        int a = 1;
        int b = 4;
        int [] expected = {1,2,3,4};
//        int [] expected = null;
        int [] actual = Example16.between(a, b);
        assertArrayEquals(expected, actual);
    }
}