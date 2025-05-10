package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Example8Test {

    @Test
    void testIntrnalEncrypt() {
        String input = "012345";
        String expected = "135024";
        String actual = Example8.internalEncrypt(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testInternalDecryptEvenLength() {
        String input = "135024";
        String expcted = "012345";
        String actual = Example8.internalDecrypt(input);
        Assertions.assertEquals(expcted, actual);
    }

    @Test
    void testInternalDecryptOddLength() {
        String input = "13024";
        String expcted = "01234";
        String actual = Example8.internalDecrypt(input);
        Assertions.assertEquals(expcted, actual);
    }


    @Test
    void testEncrypt() {
        String input = "012345";
        int n = 2;
        String expcted = "304152";
        String actual = Example8.encrypt(input, n);
        Assertions.assertEquals(expcted, actual);
    }

    @Test
    void testDecrypt() {
        String input = "304152";
        int n = 2;
        String expcted = "012345";
        String actual = Example8.decrypt(input, n);
        Assertions.assertEquals(expcted, actual);

    }

    @Test
    public void testEncryptWithWrongParameters() {
        String input1 = "";
        int n1 = 2;
        String expcted = "";
        String actual = Example8.encrypt(input1, n1);
        Assertions.assertEquals(expcted, actual);

        String input2 = "012345";
        int n2 = -2;
        String expcted2 = "012345";
        String actual2 = Example8.encrypt(input2, n2);
        Assertions.assertEquals(expcted2, actual2);


    }


    @Test
    void testAddingShifted() {
        int [] [] inputArray = {{1,1,1},{-1,1,1},{1,1,1}};
        int shift = 3;
        int [] expected = {1,1,1,-1,1,1,1,1,1};
        int [] actual = Example8.addingShifted(inputArray, shift);
        Assertions.assertEquals(Arrays.stream(expected).boxed().toList(),
                Arrays.stream(actual).boxed().toList());
    }

    @Test
    void testGetListOfStringListsFromIntArrays() {
        int [] [] inputArray = {{1,1,1},{1,1,1},{1,1,1}};
        List<Integer> list1 = Arrays.asList(1,1,1);
        List<Integer> list2 = Arrays.asList(1,1,1);
        List<Integer> list3 = Arrays.asList(1,1,1);
        List<List<Integer>> testResult = Arrays.asList(list1, list2, list3);
        List<List<Integer>> actual = Example8.getListOfStringListsFromIntArrays(inputArray);
        Assertions.assertEquals(testResult, actual);
        System.out.println(actual);


    }

    @Test
    void testShiftStringListWithCount() {
        List<Integer> input = Arrays.asList(1,2,3,4,5);
        int count = 1;
        int shift = 3;
        Map<Integer,Integer> map = new HashMap<>();
        String expected = "   12345";
        List<Integer> expectedList = Arrays.asList(0,0,0,1,2,3,4,5);
        List<Integer> actualList = Example8.shiftStringListWithCount(input,count,shift, map);
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void testCountResultSumForListOfIntegerListsList() {
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(0,0,0,1,2,3);
        List<List<Integer>> inputList = Arrays.asList(list1,list2);
        List<Integer> expected = Arrays.asList(1,2,3,1,2,3);
        int max = 6;
        List<Integer> actual = Example8.countResultSumForListOfIntegerListsList(inputList, max);
    }
}