package org.example.algs;

import java.util.*;

public class Graph1 {
    int x = 7;
    int y = 8;
    int k = 5;
    int min = 0;
    int max = 6;
    Set<Integer> visited = new HashSet<>();
    List<List<Integer>> ways = new ArrayList<>();
    Map<Integer, List<Integer>> inputMap;
    Map<Integer, List<Integer>> changedMap;
    public static void main(String[] args) {
        int [][] inputArray = {
                {0,1},
                {0,4},
                {0,6},
                {2,3},
                {2,4},
                {3,4},
                {5,1},
                {5,6}
        };
        Graph1 graph = new Graph1();
        graph.inputMap = getData(inputArray);
        graph.changedMap = new HashMap();
        graph.changedMap.putAll(graph.inputMap);
        int startLength = 0;
        List<Integer> way = new ArrayList<>();
        graph.doAction(startLength,graph.min, 100,
                graph.changedMap.get(graph.min),
                way,
                graph.ways
                );
        System.out.println(graph.ways);

    }
    public static Map<Integer, List<Integer>> getData(
            int [][] inputArray
    ) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < inputArray.length; i++) {
            int v1 = inputArray[i][0];
            int v2 = inputArray[i][1];
            if (!map.containsKey(v1)) {
                map.put(v1, new ArrayList<Integer>());
                map.get(v1).add(v2);
            } else {
                map.get(v1).add(v2);
            }

            if (!map.containsKey(v2)) {
                map.put(v2, new ArrayList<Integer>());
                map.get(v2).add(v1);
            } else {
                map.get(v2).add(v1);
            }
        }
        map.entrySet().forEach(
                entry -> {
//                    System.out.println(entry.getKey() + " " + entry.getValue());
                    List<Integer> nearest = entry.getValue();
                    entry.setValue(nearest.stream()
                            .sorted().toList());
                }
        );
        return map;
    }

    public void doAction(int n, int currentNumber, int prev,
                         List<Integer> nearest,
                         List<Integer> way, List<List<Integer>> ways
    ) {
        if (n == k) {
            if (currentNumber == max) {
//                ways.add(way);
                return;
            }
            List<Integer> newWay = new ArrayList<>(way);
            this.ways.add(newWay);
//            List<Integer> l = changedMap.get(n).stream()
//                    .filter(e -> e != way.get(way.size() -1))
//                    .toList();
//            changedMap.put(n,l);
            visited.add(currentNumber);
//            way.remove(way.size() - 1);

            return;
        } else {
            if (!nearest.isEmpty()) {
                for (int i = 0; i < nearest.size(); i++) {
//                    way.add(currentNumber);
                    int nxt = nearest.get(i);
                    if (nxt != prev && !visited.contains(nxt)) {
                        way.add(currentNumber);
                        System.out.println("go ahead " + currentNumber + " " + way + " ways " + ways + " visited " + visited);
                        doAction(n + 1, nxt, currentNumber, changedMap.get(nearest.get(i)), way, ways);
                        System.out.println("going back " + currentNumber + " " + way + " ways " + ways + " visited " + visited);
                        way.remove(way.size() - 1);
                    }
                }
            }
        }
    }
}
