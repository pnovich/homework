package org.example.util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example14 {
    public static String longestConsec(String[] strarr, int k) {
        // your code
        String result = "";
        List<String> list = Arrays.stream(strarr).toList();
        Map<String, Integer> map = IntStream.range(0, strarr.length - k + 1)
                .boxed()
                .collect(Collectors.toMap(
                        i -> {
                            String s = "";
                            for (int j = 0; j < k; j++) {
                                s = s + list.get(i + j);
                            }
                            return s;
                        },
                        i -> {
                            int l = 0;
                            for (int j = 0; j < k; j++) {
                                l = l + list.get(i + j).length();
                            }
                            return l;
                        },
                        (a,b) -> a,
                        LinkedHashMap::new));
        BiConsumer<Map<String, Integer>, Integer> accumulator1 =
                new BiConsumer<Map<String, Integer>, Integer>() {
                    @Override
                    public void accept(Map<String, Integer> stringIntegerMap, Integer integer) {
                        stringIntegerMap.put(list.get(integer),integer);
                        System.out.println("done, integer = " + integer );
                    }
                };

        BinaryOperator<Map<String, Integer>> combiner1 =
                new BinaryOperator<Map<String, Integer>>() {
                    @Override
                    public Map<String, Integer> apply(Map<String, Integer> stringIntegerMap, Map<String, Integer> stringIntegerMap2) {
                        stringIntegerMap.putAll(stringIntegerMap2);
                        return stringIntegerMap;
                    }
                };

        Function<Map<String,Integer>, Integer> finisher12 =
                new Function<Map<String,Integer>, Integer>() {
                    @Override
                    public Integer apply(Map<String,Integer> stringIntegerMap) {
                        System.out.println("inside fin apply method");
                        var v = stringIntegerMap.entrySet().stream()
                                .map(e -> e.getValue())
                                .findFirst()
                                .get();
                        Integer res = v;
                        return res;

                    }
                };
//                i -> {
//                    System.out.println("fin");
//                    int r =  (int) i.values().stream().count();
//
//                    var v = i.entrySet().stream()
//                            .map(e -> e.getValue())
//                            .findFirst()
//                            .get();
//                    Integer res = (Integer) v;
//                    return res;
//                };

        Collector<Integer,?, Map<String,Integer>> collector1 =
                Collector.of(
                        () -> new HashMap<String,Integer>(),
                        accumulator1,
                        combiner1,
                        Collector.Characteristics.IDENTITY_FINISH);

        Collector<Integer,?, Integer> collector12 =
                Collector.of(
                        () -> new HashMap<String,Integer>(),
                        accumulator1,
                        combiner1,
                        m -> {
                            return finisher12.apply(m);
                        });



        Map<String, Map<String, Integer>> map1 = IntStream.range(0, strarr.length - k + 1)
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> list.get(i),
                        collector1
                       ));

        Map<String, Integer> map12 = IntStream.range(0, strarr.length - k + 1)
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> list.get(i),
                        collector12
                ));


        BiConsumer<Integer, Integer> accumulator2 =
                new BiConsumer<Integer, Integer>() {
                    @Override
                    public void accept(Integer main, Integer integer) {
                        main = main + integer;
                    }
                };

        BinaryOperator<Integer> combiner2 =
                new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer stringIntegerMap, Integer stringIntegerMap2) {
                        stringIntegerMap = stringIntegerMap2 + stringIntegerMap;
                        return stringIntegerMap;
                    }
                };
        Supplier<Integer> supplier2 =
                new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return 0;
                    }
                };
        Collector<Integer,?, Integer> collector2 =
                Collector.of(
                        supplier2,
                        accumulator2,
                        combiner2,
                        Collector.Characteristics.IDENTITY_FINISH);

        Map<String, Integer> map2 = IntStream.range(0, strarr.length - k + 1)
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> list.get(i),
                        collector2
                ));


        Optional<Integer> max =map.values().stream()
                .max(Integer::compareTo);
        if (!max.isPresent()) {
            return "";
        } else {
            int actualMax = max.get();
            Map.Entry<String,Integer> entry = map.entrySet().stream()
                    .filter(e -> e.getValue() == actualMax)
                    .findFirst()
                    .get();
            result = entry.getKey();
        }
        return result;
    }

}
