package org.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Example16 {
    public static boolean comp(int[] a, int[] b) {
        boolean result = true;
        if (a == null || b == null || a.length != b.length) {
            return false;
        }
        List<Integer> listA = Arrays.stream(a).boxed()
                .map(e -> e * e)
                .sorted()
                .toList();
        List<Integer> listB = Arrays.stream(b)
                .boxed()
                .sorted()
                .toList();
        for (int i = 0; i < listA.size(); i++) {
            if (listA.get(i) == null ||
                    listB.get(i) == null ||
                    !Objects.equals(listA.get(i), listB.get(i))) {
                result = false;
                break;
            }
        }
        System.out.println("listA: " + listA);
        System.out.println("listB: " + listB);
        return result;
    }

    public static int[] between(int a, int b) {
        // your code here
        int [] result = IntStream.range(a,b + 1).toArray();
        return result;
    }


}
