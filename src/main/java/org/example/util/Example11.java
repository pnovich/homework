package org.example.util;

import java.util.List;
import java.util.stream.IntStream;

public class Example11 {
    public static void main(String[] args) {
        Example11 example11 = new Example11();
        example11.doWork();
    }

    public void doWork() {
        int i = 1;
        A a = new A(1);
        System.out.println(A.doA1(i, a));
        System.out.println("i = " + i);
        System.out.println("a.i = " + a.i);
        a.doA2();
        A aa = new B();
        try {
         aa.doA3();
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}
class A {
int i = 5;
public A() {
    super();
}
public A(int i) {
    this.i = i;
}
static A doA1(int i, A a) {
    a.i++;
    i++;
    return new A(a.i);
}

public void doA2() {
    int[] array = new int[10];
    List<Integer> list = IntStream.range(5,12)
//            .skip(4)
            .boxed()
            .toList();
    System.out.println(list);
    try {
        int res = 10 / 0;
        array[-1] = res;
    } catch (ArithmeticException e) {
        System.out.println("ArithmeticException");
//    } catch (RuntimeException runtimeException) {
//        System.out.println("RuntimeException");
    } catch (NullPointerException nullPointerException) {
        System.out.println("NullPointerException");
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
        System.out.println("IndexOutOfBoundsException");
    }
}

public void doA3() throws ArithmeticException {
    int o = getA4();
    System.out.println("a doA3");
}

public int getA4() throws ArithmeticException{
    int result = 10/0;
    System.out.println("a getA4");
    return result;
}
}

class B extends A {

    @Override
    public void doA3() throws RuntimeException{
    System.out.println("b doA3");
    }
}

