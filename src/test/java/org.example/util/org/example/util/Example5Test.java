package org.example.util;

//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class Example5Test {
    @Test
    public void testDoReplaceNeededCharacters() {
//        Example5 example5 = new Example5();
//        String expected = "FaRbFR";
//        String actual = example5.doReplaceNeededCharacters(new StringBuilder("Fa")).toString();
//        assertEquals(expected,actual);
    }

    @Test
    public void  testGetSum() {
     Example5 example5 = new Example5();
     int actual = example5.GetSum(1,3);
     int expected1 = 6;
     int expected2 = 1;
     Assertions.assertEquals(actual, expected1);
     actual = example5.GetSum(1,1);
     Assertions.assertEquals(actual, expected2);
    }

    @Test
    public void  testGetSumWithNegativeNumber() {
        Example5 example5 = new Example5();
        int actual = example5.GetSum(-1,3);
        int expected1 = 5;
        int expected2 = 1;
        Assertions.assertEquals(actual, expected1);
        actual = example5.GetSum(1,1);
        Assertions.assertEquals(actual, expected2);
    }

    @Test
    public void testIsTriangle() {
        Example5 example5 = new Example5();
        boolean expected = false;
        boolean actual = example5.isTriangle(1,2,3);
        Assertions.assertEquals(expected, actual);


    }

    @Test
    public void testMxdiflg() {
      Example5 example5 = new Example5();
      int expected = 10;
      String [] input1 = {"11","1111", "1111111111"};
      String [] input2 = {"1", "11",""};
      int actual = example5.mxdiflg(input1, input2);
      Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMxdiflgWithEmptyArray() {
        Example5 example5 = new Example5();
        int expected = -1;
        String [] input1 = {"11","1111", "1111111111"};
        String [] input2 = {};
        int actual = example5.mxdiflg(input1, input2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMxdiflg2() {
        Example5 example5 = new Example5();
        int expected = 18;
        String [] input1 = {"ejjjjmmtthh",
                "zxxuueeg",
                "aanlljrrrxx",
                "dqqqaaabbb",
                "oocccffuucccjjjkkkjyyyeehh"
        };
        String [] input2 = {"1bbbaaayddqbbrrrv"};
        int actual = example5.mxdiflg(input1, input2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindNeedle() {
        Example5 example5 = new Example5();
        Object [] array= {"qw",null,"needle","werrrer"};
        String expected = "found the needle at position 2";
        String actual = example5.findNeedle(array);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetMiddle() {
        Example5 example5 = new Example5();
        String input = "errp";
        String expected = "rr";
        String actual = example5.getMiddle(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDoRotate() {
        Example5 example5 = new Example5();
        String input = "12345";
        String expected = "23451";
        String actual = Example5.rotateString(input);
//        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testStringToInt() {
        String input = "12345";
        Example5 example5 = new Example5();
        int expected = 12345;
        int actual = example5.stringValueToInt(input);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testArrange() {
        String input = "after be arrived two My so";
        String expected = "be ARRIVED two AFTER my SO";
        String actual = Example5.arrange(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testVertMirror() {
        String s = "abcd,efgh,ijkl,mnop";
        String expected = "dcba\rhgfe\rlkji\rponm";
        String actual = Example5.vertMirror(s);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testHorMirror() {
        String s = "abcd,efgh,ijkl,mnop";
        String expected = "mnop\rijkl\refgh\rabcd";
        String actual = Example5.horMirror(s);
        Assertions.assertEquals(expected, actual);
        String s2 = "abcd,efgh,oooo,ijkl,mnop";
        String expected2 = "mnop\rijkl\roooo\refgh\rabcd";
        String actual2 = Example5.horMirror(s2);
        Assertions.assertEquals(expected2, actual2);

    }


    @Test
    void testOper() {
        String s = "abcd,efgh,ijkl,mnop";
//        String s2 = "hSgdHQ,HnDMao,ClNNxX,iRvxxH,bqTVvA,wvSyRu";
        Function <String, String >nameOfThefunction = Example5::horMirror;
        String expected = "mnop\rijkl\refgh\rabcd";
        String actual = Example5.oper(nameOfThefunction, s);
        System.out.printf("actual = " + actual);
        Assertions.assertEquals(expected, actual);


    }

    @Test
    void testShowSequence() {
        int n = 6;
        String expected = "0+1+2+3+4+5+6 = 21";
        String actual = Example5.showSequence(n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testShowSequenceWitZeroNumber() {
        int n = 0;
        String expected = "0=0";
        String actual = Example5.showSequence(n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testShowSequenceWithNegativeNumber() {
        int n = -15;
        String expected = "-15<0";
        String actual = Example5.showSequence(n);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testGetListFromString() {
        String input = "qq dd aa bb";
        String[] expected = {"qq dd aa","bb"};
        String [] inputArray = input.split(" ");
        String [] actual = Example5.getListFromString(inputArray, 2);
        System.out.println("actual = " + actual);
        Assertions.assertEquals(actual[0], expected[0]);
        Assertions.assertEquals(actual[1], expected[1]);
        Assertions.assertEquals(actual.length, expected.length);

    }

    @Test
    void testPartlist() {
        String [] input = {"qq","dd","aa","bb"};
        String [] input2 = {"qq","dd"};
        String [] [] actual = Example5.partlist(input2);
        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(actual[i][j]);
            }
        }
    }

    @Test
    void testAttack() {
        Fighter attacker = new Fighter("Klitchko",100, 15);
        Fighter defender = new Fighter("Tyson",40, 20);
        boolean expected = true;
        boolean actual = Example5.attack(attacker,defender);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testAttackWithKnockout() {
        Fighter attacker = new Fighter("Klitchko",100, 50);
        Fighter defender = new Fighter("Fury",40, 20);
        boolean expected = false;
        boolean actual = Example5.attack(attacker,defender);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testDeclareWinner() {
        String expected = "Lew";
        String actual = Example5.declareWinner(new Fighter("Lew", 10, 2),new Fighter("Harry", 5, 4), "Lew");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void doAttackWithThreads() throws InterruptedException {
        int expected = 1;
        int actual = Example5.doAttackWithThreads();
        System.out.println("actual = " + actual);
        Assertions.assertEquals(expected, actual);
    }
}
