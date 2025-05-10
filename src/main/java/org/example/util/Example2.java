package org.example.util;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example2 {
    public static void main(String[] args) {

        getIncrementedMap();
    }
    public static String autocorrect(String input) {
//        input = input.toLowerCase();
//        String regex1 = "[!._,'@?/]";
//        input = input.replaceAll(regex1, "");
        String endSymol = "";
        if (input.endsWith("!")) {
            endSymol = input.substring(input.length() - 1, input.length());
            input = input.substring(0, input.length() - 1);

        }
        Pattern p1 = Pattern.compile("[!._,'@?//s]");
        Pattern p = Pattern.compile("[!._,'@?/s]");
        List<String> list = Arrays.stream(input.split(" ")).toList();
        Map<Integer, String> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i, list.get(i));
        }
        System.out.println("map1 = " + map);
        Map<Integer, String> map2 = new LinkedHashMap<>();
        map.entrySet().forEach((entry) ->  {
            if (entry.getValue().toLowerCase().equals("you") ||
                    entry.getValue().toLowerCase().equals("u") ||
                    (entry.getValue().toLowerCase().contains("youu"))

            )  {
                map2.put(entry.getKey(), entry.getValue());
            }
        });

        System.out.println("map2 = " + map2);

        map2.entrySet().forEach(entry -> {
            map.put(entry.getKey(), "your sister");
        });

        System.out.println("map1 after changing = " + map);
        String result;

        if (map.size() > 1) {
            result = map.values().stream().collect(Collectors.joining(" "));
        } else {
            result = map.get(0);
        }
        // your code here
        return result + endSymol; // "corrected" input
    }

    public static void doExample111() {
        String string = "as as as sddfdfdfdfdf ffff";
        List<String> list = Arrays.asList(string.split(" "));
        System.out.println("doExample111 " +list);
        Set<String> set = list.stream()
                .filter(i -> !i.equals("sd"))
                .collect(Collectors.toSet());
        System.out.println("set = " + set);
        Map<String,Long> map = list.stream()

                .collect(Collectors.toMap(s-> {
                    return s;
                },s -> (long) s.length(),
                        (e,u) -> e, HashMap::new));
        map = map.entrySet().stream()
                        .sorted(new Comparator<Map.Entry<String, Long>>() {
                            @Override
                            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                                return o1.getKey().length() - o2.getKey().length();
                            }
                        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e,u) -> e, HashMap::new));
        System.out.println("Map = " + map);

        Map <String, Long> map2  = list.stream()
                .collect(Collectors.groupingBy(s->s, Collectors.counting()));
        System.out.println("map2 = " + map2);
        int [] a = {2,4,6,8,10};
        List<Integer> ints = new ArrayList<>();
        IntStream.range(0,4).forEach(i->{
            ints.add(a[i]);
                }
        );
        Map<Integer,Integer> map4 = new HashMap<>();
        System.out.println("intstream");
        IntStream.range(0,ints.size()-1).forEach(i-> {
            map4.put(i,a[i]);
        });
        Map<Integer,Integer> map5 = Arrays.stream(a).boxed().collect(Collectors.toMap(Function.identity(),t->t*2));
        System.out.println("ints = " + ints);
        System.out.println("map4 = " + map4);
        System.out.println("map5 = " + map5);
    }
    public static String autocorrect2(String input) {
        return input.replaceAll("(?i)\\b(u|you+)\\b", "your sister");
    }

    public static String autocorrect3(String input) {
        return input.replaceAll("\\b[Yy][Oo][Uu]+\\b|\\bu\\b","your sister");
    }

    public Object[] rotate(Object[] data, int n) {
        // Your code here
        Object[] result = new Object[data.length];
        int k;
        if (data.length == 0) {
          k = n;
        } else {
            k = n % data.length;
        }
        if (k < 0) {
            k = data.length + k;
        }
        System.out.println(k);
        List<Object> queue = new ArrayList<>();
        for (int i = data.length - k; i < data.length; i++) {
          queue.add(data[i]);
        }
        for (int i = 0; i < data.length - k; i++) {
            queue.add(data[i]);
        }
        System.out.println("queue = " + queue);
        for (int i = 0; i < data.length; i++) {
            result[i] = queue.get(i);
        }
        System.out.println("result = ");
        Arrays.asList(result).stream().forEach(System.out::println);
        return result;
    }

    public Object[] rotate2(Object[] data, int n) {
        Collections.rotate(Arrays.asList(data), n);
        return data;
    }

    public Object[] rotate3(final Object[] data, final int n) {
        final int length = data.length;
        final Object[] rotatedData = new Object[length];
        final int rotateBy = (n % length + length) % length;
        final int offset = length - rotateBy;
        System.arraycopy(data, 0, rotatedData, rotateBy, offset);
        System.arraycopy(data, offset, rotatedData, 0, rotateBy);
        return rotatedData;
    }

    public static void doRotate() {
        Object[] input = new Object[]{1, 2, 3, 4, 5};
        int n = -6;
        Example2 example2 = new Example2();
        Object[] result = example2.rotate(input, n);
    }

    public static void linkedListTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        System.out.println(list);
        System.out.println(list.pollLast());
        System.out.println(list);
    }

    public static void getIncrementedMap() {
        List<String> strings = Arrays.asList("aaa","bbbb","cc");
        Map<Integer, Integer> map =
                IntStream.range(0,strings.size())
                        .boxed()
                        .collect(Collectors.toMap(i -> i, i -> strings.get(i).length()));
        System.out.println("map = " + map);
    }

}
