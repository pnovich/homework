package org.example;

class Mainclass {
    public static void main(String[] args) {
        Mainclass main = new Mainclass();
        main.start();
    }

    public void start() {
        //This code is run by the 'waiting' thread
        Object monitorObj = new Object();
        Worker worker = new Worker(monitorObj); //Our worker object now has access to the same object monitorObj as this thread
        //Therefore, both the waiting thread and the worker thread have the same object to synchronize upon

        Thread thread = new Thread(worker);


        synchronized(monitorObj) { //Claiming monitorObj's monitor
            System.out.println(Thread.currentThread().getName() + "About to start the worker thread");
            thread.start(); //The worker thread has been started
            try {
                System.out.println(Thread.currentThread().getName() + "This thread is going to pause until another thread wakes it up");
                monitorObj.wait(); //This thread now pauses and releases the monitor. It can be claimed by any other thread
                System.out.println(Thread.currentThread().getName() + " after wait call");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "This thread has now resumed since Worker has called notify() and released the monitor");
    }
}
