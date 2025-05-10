package org.example.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example17 {
    public static void main(String[] args) {
//      Example17 example17 = new Example17();
//      int [] A = {5,3,5,6,5,4};
//      int K = 4;
//      int result = example17.solution(A, K);
//        System.out.println("result =" + result);
//        getGenericBinary(31);
        Class111 c1 = new Class111("C1111111");
        System.out.println("count = " + c1.getCount());
        Class111 c2 = new Class111("c2222222");
        System.out.println("count = " + c2.getCount());


    }

    public int solution(int[] A, int K) {
        // Implement your solution here
        int result = K;
        Set<Integer> set = IntStream.range(1,K +1)
                .boxed()
                .collect(Collectors.toSet());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length - K + 1; i++) {
           int count = 0;
           for (int j = i; j < i + K; j++) {
               if (set.contains(A[j])) {
                   count++;
               }
           }
           map.put(i, count);
        }
        System.out.println("map " + map);
        System.out.println("set =" + set);
        int result1 = 0;
        result1 = map.values().stream().map(e -> K - e).collect(Collectors.minBy(Comparator.naturalOrder())).get();
        return result1;
    }

//    public int solution(String S) {
//        // Implement your solution here
//        if (S == null || S.length() == 0) {
//            return 0;
//        }
//        Map<String,Long> map = new HashMap<String,Long>();
//        map = Arrays.stream(S.split(""))
//                .collect(Collectors.groupingBy(i -> i,Collectors.counting()));
//        System.out.println(map);
//        OptionalInt max = map.values().stream().mapToInt(Long::intValue).max();
//        int all = map.values().stream().mapToInt(Long::intValue).sum();
//        if (max.isPresent()) {
//            return all - max.getAsInt();
//        }
//
//    int result = 0;
//    return result;
//    }

    public static String binaryAddition(int a, int b){
        //your code here
        int sum = a + b;
        String result = integerToBinaryString(sum);
        return result;
    }

    public static String integerToBinaryString(int n){
//        String binaryString = Integer.toBinaryString(n);
        String result = "";
        int currentDigit = 0;
        int k = 0;
        long temp = 1;
        while (temp <= n) {
            temp = temp * 2;
            k++;
        }
        for (int i = k - 1; i > 0; i--) {
            currentDigit = n / ((int) Math.pow(2, i));
            result = result + currentDigit;
            n = n - currentDigit * (int)Math.pow(2, i);
        }

        result = result + n;
//        System.out.println("n = " + n + "result =" + result);
        return result;
    }


    public static String binaryAddition2(int a, int b) {
        int sum = a + b;
        int bit;
        String result = "";
        while (sum != 0) {
            bit = sum % 2;
            result = bit + result;
            sum = sum / 2;
        }
        return result;
    }

    public static String integerToBinaryString2(int n){
        String binaryString = Integer.toBinaryString(n);
        String result = "";
        int currentDigit = 0;

//        int i = 0;
//        while (n > 0) {
//            currentDigit =  (n % ((int)Math.pow(2,i)));
//            result = currentDigit + result;
//            n = n / ((int)Math.pow(2,i));
//            i++;
//        }
        int k = 0;
        int temp = 1;
        while (temp < n) {
            temp = temp * 2;
            k++;
        }
//         k--;

//
//        for (int i = k; i > -1; i--) {
////            currentDigit = n % ((int)Math.pow(2,k));
//            currentDigit = n / ((int)Math.pow(2,k));
//            result = result + currentDigit;
////            n = n / ((int)Math.pow(2,k));
//            n = n - (int)Math.pow(2,k);
//        }
        int hash = 0;
        for (int i = 0; i < k; i++) {
            if (i != 0) {
                currentDigit = n % ((int) Math.pow(2, i));
            } else {
                currentDigit = n % 2;
            }
            hash = hash + currentDigit;
            if (hash > 1) {
                hash = 1;
                currentDigit = 0;
            } else {
                currentDigit = hash;
                hash = 0;
            }

//            n = n - currentDigit * (int)Math.pow(2,i);
            n = n - currentDigit * (int)Math.pow(2,i);
            String stringNow = String.valueOf(currentDigit);
            if (i != k - 1) {
            result = stringNow + result;
            } else {
                result = new String(String.valueOf(n / n/((int) Math.pow(2, i)))) + result;
            }
//            if (i != 0) {
//            n = n - n/((int) Math.pow(2, i));
//            } else {
//                n = n - n % 2;
//            }
        }
        System.out.println("temp =" + temp + " k = " + k);
        System.out.println("result =" + result);
        System.out.println("binaryString = " + binaryString);
        return binaryString;
    }


    public static String getGenericBinary(int input) {
        String binaryString = Integer.toBinaryString(input);
//        System.out.println("binary string " + binaryString);
//        int test = 1011;
//        int realTest = Integer.parseInt(String.valueOf(test),2);
//        System.out.println("real test " + realTest);
        return binaryString;
    }


}

class Class111 {
    public static int count = 0;
    String name;
    Class111(String name) {
        this.name = name;
        count++;
    }
    public int getCount() {
        return count;
    }
}
