package org.example.algs;

import java.util.*;

public class Graph7 {
    Map<Integer, List<Integer>> graph;
//    Set<Integer> visited;

//    Set<Integer> globalVisitedSet;
    List<List<Integer>> ways;
    public Graph7() {
        graph = new HashMap<Integer, List<Integer>>();

//        visited = new HashSet<Integer>();

//        globalVisitedSet = new HashSet<>();
        ways = new ArrayList<>();
        graph.put(1, new ArrayList<>() {{ add(2); add(3); }});
        graph.put(2, new ArrayList<>() {{ add(1);add(3); }});
        graph.put(3, new ArrayList<>() {{ add(1); add(2); add(4); }});
        graph.put(4, new ArrayList<>() {{ add(3); }});
    }

    public static void main(String[] args) {
        Graph7 g = new Graph7();
        List<Integer> path = new ArrayList<>();
        g.dfs(1,4, path);
        System.out.println("ways = " + g.ways);
//        System.out.println("globalVisitedSet = " + g.globalVisitedSet);

//        System.out.println("visited stop = " + g.visited);
    }

    public void dfs(int start, int end, List<Integer> path) {
        System.out.println("current path = " + path);

//        visited.add(start);

        path.add(start);
        System.out.println("current path after adding start = " + path);
//        System.out.println("visited = " + visited);
        if (start == end) {
            List<Integer> currentPath = new ArrayList<>(path);
            ways.add(currentPath);
            System.out.println("out");
        }
        else
        {
            List<Integer> children = graph.get(start);
            if (children != null && !children.isEmpty()) {
                for (Integer child : children) {
                    if (!path.contains(child)) {
                        System.out.println("child = " + child);
                        dfs(child, end, path);
                        path.remove(path.size() - 1);
//                        visited.remove(child);
                        System.out.println("return from recursion, path = " + path);
//                        System.out.println("return from recursion, visited = " + visited);
                    }
                }
            }
        }
    }
}
