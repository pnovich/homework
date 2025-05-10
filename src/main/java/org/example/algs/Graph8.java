package org.example.algs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph8 {
    Map<Integer, List<Integer>> graph;
    Map<Integer, List<Integer>> weights;
    List<List<Integer>> ways;
    public Graph8() {
        graph = new HashMap<Integer, List<Integer>>();
        ways = new ArrayList<List<Integer>>();
        graph.put(1, new ArrayList<>(){{add(2); add(3);}});
        graph.put(2, new ArrayList<>(){{add(1); add(3);}});
        graph.put(3, new ArrayList<>(){{add(1); add(2); add(4);}});
        graph.put(4, new ArrayList<>(){{add(3);}});

        weights = new HashMap<>();
        weights.put(1, new ArrayList<>(){{add(1); add(3);}});
        weights.put(2, new ArrayList<>(){{add(1); add(1);}});
        weights.put(3, new ArrayList<>(){{add(3); add(1); add(5);}});
        weights.put(4, new ArrayList<>(){{add(5);}});
    }

    public static void main(String[] args) {
        Graph8 g = new Graph8();
        List<Integer> path = new ArrayList<>();
        g.dfs(1,4, path);
        System.out.println("ways = " + g.ways);
        System.out.println("--------------");
        g.findSimpleMinimalPath();
        System.out.println("--------------");
        g.findWeightedMinimalPath();

    }
    public void dfs(int start, int end, List<Integer> path) {
        path.add(start);
        if (start == end) {
            List<Integer> currentPath = new ArrayList<>(path);
            ways.add(currentPath);
        } else {
            List<Integer> children = graph.get(start);
            if (children != null && children.size() > 0) {
                for (Integer child : children) {
                    if (!path.contains(child)) {
                        dfs(child, end, path);
                        path.remove(path.size() - 1);
                    }
                }
            }
        }
    }

    public void findSimpleMinimalPath() {
        System.out.println("findSimpleMinimalPath");
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int length = 0;
        int currentNumber = 0;
        int currentLength;
        int minLength = Integer.MAX_VALUE;
        for (List<Integer> way : ways) {
            currentLength = 0;
            for (Integer current : way) {
                currentLength = currentLength + 1;
            }
            if (currentLength < minLength) {
                minLength = currentLength;
//                currentNumber = ways.indexOf(way);
                result = way;
            }
        }
        System.out.println("minimal path is : " + result);
        System.out.println("minimal length is : " + minLength);
    }

    public void findWeightedMinimalPath() {
        System.out.println("findWeightedMinimalPath");
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int length = 0;
        int currentNumber = 0;
        int currentLength;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < ways.size(); i++) {
            List<Integer> way = ways.get(i);
            currentLength = 0;
            for (int j = 0; j < way.size() - 1; j++) {
                int currentWeight = getWeightForTwoVerixes(way.get(j),way.get(j + 1));
                currentLength = currentLength + currentWeight;
                System.out.println("currentLength = " + currentWeight);
            }
            if (currentLength < minLength) {
                minLength = currentLength;
//                currentNumber = ways.indexOf(way);
                result = way;
            }
        }
        System.out.println("minimal path is : " + result);
        System.out.println("minimal length is : " + minLength);
    }

    public int getWeightForTwoVerixes(int first, int second) {
        int result = 0;
        int index = 0;
        List<Integer> neighbors = graph.get(first);
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i) == second) {
                index = i;
            }
        }
//        System.out.println("index = " + index);
        result = weights.get(first).get(index);
        return result;
    }

}
