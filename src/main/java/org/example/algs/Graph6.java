package org.example.algs;

import java.util.*;

public class Graph6 {
    Map<Integer, List<Integer>> graph;
    Set<Integer> visited;
    List<List<Integer>> ways;
    public Graph6() {
        graph = new HashMap<Integer, List<Integer>>();
        visited = new HashSet<Integer>();
        ways = new ArrayList<>();
        graph.put(1, new ArrayList<>() {{ add(2); add(3); }});
        graph.put(2, new ArrayList<>() {{ add(1);add(3); }});
        graph.put(3, new ArrayList<>() {{ add(1); add(2); add(4); }});
        graph.put(4, new ArrayList<>() {{ add(3); }});
    }

    public static void main(String[] args) {
        Graph6 g = new Graph6();
        List<Integer> path = new ArrayList<>();
        g.dfs(1,4, path);
        for (List<Integer> w : g.ways) {
            System.out.println(w);
        }
    }

    public void dfs(int start, int end, List<Integer> path) {
        System.out.println("current path = " + path);
    path.add(start);
    visited.add(start);
        if (start == end) {
          List<Integer> currentPath = new ArrayList<>(path);
          ways.add(currentPath);
        } else {
           List<Integer> children = graph.get(start);
           if (children != null && !children.isEmpty()) {
               for (Integer child : children) {
                   if (!visited.contains(child)) {
                       dfs(child, end, path);
                   }
               }
           }
        }
    }
}
