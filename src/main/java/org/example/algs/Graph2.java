package org.example.algs;
//dfs,bfs,  robot on xy with dfs
import java.util.*;

public class Graph2 {
    public Map<Integer, List<Integer>> graph;
    public Set<Integer> visited = new LinkedHashSet<>();
    public List<List<Integer>> paths = new ArrayList<List<Integer>>();
    public  List<List<Integer>> bfsFinalPaths = new ArrayList<>();
    public  List<List<Integer>> dfsFinalPaths = new ArrayList<>();
    Map<Integer,Integer> parenttMap = new HashMap<>();

    public Graph2(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {

        List<Integer> list1 = List.of(2);
        List<Integer> list2 = List.of(3, 4, 6 );
        List<Integer> list3 = List.of(2, 4);
        List<Integer> list4 = List.of(3, 5, 6);
        List<Integer> list5 = List.of(4, 7);
        List<Integer> list6 = List.of(2, 4, 7);
        List<Integer> list7 = List.of(5, 6);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, list1);
        graph.put(2, list2);
        graph.put(3, list3);
        graph.put(4, list4);
        graph.put(5, list5);
        graph.put(6, list6);
        graph.put(7, list7);

//        Map<Integer, List<Integer>> graph22  = new HashMap<>();
//        graph22.put(1, new ArrayList<>() {{ add(2); add(3); }});
//        graph22.put(2, new ArrayList<>() {{ add(1);add(3); }});
//        graph22.put(3, new ArrayList<>() {{ add(1); add(2); add(4); }});
//        graph22.put(4, new ArrayList<>() {{ add(3); }});


        Graph2 graph2 = new Graph2(graph);
//        System.out.println(" r =" + r);
        int [][] matrix1 = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        int [][] matrix2 = {
                {1,1,1,1},
                {1,1,0,1},
                {1,1,1,1}
        };

        Set<Integer> set = new HashSet<>();
        int ccc = 0;
        for (int i = 0; i < matrix1.length; i ++){
            for (int j = 0; j < matrix1[0].length; j++) {
                ccc++;
                if (matrix2[i][j] == 0) {
                    set.add(ccc);
                }
            }
        }
//        set.add(4);
        Map<Integer, List<Integer>> graphXY = graph2.getGraphXY(matrix2, set);
        System.out.println("graphXY = " + graphXY);
        graph2.setGraph(graphXY);
//        graph2.setGraph(graph22);
//        graph2.findWays(1,4);
        graph2.findWays(3,10);

    }

    public void findWays(int start, int fin) {
        System.out.println("dfs");
        visited.clear();
        dfs(start, fin);
        paths.forEach(System.out::println);

//        System.out.println("bfs");
//        visited.clear();
//        bfs(start, fin);
//
//        System.out.println("set = " + this.visited);
//        System.out.println("paths = " + this.paths);
//        System.out.println("bfsFinalPaths = " + this.bfsFinalPaths);
//        System.out.println("dfsFinalPaths = " + this.dfsFinalPaths);
//        System.out.println("parentMap = " + this.parenttMap);
//        List<Integer> bfsPath = this.getBFSPathFromMap(this.parenttMap, start, fin);
//        System.out.println("bfsPath = " + bfsPath);

//        return this.paths;
    }

    public void dfs(int start, int end) {
        visited.add(start);
        List<Integer> list = new ArrayList<>();
        if (!paths.isEmpty()) {
             paths.get(paths.size() - 1).forEach(x -> {
                 list.add(x);
             });
        }
        list.add(start);
        paths.add(list);
        if (start == end) {
            System.out.println("found path: " + paths.get(paths.size() - 1));
            dfsFinalPaths.add(new ArrayList<>(paths.get(paths.size() - 1)));
            visited.remove(end);
            return;
        }
        List<Integer> neighbors = graph.get(start);
        if (neighbors != null) {
            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    System.out.println("next: " + neighbor);
                    dfs(neighbor, end);
                    if (!paths.isEmpty()) {
                        paths.get(paths.size() - 1).remove(paths.get(paths.size() - 1).size() - 1);
                    }
                    System.out.println("go back " + neighbor);
                }
            }
        }
    }

    public void bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            List<Integer> neighbors = graph.get(cur);
            System.out.println("neighbors = " + neighbors);
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
//                    parenttMap.put(neighbor, cur);
                    if (neighbor.equals(end)) {
                        parenttMap.put(neighbor, cur);
                        System.out.println("found path");
                        break;
                    }
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        parenttMap.put(neighbor, cur);
                    }
                }
            }
        }
    }

    List<Integer> getBFSPathFromMap(Map<Integer,Integer> map, int start, int fin) {
        List<Integer> list = new ArrayList<>();
        list.add(fin);
        int itrator = fin;
        while (map.get(itrator) != null) {
            list.add(map.get(itrator));
            itrator = map.get(itrator);
        }
        List<Integer> revertedList = new ArrayList<>();
        for (int i = list.size() - 1; i > -1; i--) {
         revertedList.add(list.get(i));
        }
        return revertedList;
    }

    public Map<Integer,List<Integer>> getGraphXY(int [] [] inputMatrix, Set<Integer> set) {
        int n = -1;
        int x = inputMatrix[0].length;
        int y = inputMatrix.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < y ; i++) {
            for (int j = 0; j < x; j++) {
                n++;
                map.put(n, new ArrayList<>());
                if (i > 0 && inputMatrix[i - 1] [j] != 0) {
                    if (!map.get(n).contains(n - x)) {
                        map.get(n).add(n - x);
                    }
                    if (!map.get(n - x).contains(n)) {
                        map.get(n - x).add(n);
                    }
//                   map.put(n,(map.get(n)).add(n - x));
                }
                if (i < y - 1 && inputMatrix[i + 1] [j] != 0) {
                    map.get(n).add(n + x);
                    map.getOrDefault(n + x, new ArrayList<>()).add(n);
                }
                if (j > 0 && inputMatrix[i] [j - 1] != 0) {
                    if (!map.get(n).contains(n - 1)) {
                        map.get(n).add(n - 1);
                    }
                    if (!map.get(n - 1).contains(n)) {
                        map.get(n - 1).add(n);
                    }
                }
                if (j < x - 1 && inputMatrix[i] [j + 1] != 0) {
                    map.get(n).add((n + 1));
                    map.getOrDefault(n + 1, new ArrayList<>()).add(n);
                }

            }
        }
        map.forEach((k,v) -> {

            List<Integer> l =v.stream()
                    .filter(e -> !set.contains(e))
                    .toList();
            map.put(k,l);
        });
//        map.forEach((k,v) -> {
//            if (set.contains(k)) {
//                map.remove(k);
//            }
//        });
        for (int i = 0; i < map.size(); i++) {
            if (set.contains(i)) {
                map.remove(i);
            }

        }
        return map;
    }

    public void setGraph(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }
}
