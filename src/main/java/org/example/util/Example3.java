package org.example.util;

import java.util.*;
import java.util.stream.IntStream;

public class Example3 {
    public int recursiveVoidResult;
    public Integer max = -1;
    public static void main(String[] args) {
        Example3 example3 = new Example3();
//        example3.recursiveVoidResult = 1;
//        System.out.println("result = " + example3.fac(3));
//        System.out.println(" void recursion :");
//        example3.fac2(3);
//        System.out.println("void recursion result " + example3.recursiveVoidResult);
//
//        System.out.println("hanoi towers: ");
//        example3.doHanoiAlgorythm();

//        int result = example3.fibo3(10);
//        System.out.println("result = " + result);

//        example3.doFibo4();
        example3.doFindMax();
    }

    public int fac(int n) {
        int result;
        if (n <= 1) {
            result = 1;
            return result;
        } else {
            System.out.println("befroe recursive call");
            result = n * fac(n - 1);
            System.out.println("after recursive call");
        }
        return result;
    }

    public void fac2(int n) {
        if (n <= 1) {
            recursiveVoidResult = 1;
        } else  {
            fac2(n -1);
            recursiveVoidResult = recursiveVoidResult * n;
        }
    }

    public void doHanoiAlgorythm() {
        LinkedList<Integer> c = new LinkedList<>();
        LinkedList<Integer> h = new LinkedList<>();
        LinkedList<Integer> t = new LinkedList<>();
        c.add(8);
        c.add(7);
        c.add(6);
        c.add(5);
        c.add(4);
        c.add(3);
        c.add(2);
        c.add(1);
        int n = 8;
        move(8,c,t,h);
    }

    public void move(int n, LinkedList<Integer> c, LinkedList<Integer> t, LinkedList<Integer> h) {
        if (n <= 2) {
            Integer cpop = c.pollLast();
            h.add(cpop);
            System.out.println("push " + cpop + " to " + h);
            cpop = c.pollLast();
            t.add(cpop);
            System.out.println("push " + cpop + " to " + t);
            Integer hpop = h.pollLast();
            t.add(hpop);
            System.out.println("push " + hpop + " to " + t);
        } else {
            Integer cpollFirst = c.pollFirst();
            System.out.println("before recursive call, the biggest is " + cpollFirst);
            move(n-1,c,h,t);
            System.out.println("after first recursive call, moving  biggest to " +t + "value = " + cpollFirst);
            t.add(cpollFirst);
            System.out.println("before secind recursive call, push " + cpollFirst + " to " + t);
            move(n-1,h,t,c);
        }

    }

    public int fibo(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n > 2) {
           return fibo(n - 2) + fibo(n - 1);
        }
        return 0;
    }

    public void doFibo4() {
        int n = 10;
        Integer result = 0;
        List<Integer> list = Arrays.asList(0,0,0,0,0,0,0,0,0,0);
        fibo4(n, list);
        System.out.println(list);
    }

    public void fibo4(int n, List<Integer> list) {
        if (n == 1) {
//            result = 0;
//            list.add(0,0);

            list.set(n -1,0);
        } else if (n == 2) {
            list.set(n - 1,1);
//            result = 1;
        } else if (n > 2) {
            fibo4(n - 1, list);
            fibo4(n - 2, list);
            list.set(n - 1,list.get(n - 2) + list.get(n - 3));
//           Integer tempResult1 = result;
//           fibo4(n -1, tempResult1);
//           Integer tempResult2 = result;
//           fibo4(n -2, tempResult2);
//           result = tempResult1 + tempResult2;
//            System.out.println("current result = " + result);
        }
    }

    public int fibo2(int n) {
        int result = 0;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(0,0);
        numbers.add(1,1);
        for (int i = 2; i < n; i++) {
            int current = numbers.get(i - 1) + numbers.get(i - 2);
            numbers.add(current);
        }
        result = numbers.get(n - 1);
        return result;
    }

    public int fibo3(int n) {
        ArrayList<Integer> result =
                IntStream.range(0,n)
                        .boxed()
                        .collect(ArrayList::new, this::sumTwoPrevious, ArrayList::addAll);
        return result.get(result.size() - 1);
    }
    public void sumTwoPrevious(ArrayList<Integer> a, int b) {
        if (a.size() == 0) {
            a.add(0);
        } else if (a.size() == 1 ) {
            a.add(1);
        } else if (a.size() > 1) {
            a.add(a.get(a.size() - 1) + a.get(a.size() - 2));
            System.out.println("b = " + b);
        }

    }

    public void doFindMax() {
        List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8);
        findMax(list.size() - 1,  list);
        System.out.println("max = " + max);
    }

    public void findMax(int n,  List<Integer> list) {
        if (n <= 1) {
            if (list.get(n) > this.max) {
                this.max = list.get(n);
                System.out.println("changed max for " + max);
            }
        } else if (n > 1) {
            findMax(n - 1,  list);
            if (list.get(n) > max) {
                max = list.get(n);
            }
            System.out.println("current max = " + max);
        }
    }

}
