package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.util.Example12.accum;
import static org.junit.jupiter.api.Assertions.*;

class Example12Test {

    @Test
    void tstGreetOk() {
        String input = "dutch";
        String expected = "Welkom";
        String actual = Example12.greet(input);
        assertEquals(expected, actual);
    }

    @Test
    void tstGreetNull() {
        String input = "indian";
        String expected = "Welcome";
        String actual = Example12.greet(input);
        assertEquals(expected, actual);
    }

    @Test
    void testBmi() {
        double weigth = 75;
        double height = 1.80;
        String expected = "Normal";
        String actual = Example12.bmi(weigth, height);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testStrCount() {
        String input = "Hello";
        char character = 'o';
        int expected = 1;
        int actual = Example12.strCount(input, character);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testIsLetter() {
        String substring = "o";
        char character = 'o';
        boolean expected = true;
        boolean actual = Example12.isLetter(substring, character);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testRemoveLetters() {
        String input = "hello!";
        String expected = "hll!";
        String actual = Example12.removeLetters(input);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testDisemvowel() {
        String input = "This website is for losers LOL!";
        String expected = "Ths wbst s fr lsrs LL!";
        String actual = Example12.disemvowel(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDnaToRna() {
        String input = "TTTT";
        Example12 example12 = new Example12();
        String expected = "UUUU";
        String actual = example12.dnaToRna(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFindNumber() {
        int [] input = {3,2,4,1,7,9,8,6};
        int expectd = 5;
        int actual = Example12.findNumber(input);
        Assertions.assertEquals(expectd, actual);
    }

    @Test
    void testFindNumber2() {
        int [] input = {1,2};
        int expectd = 3;
        int actual = Example12.findNumber2(input);
        Assertions.assertEquals(expectd, actual);
    }


    @Test
    void testAccum() {
        String input = "aBcd";
        String expected = "A-Bb-Ccc-Dddd";
        String actual = Example12.accum(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCheckString() {
        String input = "(()(())(ff))";
        boolean expected = true;
        boolean actual = Example12.checkString(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testMergeAlternately() {
        Example12 example12 = new Example12();
        String word1 = "abc";
        String word2 = "pqr111";
        String expected = "apbqcr111";
        String actual = example12.mergeAlternately(word1, word2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGcdOfStrings() {
        Example12 example12 = new Example12();
        String str1 = "TAUXXTAUXXTAUXXTAUXXTAUX";
        String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        String expected = "TAUXX";
        String actual = example12.gcdOfStrings(str1, str2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetRepeatedString() {
        Example12 example12 = new Example12();
        String input = "TAUXXTAUXXTA";
        String expected = "TAUXX";
        String actual = example12.getRepeatedString(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testIsRepeated() {
        Example12 example12 = new Example12();
        String input = "TAUXXTAUXX";
        String expected = "TAUXX";
        String actual = example12.getRepeatedSubstring(input);
        Assertions.assertEquals(expected, actual);
    }
}