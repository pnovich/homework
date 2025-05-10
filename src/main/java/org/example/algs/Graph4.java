package org.example.algs;

import java.util.*;

public class Graph4 {
    public Map<Integer, List<Integer>> graph;
    public Set<Integer> visited = new LinkedHashSet<>();
    public List<Integer> path = new LinkedList<>();
    public List<List<Integer>> allPaths = new LinkedList<>();
    Stack<Integer> stack = new Stack<>();

    public Graph4(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        Graph4 graph4 = new Graph4(getGraph());
        System.out.println("size: " + graph4.graph.size());
        graph4.dfs(0,3);
        graph4.stack.push(0);
        System.out.println("allPaths: " + graph4.allPaths);
        System.out.println("stack: " + graph4.stack);
        List<Integer> sortResult = graph4.sort(graph4.stack);
        System.out.println("sortResult: " + sortResult);
    }

    public List<Integer> sort(Stack<Integer> stack) {
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public void dfs(int current, int target) {
        visited.add(current);
        path.add(current);
        if (
//                current == target
                stack.size() == graph.size() -1
        ) {
            System.out.println("found path: " + path);
            System.out.println("visited: " + visited);
            System.out.println("stack: " + stack);
            allPaths.add(path);
            visited.remove(current);
        }
        List<Integer> children = graph.get(current);
        for (Integer child : children) {
            if (!visited.contains(child)) {
                System.out.println("child: " + child);
                dfs(child, target);
                stack.push(child);
                System.out.println("go back " + child);
                path.remove(path.size() - 1);
            }
        }
    }

    public static Map<Integer, List<Integer>> getGraph() {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        List<Integer> list0 = new ArrayList<Integer>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list0.add(3);
        list2.add(1);
        list3.add(1);
        list3.add(2);

//        list0.add(3);
//        list1.add(2);
//        list1.add(3);
//        list2.add(1);
//        list2.add(3);
//        list3.add(0);
//        list3.add(1);
//        list3.add(2);



//        List<Integer> list4 = new ArrayList<>();
//        List<Integer> list5 = new ArrayList<>();
//        list2.add(3);
//        list3.add(1);
//        list4.add(0);
//        list4.add(1);
//        list5.add(0);
//        list5.add(2);
        graph.put(0, list0);
        graph.put(1, list1);
        graph.put(2, list2);
        graph.put(3, list3);
//        graph.put(4, list4);
//        graph.put(5, list5);
        return graph;
    }
}
