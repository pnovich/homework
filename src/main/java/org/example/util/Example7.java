package org.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example7<P> {
    P object;
//    private Integer a;

    public Example7(P object) {this.object = object;}
    public P getObject() {
        return this.object;
    }
    public <F> P getObjectWithParams(F parameter1, P parameter2) {
        return this.object;
    }

    public static <L> void doL(L param) {
        System.out.println("param class = " + param.getClass().getName() + " = " + param );
    }

    public void doWorkWithIntList(List<Integer> list) {
        Object n = list.get(0);
        Integer newInteger = 11;
//        list.add(newInteger);
        System.out.println(n);
    }
    public<T extends Number> void doWorkWithInt(T a) {
        T n = a;
        System.out.println(n);
    }

    public void doWorkWithListOfClassClass(Class2 class2, List<? extends Class2> list) {
       Class2 receivedFromParams = list.get(0);
       Class2 newClass2 = new Class2(11);
       list.add(null);
       list.add(null);
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Example7<Integer> intObject = new Example7<>(1);
        Example7<String> stringObject = new Example7<>("string");
        int intres = intObject.object;
        String stringres = stringObject.getObject();
        System.out.println(intres);
        System.out.println(stringres);
        Boolean b = true;
        System.out.println(intObject.getObjectWithParams(b,3));
        doL("aaa");
        int n = 34;
        doL(n);
//        List<Integer> list1 = Arrays.asList(1,2,3);
//        stringObject.doWorkWithIntList(list1);
        Integer n1 = 5;
//        stringObject.doWorkWithInt(n1);
        Class2 c1 = new Class2(1);
        Class2 c2 = new Class2(2);
        Class2 c3 = new Class2(10);
        List<Class2> classList = new ArrayList<>();
        classList.add(c1);
        classList.add(c2);
        stringObject.doWorkWithListOfClassClass(c3,classList);
        System.out.println(classList);
    }

}

class Class1<Class1> {
    int a;
    public Class1(int a) {
        this.a = a;
    }
    @Override
    public String toString() {
        return this.hashCode() + " a = " + a;
    }
}
class Class2 extends Class1 {
    public Class2(int a) {
    super(a);
    }
}
class Class3 extends Class2 {
    public Class3(int a) {
        super(a);
    }
}