package org.example;

public class Worker implements Runnable {

    private Object monitor;

    public Worker(Object monitorObj) {
        monitor = monitorObj;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Waiting to acquire the monitor");
        synchronized(monitor) {
            System.out.println(Thread.currentThread().getName() + "About to wake up the waiting thread");
            monitor.notify();
            System.out.println(Thread.currentThread().getName() + "after notify");
        }
        //Now that we are out of the synchronized block, the Worker thread has released the monitor
        //Furthermore, notify() was invoked in the synchronized block above
        System.out.println(Thread.currentThread().getName() + "Now that the waiting thread has woken up, this Worker thread can continue running as usual");
        //The worker thread continues running and executes additional code that may be present here
    }

}