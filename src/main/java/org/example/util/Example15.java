package org.example.util;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example15 {
    public static boolean check(Object[] a, Object x) {
        List<String> list = Arrays.stream(a)
                .map(String::valueOf)
                .collect(
                        ArrayList::new,
                        (r,e) -> {
                            r.add(e);
                        },
                        (c1,c2) -> {
                            c1.addAll(c2);
                        }
                );
        String string = String.valueOf(x);

        Map<String, Integer> map1 =
                IntStream.range(0, list.size())
                        .boxed()
                        .collect(HashMap::new,
                                (m,i) -> {
                                    int count = (int) list.stream()
                                            .filter(e -> e.equals(list.get(i)))
                                            .count();
                                    if (!m.containsKey(list.get(i))) {
                                        m.put(list.get(i), count);
                                    }
                                },
                                (m1,m2) -> {
                            m1.putAll(m2);
                                });
        Map<Integer,String> map2 =
                IntStream.range(0, list.size())
                        .boxed()
                        .collect(HashMap::new,
                                (m,i) -> {
                            m.put(i,list.get(i));
                                },
                                (m1,m2) -> {
                            m1.putAll(m2);
                                }
                                );

        Optional<String> optionalS =
                list.stream()
                        .filter(e -> e.equals(string))
                        .findFirst();
        if (optionalS.isPresent()) {
            return true;
        }

        // Your code here
        return false;
    }

    public static String remove(String str) {
        return str.substring(1, str.length() - 1);
        // your code here
    }

    public static int reversInt2(int x) {
        int result = x;
        String string = String.valueOf(x);
        StringBuilder stringBuilder = new StringBuilder(string);
        String reversedString = stringBuilder.reverse().toString();
        result = Integer.parseInt(reversedString);
        return result;
    }

    public static int reversInt(int x) {
        int result = x;
        int temp = x;
        List<Integer> list = new ArrayList<>();
        while (temp / 10 > 1) {
            list.add(temp % 10);
            temp /= 10;
        }
        list.add(temp % 10);
        int tempInt = 0;
        for (int i = 0; i < list.size(); i++) {
          tempInt = tempInt + (int)Math.pow(10, list.size()-1 -i)*list.get(i).intValue();
        }
        result = tempInt;
        return result;
    }

    public static boolean smallEnough(int[] a, int limit)
    {
        Optional<Integer> optional =
                Arrays.stream(a)
                        .boxed()
                        .filter(e -> e > limit)
                        .findFirst();
        if (!optional.isPresent()) {
            return true;
        }
        return false;
    }

    public static  int [] mergeSortArrays2(int [] arr1, int [] arr2) {
        int [] arr3 = new int [arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            arr3[arr1.length + i] = arr2[i];
        }

        for (int i = arr3.length -1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr3[j] > arr3[j + 1]) {
                    int temp = arr3[j];
                    arr3[j] = arr3[j + 1];
                    arr3[j + 1] = temp;
                }
            }
        }
        return arr3;
    }

    public static  int [] mergeSortArrays3(int [] arr1, int [] arr2) {
        int [] arr3 = new int [arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            arr3[arr1.length + i] = arr2[i];
        }

        Map<Integer,Integer> map = new TreeMap<>(Comparator.naturalOrder());
        for (int i = 0; i < arr3.length; i++) {
            map.put(arr3[i], i);
        }
//        for (int i = 0; i < arr3.length; i++) {
//            int c = 0;
//            for (Map.Entry<Integer, Integer>  entry: map.entrySet()) {
//                if (entry.getValue() == i) {
//                    c = entry.getKey();
//                }
//            }
//            arr3[i] = c;
//        }

    int [] ints =map.keySet()
            .stream().mapToInt(e ->(Integer)e)
            .toArray();

//        return arr3;
        return ints;
    }


    public static  int [] mergeSortArrays(int [] arr1, int [] arr2) {
        int [] arr3 = new int [arr1.length + arr2.length];
        int [] currentArray;
        int [] anotherArray;
        int [] tempRef;
        int currentArrayCounter = 0;
        int anotherArrayCounter = 0;
        int [] resultArrayCounterValue = {0};
        int tempCount;
        if (arr1[0] > arr2[0]) {
            currentArray = arr1;
            anotherArray = arr2;
        } else {
            currentArray = arr2;
            anotherArray = arr1;
        }
        while (currentArrayCounter < currentArray.length &&
                anotherArrayCounter < anotherArray.length) {
            System.out.println("before swapping");
            int cur = currentArray[currentArrayCounter];
          addIncreasingSequence(currentArray[currentArrayCounter],
                  anotherArray,
                  arr3,
                  anotherArrayCounter,
                  resultArrayCounterValue);
          tempRef = anotherArray;
          anotherArray = currentArray;
          currentArray = tempRef;
//          tempCount = anotherArrayCounter;
//          currentArrayCounter = anotherArrayCounter + resultArrayCounterValue[0];
            tempCount = currentArrayCounter;
            currentArrayCounter = anotherArrayCounter + resultArrayCounterValue[0];
            anotherArrayCounter = tempCount;
            System.out.println("after swapping");
        }
        for (int l = anotherArrayCounter; l <= anotherArray.length; l++) {
            System.out.println("l");
            arr3[currentArray.length + l -1] = anotherArray[l -1];
        }
        return arr3;
    }

    public static void addIncreasingSequence(int current,
                                             int [] inputArray,
                                             int [] resultArray,
                                             int inputArrayCounter,
                                             int [] resultArrayCounterValue) {
        int i = inputArrayCounter;
        while (i < inputArray.length && current >= inputArray[i]) {
            i++;
        }
        for (int j = 0; j < i - inputArrayCounter; j++) {
            resultArray[resultArrayCounterValue[0] + j] = inputArray[inputArrayCounter + j];
        }
        resultArrayCounterValue[0] = resultArrayCounterValue[0] + i - inputArrayCounter;
        System.out.println("done inside addSequence method");
    }


    public static int[] capitals(String s){
        char [] chars = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            int current = chars[i];
            System.out.println("current = " + current);
            if (current >64 && current < 91) {
                list.add(i);
            }
        }

        int [] result = new int [list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }





}
