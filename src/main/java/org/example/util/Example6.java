package org.example.util;

import java.util.*;

public class Example6 {
    public static void main(String[] args) {
        Animal animal = new Animal();
//        try {
//            animal.eat();
//        } catch (Throwable e ) {
//            e.printStackTrace();
//        }
        Set<Integer> set = animal.getAnimalsNumbers();
        set.add(1);
        System.out.println(set);
    }

}

class Animal {
    public void eat() {
        System.out.println("Animal eat");
    }

    public HashSet<Integer> getAnimalsNumbers() {
       HashSet<Integer> numbers = new HashSet<>();
       numbers.add(1);
       return numbers;
    }
}

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("Dog eat");
    }


    public HashSet<Integer> getAnimalsNumbers() {
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(2);
        return numbers;
    }
}