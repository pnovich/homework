package org.example;

public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }

    public static void main(String[] args) {
        SharedResource sharedResource
                = new SharedResource();

        // Producer thread
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.produce(i);
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.consume();
            }
        }).start();
    }
}