package org.example.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                });
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable> tasks = new ArrayList<Callable>();
        for (int i = 0; i < 5; i++) {
            tasks.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    return "String from" + Thread.currentThread().getName();
                }
            });
        }

//        tasks.forEach(task -> {
//            executorService.submit(task);
//        });

//        Future<String> future = executor.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "String from" + Thread.currentThread().getName();
//            }
//        });

//        String result = future.get();
//        System.out.println(result);

 /////////////////////////////

//        List<Future<String>> futureList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            futureList.add(executor.submit(tasks.get(i)));
//        }
//        futureList.forEach(future -> {
//            try {
//                System.out.println(future.get());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        System.out.println("all done");
///////////////////////////////////////

        List<CompletableFuture<String>> completableFutureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            Supplier<String> stringSupplier = () -> "String from" + Thread.currentThread().getName();
            completableFutureList.add(CompletableFuture.supplyAsync(stringSupplier, executor));
        }
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            resultList.add(completableFutureList.get(i).get());
        }

        System.out.println("Result: " + resultList);



        executorService.shutdown();
        System.out.println("executorService shutdown");
        executor.shutdownNow();
        System.out.println("executor shutdown");
    }
}
