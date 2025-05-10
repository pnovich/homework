package org.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Example17Test {

    @Test
    void testIntegerToBinaryString() {
        int input = 11;
//        String expected = "10";
        String expected = Integer.toBinaryString(input);
        String actual = Example17.integerToBinaryString(input);
        assertEquals(expected, actual);
    }

    @Test
    void binaryAddition() {
        int a = 0;
        int b = 2147483647;
        String expected = "10";
//        String actual1 = Example17.getGenericBinary(a + b);
//        System.out.println("first result = " + actual1);
        String actual2 = Example17.binaryAddition(a, b);
        System.out.println("actual = " + actual2);
//        assertEquals(expected, actual);
    }
}