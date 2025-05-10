package org.example.util;

import javax.management.RuntimeErrorException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example1 {
    public static void main(String[] args) {

        doExample1();
    }

    public static String getWithException() {
        try {
            System.out.println("wiil try to throw error");
            throw new Error("This is an error");
        } catch (Throwable e) {
            System.out.println("Exception caught: " + e);
            e.printStackTrace();
        }
        return "Hello World";
    }

    public static void doExample1() {
    Map map = putStringToMap("e e ss ss ss er");
        System.out.println(map);
    }

    public static Map <String, Long> putStringToMap(String input) {
        List<String> list = stringToList(input);
        Map <String, Long> map = putListToMap(list);
        return map;
    }

     public static Map<String, Long> putListToMap(List <String> list) {
        Map <String, Long> map = list.stream().collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        Map <String, Long> result = map.entrySet().stream().sorted(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return result;
     }
     public static List <String> stringToList(String input) {
        List <String> list = Arrays.stream(input.split(" ")).toList();
        return list;
     }

     public static void doExample2() {
        String input = "Hello World";
         Stream<String> st = Arrays.stream(input.split(" "));
//         List<String> list = st.filter(i -> !i.equals("World")).collect(Collectors.toList());
         List<String> list2 = st.collect(Collectors.toList());

//         System.out.println(list);
         System.out.println(list2);
     }

}
