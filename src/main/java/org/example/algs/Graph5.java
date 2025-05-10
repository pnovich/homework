package org.example.algs;

import java.util.*;

public class Graph5 {

    public static void main(String[] args) {
        Graph5 g5 = new Graph5();
        Graph g = g5.doFindWay();
        System.out.println("graph: " + g);
        System.out.println("result = " + g.getPathToNode("D"));
    }

    public Graph doFindWay() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        nodeA.addDestination(nodeB, 6);
        nodeA.addDestination(nodeC, 8);
        nodeB.addDestination(nodeC, 1);
        nodeC.addDestination(nodeB, 1);
        nodeB.addDestination(nodeD, 5);
        nodeC.addDestination(nodeD, 3);
        Graph graph = new Graph();
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph = calulateShortestPath(graph, nodeA);
        return graph;
    }

    public Graph calulateShortestPath(Graph graph, Node source) {
        source.setDistance(0);
        Set<Node> visited = new HashSet<>();
        Set<Node> unvisited = new HashSet<>();
        unvisited.add(source);
        while (!unvisited.isEmpty()) {
            Node current = getLowestDistanceNode(unvisited);
            unvisited.remove(current);
            for (Map.Entry<Node,Integer> entry: current.getAdjacentNodes().entrySet()) {
                Node adjacentNode = entry.getKey();
                Integer edgeWeight = entry.getValue();
                if (!visited.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, current);
                    unvisited.add(adjacentNode);
                }
            }
            visited.add(current);
        }

        return graph;
    }

    private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        if (sourceNode.getDistance() + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceNode.getDistance() + edgeWeight);
            LinkedList<Node> sourcePath = new LinkedList<>(sourceNode.getShortestPath());
            sourcePath.add(evaluationNode);
            evaluationNode.setShortestPath(sourcePath);
        }
    }

    private Node getLowestDistanceNode(Set<Node> unvisited) {
        Node lowestDistanceNode = null;
        Integer lowestDistance = Integer.MAX_VALUE;
        for (Node node : unvisited) {
            if (node.getDistance() < lowestDistance) {
                lowestDistance = node.getDistance();
                lowestDistanceNode = node;
            }
        }
      return lowestDistanceNode;
    }

}
    class Graph {
        Set<Node> nodes = new HashSet<>();
        public Graph() {}
        public void addNode(Node node) {
            nodes.add(node);
        }
        public List<Node> getPathToNode(String  nodeName) {
            List<Node> result = null;
            for (Node node : nodes) {
                if (node.getName().equals(nodeName)) {
                    result = node.getShortestPath();
                }
            }
            return result;
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "nodes=" + nodes +
                    '}';
        }
    }
    class Node {
        String name;
        List<Node> shortestPath = new LinkedList<Node>();
        Integer distance = Integer.MAX_VALUE;
        Map<Node, Integer> adjacentNodes = new HashMap<Node, Integer>();
        public Node(String name) {
            this.name = name;
        }
        public void addDestination(Node destination, Integer distance) {
            adjacentNodes.put(destination, distance);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
            this.adjacentNodes = adjacentNodes;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
//                    ", shortestPath=" + shortestPath +
                    ", distance=" + distance +
//                    ", adjacentNodes=" + adjacentNodes +
                    '}';
        }
    }

