package org.example.threads;

public class Task11 {
    int [] numbers = {1,2,3,4,5,6,7,8,9};
    int min = numbers[0];
    int max = numbers[0];
    class FindMax implements Runnable {
        @Override
        public void run() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(Thread.currentThread().getName() + " " + numbers[i] +
                    " current max = " + max);
            if (numbers[i] > max) {

                max = numbers[i];
            }
        }
            System.out.println("Task max finished, max = " + max);
        }
    }
    class FindMin implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < numbers.length; i++) {
                System.out.println(Thread.currentThread().getName() + " " + numbers[i] +
                        " current min = " + min);
                if (numbers[i] < min) {
                    min = numbers[i];
                }
            }
            System.out.println("Task min finished, min = " + min);
        }
    }

    public void start() {
        Thread threadMin = new Thread(new FindMin());
        Thread threadMax = new Thread(new FindMax());
        threadMin.start();
        threadMax.start();
    }

    public static void main(String[] args) {
        Task11 task11 = new Task11();
        task11.start();

    }
}
