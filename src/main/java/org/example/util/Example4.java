package org.example.util;

public class Example4 {
    public int sum;
    public static void main(String[] args) {
        Example4 example4 = new Example4();
        MyNode nodeA = new MyNode(1, "a");
        MyNode nodeB = new MyNode(2, "b");
        MyNode nodeC = new MyNode(3, "c");
        MyNode nodeD = new MyNode(4, "d");
        MyNode nodeE = new MyNode(5, "e");
        MyNode nodeF = new MyNode(6, "f");
        MyNode nodeG = new MyNode(7, "g");
        nodeA.right = nodeC;
        nodeA.left = nodeB;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        System.out.println(nodeA);

        example4.findInTree(nodeA);

    }

    public void findInTree(MyNode node) {
        find(node);
        System.out.println("sum  value = " + this.sum);
    }
     public void find(MyNode node) {
         this.sum = this.sum + node.value;
         System.out.println("node " + node.name + " has balue = " + node.value);
         if (node.right == null && node.left == null) {
                  return;
         }
     if (node.left != null) {
         find(node.left);
     }
     if (node.right != null) {
         find(node.right);
     }
     }
}

class MyNode {
    String name;
    int value;
    MyNode left;
    MyNode right;
    boolean isVisited;
    public MyNode(int value, String name) {
        this.value = value;
        this.right = this.left = null;
        this.name = name;
        this.isVisited = false;

    }
    public String toString() {
        return ("node " + this.name + " value = " + this.value + " left = { " + this.left + " }, right = { " + this.right + " }");
    }
}
