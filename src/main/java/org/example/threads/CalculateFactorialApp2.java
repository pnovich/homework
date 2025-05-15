package org.example.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CalculateFactorialApp2 {


    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();
        int numberOfThreads = 10;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        inputQueue.offer("1");
        inputQueue.offer("2");
        inputQueue.offer("3");
        inputQueue.offer("4");
        inputQueue.offer("5");
        inputQueue.offer("6");
        inputQueue.offer("7");
        inputQueue.offer("8");
        inputQueue.offer("9");
        inputQueue.offer("10");

        System.out.println(inputQueue);

        List<Future<String>> list = new ArrayList<Future<String>>();

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            String line = inputQueue.poll();
            Future<String> future = executorService.submit(new FactorialThread("thread" + i, line, outputQueue, countDownLatch));
            list.add(future);
        }

        executorService.shutdown();
        countDownLatch.await();
        System.out.println("result: " + outputQueue);
        System.out.println("list = " + list);
        for(Future<String> fut : list){
            try {
                String currentOutput = fut.get();
                System.out.println(currentOutput);
                outputQueue.offer(currentOutput);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("result: " + outputQueue);


    }
}

class FactorialThread implements Callable<String> {
    String threadName;
    String input;
    LinkedBlockingQueue<String> outputQueue;
    CountDownLatch countDownLatch;

    FactorialThread(String threadName, String input, LinkedBlockingQueue<String> outputQueue, CountDownLatch countDownLatch) {
        this.input = input;
        this.outputQueue = outputQueue;
        this.countDownLatch = countDownLatch;
        this.threadName = threadName;
    }

    @Override
    public String call() {
//        System.out.println("thread " + threadName + " started, input = " + input);
        String currrentResult = calculateFactorial(this.input);
//        this.outputQueue.offer(currrentResult);
//        System.out.println("thread " + threadName + " stoped, input = " + input + "output = " + currrentResult
//                + " queue = " + outputQueue
//        );
        this.countDownLatch.countDown();
        return currrentResult;
    }

    private String calculateFactorial(String line) {
        String result = "mocked calculate factorial of " + line
//                + " " + threadName + "queue = " + outputQueue
                ;
        System.out.println("result = " + result
//                + " from thread " + threadName
        );
        return result;
    }

}

