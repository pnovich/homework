package org.example.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CalculateFactorialApp {
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

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new CalculateFactorialAppThread("thread " + i, inputQueue, outputQueue, countDownLatch));
        }

        executorService.shutdown();
        countDownLatch.await();
        System.out.println("result: " + outputQueue);
    }
}

class CalculateFactorialAppThread implements Runnable
{
    String threadName;
    LinkedBlockingQueue<String> inputQueue;
    LinkedBlockingQueue<String> outputQueue;
    CountDownLatch countDownLatch;


    CalculateFactorialAppThread(String threadName,
                                LinkedBlockingQueue<String> inputQueue, LinkedBlockingQueue<String> outputQueue,
                                CountDownLatch countDownLatch) {
        this.threadName = threadName;
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
            System.out.println("Starting " + threadName + "queue = " + inputQueue);
            String line;
            while ((line = inputQueue.poll()) != null) {
                System.out.println("thread = " + threadName + " queue = " + inputQueue);
                System.out.println("line = " + line + " from thread " + threadName);
                String currentresult = calculateFactorial(line, threadName);
                System.out.println("currentresult = " + currentresult);
//            String currentresult = "mocked calculate factorial of " + line;
                outputQueue.offer(currentresult);
                countDownLatch.countDown();
            }
    }

    private String calculateFactorial(String line, String threadName) {
        String result = "mocked calculate factorial of " + line;
        System.out.println("result = " + result
//                + " from thread " + threadName
        );
        return result;
    }
}

class CalculateUtil {
    public static String calculateFactorial(String line) {
        String result = "mocked calculate factorial of " + line;
        System.out.println(result);
        return result;
    }

}

