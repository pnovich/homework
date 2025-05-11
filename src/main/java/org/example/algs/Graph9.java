package org.example.algs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph9 {
    public Map<Integer, Map<Integer, Integer>> graph;
    public List<List<Integer>> ways;
    public Graph9() {
        graph = new HashMap<Integer, Map<Integer, Integer>>();
        graph.put(1, new HashMap<>(){{put(2,1); put(3,3);}});
        graph.put(2, new HashMap<>(){{put(1,1); put(3,1);}});
        graph.put(3, new HashMap<>(){{put(1,3); put(2,1); put(4,5);}});
        graph.put(4, new HashMap<>(){{put(3,5);}});
        ways = new ArrayList<>();
    }

    public static void main(String[] args) {
        Graph9 g = new Graph9();
        System.out.println(g.graph);
        System.out.println("------------------");
        List<Integer> path = new ArrayList<>();
        g.dfs(1,4, path);
        System.out.println("ways = " + g.ways);
        System.out.println("minimal simple path  = "
                + g.getMinimalSimplePath(g.ways));
        System.out.println("minimal weight path  = "
                + g.getMinimalWeightPath(g.ways));
    }

    public void dfs(int start, int end, List<Integer> path) {
        path.add(start);
        if (start == end) {
            List<Integer> currentPath = new ArrayList<>(path);
            System.out.println(currentPath);
            ways.add(currentPath);
        } else {
            List<Integer> nextPath = new ArrayList<>();
            Map<Integer, Integer> nextMap = graph.get(start);
            System.out.println("next map = " + nextMap);
            nextPath.addAll(nextMap.keySet());
            if (nextPath != null && nextPath.size() > 0) {
                for (Integer next : nextPath) {
                    if (!path.contains(next)) {
                        dfs(next, end, path);
                        path.remove(path.size() - 1);
                    }
                }
            }
        }
    }

    List<Integer> getMinimalWeightPath(List<List<Integer>> allWays) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < allWays.size(); i++) {
            int iterationCounter = 0;
            for (int j = 0; j < allWays.get(i).size() - 1; j++) {
                int currentWeight = getWeightForTwoVertexes(allWays.get(i).get(j),
                        allWays.get(i).get(j + 1));
                iterationCounter = iterationCounter + currentWeight;
            }
            if (iterationCounter < min) {
                min = iterationCounter;
                minIndex = i;
            }
        }
        return ways.get(minIndex);
    }

    public int getWeightForTwoVertexes(int first, int second) {
        int result = graph.get(first).get(second);
        return result;
    }

    List<Integer> getMinimalSimplePath(List<List<Integer>> allWays) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < allWays.size(); i++) {
            int iterationCounter = 0;
            for (int j = 0; j < allWays.get(i).size(); j++) {
                iterationCounter = iterationCounter + 1;
            }
            if (iterationCounter < min) {
                min = iterationCounter;
                minIndex = i;
            }
        }
        return ways.get(minIndex);
    }

}
