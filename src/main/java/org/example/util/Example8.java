package org.example.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example8 {
    public static void main(String[] args) {
        doSomWork();
    }

    public  static String encrypt(final String text, final int n) {
        // Your code here
        if (text.equals("") || n < 0) {
            return text;
        }
        String result = encryptRecursiveCall(text, n);
        return result;
    }

    public  static String decrypt(final String encryptedText, final int n) {
        // Your code here
        if (encryptedText.equals("") || n < 0) {
            return encryptedText;
        }

        String result = decryptRecursiveCall(encryptedText, n);
        return result;
    }


    public static String encryptRecursiveCall(String input, int n) {
        if (n == 0) {
            return input;
        } else {
            String newString = internalEncrypt(input);
            return encryptRecursiveCall(newString, n -1);
        }
    }

    public static String decryptRecursiveCall(String input, int n) {
        if (n == 0) {
            return input;
        } else {
            String newString = internalDecrypt(input);
            return decryptRecursiveCall(newString, n -1);
        }
    }


    public static String internalEncrypt(String string) {
        String result = "";
        List<String> inputStrings = Arrays.stream(string.split("")).toList();
        List<String> odds = new ArrayList<>();
        List<String> evens = new ArrayList<>();
        for (int i = 0; i < inputStrings.size(); i++) {
           if (i % 2 == 0) {
               evens.add(inputStrings.get(i));
           }  else {
               odds.add(inputStrings.get(i));
           }
        }
        StringBuilder substringOdds = new StringBuilder();
        StringBuilder substringsEvens = new StringBuilder();
        odds.forEach(s -> {
            substringOdds.append(s);
        });
        evens.forEach(s -> {
            substringsEvens.append(s);
        });

        result = substringOdds.append(substringsEvens).toString();
        return result;
    }

    public static String internalDecrypt(String string) {
        String result = "";
        int firstLength = string.length()/2;

        String [] firstSubstring = string.substring(0,firstLength).split("");
        String [] secondSubstring = string.substring(firstLength,string.length()).split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length()/2; i++) {
           stringBuilder.append(secondSubstring[i]);
           stringBuilder.append(firstSubstring[i]);
        }
        if (string.length() % 2 == 1) {
            stringBuilder.append(secondSubstring[secondSubstring.length - 1]);
        }
        result = stringBuilder.toString();
        return result;
    }

    public static String encrypt2(final String text, final int n) {
        if(text == null || n < 1) return text;
        String first = IntStream.range(0, text.length())
                .filter(i -> i%2 == 1)
                .mapToObj(i -> text.charAt(i))
                .map(Object::toString)
                .collect(Collectors.joining(""));
        String second = IntStream.range(0, text.length())
                .filter(i -> i%2 == 0)
                .mapToObj(i -> text.charAt(i))
                .map(Object::toString)
                .collect(Collectors.joining(""));
        return encrypt(first + second, n - 1);
    }


    public static String decrypt2(final String text, final int n) {
        if(text == null || n < 1) return text;
        String decrypted = IntStream.range(0, text.length())
                .mapToObj(i -> (i%2 == 1) ? text.charAt(i/2) : text.charAt(text.length()/2 + i/2))
                .map(Object::toString)
                .collect(Collectors.joining(""));
        return decrypt(decrypted, n - 1);
    }

    public static int ConvertBinaryArrayToInt2(List<Integer> binary) {
        // Your Code
        String workingString = binary.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining());
        List<Integer> workingList = Arrays.stream((new StringBuilder(workingString)).reverse()
                .toString()
                .split(""))
                .toList()
                .stream()
                .map(Integer::parseInt)
                .toList();
        int result = 0;
        for (int i = 0; i < workingList.size(); i++) {
//            result <<= 1;
            if (workingList.get(i)  != 0) {
                int current = (int) Math.pow(2, i);
                result  = result + current;
            }

        }
        return result;
    }

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        // Your Code

        int result = 0;
        for (int i = binary.size() - 1; i > -1; i--) {
            if (binary.get(i)  != 0) {
                int j = binary.size() - i - 1;
                int current = (int) Math.pow(2, j);
                result  = result + current;
            }

        }
        return result;
    }

    public static int ConvertBinaryArrayToInt3(List<Integer> binary) {
        return binary.stream().reduce((x, y) -> x * 2 + y).get();
    }

    public static int ConvertBinaryArrayToInt4(List<Integer> binary) {

        int number = 0;
        for (int bit : binary)
            number = number << 1 | bit;
        return number;
    }

    public static void doSomWork() {
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,2,6);
        List<Integer> list3 = Arrays.asList(2,4,9);
        List<List<Integer>> generalList = Arrays.asList(list1, list2, list3);
        Map<Integer, Long> map =
                generalList.stream()
                        .flatMap(e ->e.stream())
                        .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        map.forEach((k,v) -> {
            System.out.println(" key = " + k + " value = " + v);

        });

        List<Integer> resultList =
                generalList.stream()
                .flatMap(e ->e.stream())
                .toList();
        System.out.println(resultList);

        Integer i = resultList.stream()
                .reduce(0, (a,b) -> {
                    if (b % 2 == 0) {
                        return a + b;
                    } else {
                        return a;
                    }
//                    return a + b;
                });
        System.out.println(i);

        List<String> list11 = Arrays.asList("qq","wws","eertyyy");
        List<String> list12 = Arrays.asList("a","b","c", "qq");
        List<String> list13 = Arrays.asList("fffff","gh", "dfg", "q");

        List<List<String>> resultList2 = Arrays.asList(list11, list12, list13);

        Map<String, Integer> map2 = resultList2.stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toMap(k -> k, v -> v.length(), (x,y) -> x));

        System.out.println(map2);


        Map<Character,Double> map3 = resultList2.stream()
                        .flatMap(s -> s.stream())
                        .flatMap(s -> Stream.of(s.charAt(0)))
                        .collect(Collectors.groupingBy(Function.identity(),Collectors.averagingInt(j -> new String(String.valueOf(j)).length())));


        System.out.println(map3);
    }


    public static int[] addingShifted(int[][] arrayOfArrays, int shift)
    {
        Map<Integer,Integer> lengthMap = new HashMap<>();
        List<List<Integer>> arrayOfStrings = getListOfStringListsFromIntArrays(arrayOfArrays);
        for (int i = 0; i < arrayOfStrings.size(); i++) {
            List<Integer> shiftedString = shiftStringListWithCount(arrayOfStrings.get(i),i, shift, lengthMap);
            arrayOfStrings.set(i, shiftedString);
        }
        int max = lengthMap.values().stream().max(Comparator.naturalOrder()).get();
        List<Integer>  resultList = countResultSumForListOfIntegerListsList(arrayOfStrings, max);
        int [] result = resultList.stream()
                .mapToInt(o -> o)
                .toArray();
        return result;
    }

    public static List<List<Integer>> getListOfStringListsFromIntArrays(int [][] arrayOfArrays) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < arrayOfArrays.length; i++) {
            List<Integer> currentList = convertOneArrayToList(arrayOfArrays[i]);
            resultList.add(currentList);
        }

        return resultList;
    }

    public static List<Integer> convertOneArrayToList(int[] arrayOfArray) {
        List<Integer> result =
                Arrays.stream(arrayOfArray)
                        .boxed()
                        .toList();
        return result;
    }

    public static List<Integer> shiftStringListWithCount(List<Integer> line, int i, int shift, Map<Integer,Integer> lengthMap) {
        int resultNumber = i * shift;
        List<Integer> resultList = new ArrayList<>(line);
        for (int j = 0; j < resultNumber ; j++) {
            resultList.add(0,0);
        }
        lengthMap.put(i, resultList.size());

        return resultList;
    }

    public static List<Integer> countResultSumForListOfIntegerListsList(List<List<Integer>> arrayOfStrings, int max) {
        List<Integer> resultList = new ArrayList<>();
        List<List<Integer>> copy = new ArrayList<>(arrayOfStrings);
        for (int i = 0; i < copy.size(); i++) {
            for (int j = copy.get(i).size(); j < max; j++) {
                List<Integer> lineCopy = new ArrayList<>(copy.get(i));
                lineCopy.add(0);
                copy.set(i,lineCopy);
            }
        }

        for (int i = 0; i < max; i++) {
            int columnResult = 0;
            for (int j = 0; j < copy.size(); j++) {
                Integer addingIntValue = copy.get(j).get(i);
                    columnResult = columnResult + addingIntValue;
            }

            resultList.add(i, columnResult);
        }
        return resultList;
    }



}