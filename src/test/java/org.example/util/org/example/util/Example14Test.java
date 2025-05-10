package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Example14Test {

    @Test
    void testLongestConsec() {
        String [] strings = {"ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"};
        String [] strings2 = {"tree", "foling", "trashy", "blue", "abcdef", "uvwxyz"};
        int k = 1;
        int k2 = 2;
        String expected = "oocccffuucccjjjkkkjyyyeehh";
        String expected2 = "folingtrashy";
        String actual = Example14.longestConsec(strings, k);
        String actual2 = Example14.longestConsec(strings2, k2);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }
}