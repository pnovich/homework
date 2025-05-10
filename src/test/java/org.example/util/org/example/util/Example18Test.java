package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class Example18Test {

    @Test
    void testMakeReadable() {
        int input = 86399;
        String expected = "23:59:59";
        int input2 = 0;
        String expected2 = "00:00:00";
        String actual = Example18.makeReadable(input);
        String actual2 = Example18.makeReadable(input2);
        assertEquals(expected2, actual2);
    }

    @Test
    void testSequence() {
        int [] input = {1,2,3,4,5};
        int expected = 5;
        int [] input2 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected2 = 6;
        int actual = Example18.sequence(input2);
        assertEquals(expected2, actual);
    }

    @Test
    void testRemoveSmallest() {
        int [] input = {2, 2, 1, 2, 1};
        int [] expected = {2, 2, 2, 1};
        int [] actual = Example18.removeSmallest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testHowMuchILoveYou() {
        int input = 5;
        String expected = "";
        String actual = Example18.howMuchILoveYou2(input);
//        assertEquals(expected, actual);
        System.out.println("actual: " + actual);
    }

    @Test
    void testReplace() {
        String input = "Hi!";
        String expected = "H!!";
        String actual = Example18.replace(input);
        System.out.println("actual: " + actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCount() {
        String input = "aaBBB";
        Map<Character, Integer> expected = new HashMap<>(){{
            put('a', 2);
            put('B', 3);
        }};
        Map<Character, Integer> actual = Example18.count3(input);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDoubleChar() {
        String input = "123";
        String expected = "";
        String actual = Example18.doubleChar(input);
        System.out.println("result = "  + actual);
//        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSomeSpy() {
        List<Integer> list = new ArrayList<>();
        List<Integer> spyList = spy(list);
        spyList.add(1);
        spyList.add(3);
        System.out.println(spyList.size());
        List<Integer> mockList = mock(List.class);
        mockList.add(1);
        mockList.add(3);
        System.out.println(mockList.size());
    }

    @Test
    void getCorrectNumber() {
        int input = 0;
        int expected = 0;
        int actual = Example18.getCorrectNumber(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testTowerBuilder() {
        int n = 5;
        String[] expected = new String[0];
        String[] actual = Example18.towerBuilder(n);

    }

    @Test
    void getString() {
        int input = 5;
    }

    @Test
    void testDigitize() {
        int input = 12345;
        int [] expected = {5,4,3,2,1};
        int [] actual = Example18.digitize(input);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testFactors() {
        Long startTime = new Date().getTime();
        int input = 12;
        String expected = "(2**2)(3)";
        String actual = Example18.factors(input);

//        int input2 = 45350700;
//        int input2 = 18195729;
//        int input2 = 23069924;
        int input2 = 56854761;
//        String expected2 = "(2**5)(5)(7**2)(11)";
        String actual2 = Example18.factors(input2);
//        Assertions.assertEquals(expected2, actual2);

        Long secondTime = new Date().getTime();
        System.out.println("actual2 = " + actual2);
        System.out.println("time = " + (secondTime - startTime));

    }

    @Test
    void testConvertNumbersMapToString() {
        Map<Integer, Integer> map = new HashMap<>(){{
            put(2,2);
            put(3,1);
            put(4,0);
        }};
        String expected = "(2**2)(3)";
        String actual = Example18.convertNumbersMapToString(map);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetPrimeNumbers() {
        Long startTime = new Date().getTime();
        int input = Integer.MAX_VALUE;
//        int input = 45350700;
//        int input = 46340;

        Integer [] expected = {1,2,3,5,7,11};
        Integer [] actual = Example18.getPrimeNumbers(input);
//        for (int i = 0; i < expected.length; i++) {
//            Assertions.assertEquals(expected[i],actual[i]);
//
//        }

//        Assertions.assertTrue(actual.length > 0);
        Long secondTime = new Date().getTime();
        System.out.println("time = " + (secondTime - startTime));

    }

    @Test
    void testGetNumbersToRemove() {
        String [] input = {"NORTH", "SOUTH", "SOUTH", "EAST"};
        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1));
        Set<Integer> actual = Example18.getNumbersToRemove(input);
        System.out.println(actual);
    }

    @Test
    void testDirReduc() {
        String[] input = {"NORTH", "SOUTH", "SOUTH", "EAST"};
        String[] expected = {"SOUTH", "EAST"};
        String[] actual = Example18.dirReduc(input);
        System.out.println(actual);
        for (int i = 0; i < actual.length; i++) {
            System.out.println(actual[i]);
        }


    }

    @Test
    void testXor() {
        boolean a = true;
        boolean b = false;
        boolean expected = true;
        boolean actual = Example18.xor(a,b);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMap() {
        int[] input = {1,2,3};
        int[] expected = {2,4,6};
        int[] actual = Example18.map(input);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void century() {
        int input = 200;
        int expected = 2;
        int actual = Example18.century(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testWarnTheSheep() {
        String [] input = {"sheep","sheep", "wolf", "sheep"};
        String expected = "Oi! Sheep number 1! You are about to be eaten by a wolf!";
        String actual = Example18.warnTheSheep(input);
        Assertions.assertEquals(expected, actual);
    }
}