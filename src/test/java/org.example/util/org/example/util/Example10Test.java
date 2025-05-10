package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Example10Test {

    @Test
    void testSumTwoSmallestNumbers() {
        long[] input = new long[]{5, 8, 12, 19, 22};
        long expcted = 13;
        long actual = Example10.sumTwoSmallestNumbers(input);
        Assertions.assertEquals(expcted, actual);
    }

    @Test
    void testDoArray() {
        int[] input = new int[]{5, 8, 12, 19, 22};
        int[] expected = new int[]{5, 8, 12, 19, 22};
        int[] actual = Example10.doArray(input);
        for (int i = 0; i < expected.length; i++) {
            System.out.println(actual[i]);
        }
    }

    @Test
    void testMerge() {
        int [] nums1 = new int [] {0};
        int m = 0;
        int [] nums2 = new int [] {1};
        int n = 1;
        Example10 example10 = new Example10();
        int [] expected = new int [] {1,2,2,3,5,6};
        example10.merge(nums1, m , nums2, n);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i]);
//            Assertions.assertEquals(nums1[i], expected[i]);
        }


    }

    @Test
    void testFindEvenIndex() {
        int [] input = new int [] {0,0,0,0,0};
        int expected = 0;
        int actual = Example10.findEvenIndex(input);
        System.out.println("actual: " + actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGenerateSums() {
        int [] ints = new int [] {1,2,3,4,5};
        int increment = 1;
        int [] expected = new int [] {1,3,6,10,15};
        int [] actual = Example10.generateSums(ints, increment);
        for (int i = 0; i < expected.length; i++) {
            System.out.println(actual[i]);
            Assertions.assertEquals(expected[i], actual[i]);
        }
        int [] expected2 = new int [] {15,14,12,9,5};
        int increment2 = -1;
        int [] actual2 = Example10.generateSums(ints, increment2);
        for (int i = 0; i < expected2.length; i++) {
            System.out.println(actual2[i]);
            Assertions.assertEquals(expected2[i], actual2[i]);
        }
    }

    @Test
    void testGetCurrentChar() {
        String s1 = ":";
        String s2 = ";";
        String s3 = "-";
        String s4 = ")";
        String s5 = "D";
        String s6 = "~";
        List<Character> chars = Arrays.asList(s1,s2,s3,s4,s5,s6).stream()
                .map(s -> (Character)Example10.getCurrentChar(s))
                .toList();
        chars.forEach(System.out::println);
    }

    @Test
    void testIsTheSmile() {
        String input = ":~)";
        boolean expected = true;
        boolean actual = Example10.isTheSmile(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCountSmileys() {
//        List<String> input = new ArrayList<>(Arrays.asList(":)", ";(", ";}", ":-D"));
        List<String> input = new ArrayList<>();
        int expected = 0;
        int actual = Example10.countSmileys(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSmash() {
        String [] input = new String [] { "hello", "world"};
        String expected = "hello world";
        String actual = Example10.smash(input);
    }

    @Test
    void testFindIt() {
        int [] input = new int [] {20, 1, -1, 2, -2, 3, 3, 5, 5, 1, 2, 4, 20, 4, -1, -2, 5};
        int expected = 5;
        int actual = Example10.findIt(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFindNb() {
        long input = 4183059834009L;
        long expected = 2022;
        long actual = Example10.findNb(input);
        System.out.println("actual: " + actual);
    }
}