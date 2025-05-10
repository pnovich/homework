package org.example.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Task12 {
    int [] numbers = {1,2,3,4,5,6,7,8,9};
    int min = numbers[0];
    int max = numbers[0];

    class FindMax implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            for(int i = 0; i <= numbers.length -1; i++){

                System.out.println(Thread.currentThread().getName() + " MaxTask " + numbers[i] +
                        " current max = " + max);

                if(numbers[i] > max){
                    max = numbers[i];
                }
            }
            System.out.println("MaxTask finished in Callable, max = " + max);
            return max;
        }
    }

    class FindMin implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            for(int i = 0; i <= numbers.length -1; i++){

                System.out.println(Thread.currentThread().getName() + " MinTask " + numbers[i] +
                        " current min = " + min);

                if(numbers[i] < min){
                    min = numbers[i];
                }
            }
            System.out.println("MinTask finished in Callable, min = " + min);
            return min;
        }
    }
    public  void startPool(ExecutorService executor) {
        System.out.println("started pool executor");
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        List<Callable> tasks = new ArrayList<Callable>();
        Callable<Integer> task1 = new FindMax();
        Callable<Integer> task2 = new FindMin();
//        tasks.add(task1);
//        tasks.add(task2);
        executor.submit(task1);
        executor.submit(task2);

    }

    public void startCompletableFuture(ExecutorService executorService) {
        System.out.println("completableFuture solution: ");
        Callable<Integer> task1 = new FindMax();
        Callable<Integer> task2 = new FindMin();
        Supplier<Integer> supplierMin = () -> {
            try {
                return new FindMin().call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        Supplier<Integer> supplierMax = () -> {
            try {
                return new FindMax().call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        CompletableFuture<Integer> futureMin = CompletableFuture.supplyAsync(supplierMin, executorService);
        CompletableFuture<Integer> futureMax = CompletableFuture.supplyAsync(supplierMax, executorService);
        futureMax.thenAccept(max -> {
            System.out.println("MaxTask finished, max = " + max);
        });
        futureMin.thenAccept(min -> {
            System.out.println("MinTask finished, min = " + min);
        });
        futureMin.join();
        futureMax.join();
        System.out.println("CompletableFuture finished");
    }

    public static void main(String[] args) {
        Task12 task = new Task12();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        task.startPool(executor);
//        executor.shutdown();


        task.startCompletableFuture(executor);
        executor.shutdown();
    }
}
