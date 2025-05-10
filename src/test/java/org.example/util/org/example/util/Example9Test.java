package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Example9Test {

    @Test
    void testReplaceNth() {
        String text = "Vader said";
        char oldCharacter = 'a';
        char newCharacter = 'o';
        int number = 2;
        String expectd = "Vader soid";
        String actual = Example9.replaceNth(text, number, oldCharacter, newCharacter);
        Assertions.assertEquals(expectd, actual);
    }

    @Test
    void testGetCharacterNumbersMap() {
//        String string = "Vader soid: No, I am your fother!";
        String string = "Vader said";

        Map<Integer, Integer> expected = new HashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 7);
        Map<Integer, Integer> actual =
                Example9.getCharacterNumbersMap(string, "a");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFindMissingLetter() {
        char [] input = {'a','b','c','d','e','g'};
        char expected = 'f';
        char actual = Example9.findMissingLetter(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testStreetFighterSelection() {
        String [] [] fighters = new String[][]{
                new String[] { "Ryu", "E.Honda", "Blanka", "Guile", "Balrog", "Vega" },
                new String[] { "Ken", "Chun Li", "Zangief", "Dhalsim", "Sagat", "M.Bison" },
        };
         int [] position = new int[]{0,0};
        String[] moves = new String[] { "up", "left", "right", "left", "left" };
        String[] expected = new String[] { "Ryu", "Vega", "Ryu", "Vega", "Balrog" };
        String[] actual = Example9.streetFighterSelection(fighters, position, moves);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testCheckChars() {
        Example9.checkChars();
    }

    @Test
    void tesRot13() {
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String actual = Example9.rot132(input);
        System.out.println(actual);
    }

    @Test
    void isValid() {
        char [] input = new char[] {'n','s','n','s','n','s','n','s','n','n'};
        boolean expected = false;
        boolean actual = Example9.isValid2(input);
        System.out.println("actual is " + actual);
        Assertions.assertEquals(expected, actual);
    }
}