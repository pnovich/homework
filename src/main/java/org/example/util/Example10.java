package org.example.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example10 {
    public static long sumTwoSmallestNumbers(long[] numbers) {
        //Your solution here
        if (numbers.length == 0) {
            return 0;
        }
        if (numbers.length < 2) {
            return numbers[0];
        }
        List<Long> numbersList = Arrays.stream(numbers).boxed().sorted((a,b) -> b.compareTo(a)).toList();
        return numbersList.get(numbersList.size() - 1) + numbersList.get(numbersList.size() - 2);

    }

    public static long sumTwoSmallestNumbers2(final long [] numbers) {
        return Arrays.stream(numbers)
                .sorted()
                .limit(2)
                .sum();
    }
    public static long sumTwoSmallestNumbers3(long[] numbers) {
        //Your solution here
        PriorityQueue<Long> pq = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toCollection(PriorityQueue::new));

        return pq.remove() + pq.remove();
    }

    public static int [] doArray(int [] array) {
        int [] result = new int[array.length];
        result = Arrays.stream(array).sorted().toArray();
        return result;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) {
            return;
        }
        if (m == 0) {
            nums1[0] = nums2[0];
            return;
        }
        for (int i = 0; i < m; i++) {
            nums1[m + i] = nums2[i];
        }
        int [] result = Arrays.stream(nums1).sorted().toArray();
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = result[i];
        }
    }

    public static int findEvenIndex(int[] arr) {
        // your code
        int result = -1;
        int [] array1 = generateSums(arr, 1);
        int [] array2 = generateSums(arr, -1);
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] == array2[i]) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static int [] generateSums(int[] arr, int increment) {
     int [] result = new int[arr.length];
     int sum = 0;
     if (increment > 0) {
         for (int i = 0; i < arr.length; i++) {
         sum = arr[i] + sum;
         result[i] = sum;
         }
     } else {
         for (int i = arr.length - 1; i > -1; i--) {
             sum = arr[i] + sum;
             result[i] = sum;
         }
     }
     return result;
    }


    public static int countSmileys(List<String> arr) {
        // Just Smile :)
        int result =(int) arr.stream()
                .filter(Example10::isTheSmile)
                .count();
        return result;
    }

    public static boolean isTheSmile(String string) {
        boolean result = false;
        if (string.length() == 3)
                 {
                     if ((string.charAt(0) == ':' || string.charAt(0) == ';')
                     && (string.charAt(1) == '-' || string.charAt(1) == '~')
                     && (string.charAt(2) == ')' || string.charAt(2) == 'D')) {
                         result = true;
                     }
                 }
        if (string.length() == 2)
        {
            if ((string.charAt(0) == ':' || string.charAt(0) == ';')
                    && (string.charAt(1) == ')' || string.charAt(1) == 'D')) {
                result = true;
            }
        }
        return result;
    }

    public static int countSmileys2(List<String> arr) {
        return (int)arr.stream().filter( x -> x.matches("[:;][-~]?[)D]")).count();
    }

    public static char getCurrentChar(String s) {
        return s.charAt(0);
    }

    public static String smash(String... words) {
        // TODO Write your code below this comment please
        String result = "";
        result = Arrays.stream(words)
                .collect(Collectors.joining(" "));
        return  result;
    }

    public static int findIt(int[] a) {
        Map<Integer, Long> map =
                Arrays.stream(a)
                        .boxed()
                        .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        long result = map.get(a[0]);
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            Integer k = entry.getKey();
            Long v = entry.getValue();
            if (v % 2 == 1) {
                result = k;
            }
        }
        return (int)result;
    }

    public static int findIt2(int[] A) {
        int odd=0;
        for (int item: A)
        {
            odd = odd ^ item;// XOR will cancel out everytime you XOR with the same number so 1^1=0 but 1^1^1=1 so every pair should cancel out leaving the odd number out
        }

        return odd;
    }

    public static long findNb(long m) {
        // your code
        System.out.println("m = " + m);
        long i = 1;
        long sum = 1;
        long current = 1;
        boolean lessThenM = true;
        if (m == 1) {
            return 1;
        }
        if (m < 1) {
            return -1;
        }

        while (lessThenM) {
            current =(i + 1)*(i + 1)*(i + 1);
            sum = sum + current;
            if (sum >= m) {
                lessThenM = false;
                if (sum == m) {
                    return i + 1;
                } else {
                    return -1;
                }
            } else {
                i = i + 1;
            }
        }
        return i + 1;
    }


}
