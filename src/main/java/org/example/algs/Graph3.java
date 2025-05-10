package org.example.algs;
//weight graph with dfs
import java.util.*;

public class Graph3 {
    public Map<Integer, Map<Integer, Integer>> graph;
    public Set<Integer> visited = new LinkedHashSet<>();
    public Set<Integer> unvisited = new HashSet<>();
    List<List<Integer>> paths = new ArrayList<>();
    List<Integer> currentPath = new ArrayList<>();

    public Graph3(Map<Integer, Map<Integer, Integer>> graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {

        Graph3 graph3 = new Graph3(getInitialGraph());
        graph3.dfs(0,5);
        System.out.println("paths: " + graph3.paths);
        System.out.println("visited " + graph3.visited);
        List<Integer> weights = graph3.calculateWeights();
        System.out.println("weights: " + weights);
        System.out.println("min weight = " + weights.stream()
        .min(Integer::compareTo).get());
    }

    public void dfs(int current, int target) {
        System.out.println("current: " + current);
        visited.add(current);
        currentPath.add(current);
        if (current == target) {
            System.out.println("found path");
            paths.add(new ArrayList<>(currentPath));
            visited.remove(current);
            return;
        }
        Map<Integer, Integer> map = graph.get(current);
        map.forEach((k,v) -> {
            if (!visited.contains(k)) {
                dfs(k, target);
                currentPath.remove(currentPath.size() - 1);
                System.out.println("go back " + k);
            }
        });

    }

    public List<Integer> calculateWeights() {
        List<Integer> weights = new ArrayList<>();
        for (List<Integer> path : paths) {
            Integer currentWeight = 0;
            for (int i = 0; i < path.size() - 1; i++) {
               Map<Integer, Integer> currentMap = graph.get(path.get(i));
               Map<Integer, Integer> nextMap = graph.get(path.get(i + 1));
//               currentWeight += currentMap.get(path.get(i)) * nextMap.get(path.get(i + 1));
//                currentMap.get(path.get(i))
                int addedWeight = currentMap.get(path.get(i + 1));
                currentWeight = currentWeight + addedWeight;
                System.out.println("debug");
            }
            weights.add(currentWeight);
        }
        return weights;
    }

    public static Map<Integer, Map<Integer, Integer>> getInitialGraph() {
        Map<Integer, Map<Integer, Integer>> graph
                = new HashMap<Integer, Map<Integer, Integer>>();
        Map<Integer, Integer> map0 = new HashMap<>();
        map0.put(1, 0);
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(0, 0);
        map1.put(2, 3);
        map1.put(3, 7);
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(1,3);
        map2.put(3,1);
        map2.put(4,2);
        Map<Integer, Integer> map3 = new HashMap<>();
        map3.put(1,7);
        map3.put(2,1);
        map2.put(4,2);
        map3.put(5,4);
        Map<Integer, Integer> map4 = new HashMap<>();
        map4.put(2,2);
        map4.put(3,2);
        map4.put(5,4);
        Map<Integer, Integer> map5 = new HashMap<>();
        map5.put(3,4);
        map5.put(4,4);
        graph.put(0, map0);
        graph.put(1, map1);
        graph.put(2, map2);
        graph.put(3, map3);
        graph.put(4, map4);
        graph.put(5, map5);
        return graph;
    }

}
