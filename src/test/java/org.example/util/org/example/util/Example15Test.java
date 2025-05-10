package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Example15Test {

    @Test
    void testCheck() {
        Object [] objects = (new Object[] {80, 117, 115, 104, 45, 85, 112, 115});
        Object input = 45;
        boolean expected = true;
        boolean actual = Example15.check(objects, input);
    }

    @Test
    void testRemove() {
        String input = "Hello World";
        String expected = "ello Worl";
        String actual = Example15.remove(input);
        assertEquals(expected, actual);
    }

    @Test
    void testReversInt() {
        int input = 812;
        int expected = 218;
        int actual = Example15.reversInt(input);
        assertEquals(expected, actual);
    }

    @Test
    void testSmallEnough() {
        int [] input = {80, 117, 115, 104, 45, 85, 112, 115};
        int limit = 119;
        boolean expected = true;
        boolean actual = Example15.smallEnough(input, limit);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMergeSortArrays() {
        int [] arr1 = {1,4,6};
        int [] arr2 = {2,5,7,9};
        int [] expected = {1,2,4,5,6,7,9};
//        int [] expected = {0,0,0,0,0,0};
        int [] actual = Example15.mergeSortArrays3(arr1, arr2);
        Assertions.assertEquals(actual.length, expected.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], actual[i]);
        }


    }

    @Test
    void testAddIncreasingSequence() {
        int current = 8;
        int [] array = {2,5,7,9};
        int [] resultArray = {0,0,0,0};
        int [] resultArrayCounter = {0};
        int inputArrayCounter = 0;
        int [] expected = {2,5,7,0};
        Example15.addIncreasingSequence(current, array, resultArray, inputArrayCounter, resultArrayCounter);
        System.out.println("done");
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(expected[i], resultArray[i]);
        }

    }
}