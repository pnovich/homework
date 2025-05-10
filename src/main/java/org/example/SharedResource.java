package org.example;

public class SharedResource {
    private int item;
    private boolean hasItem = false;

    public synchronized void produce(int newItem) {
        while (hasItem) {
            try {
                // Producer waits if the item is already present
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        item = newItem;
        hasItem = true;
        System.out.println("Produced: " + item);
        // Notify the consumer that a new item is available
        this.notify();
    }

    public synchronized void consume() {
        while (!hasItem) {
            try {
                // Consumer waits if there is no item
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed: " + item);
        hasItem = false;
        // Notify the producer that the item has been consumed
        this.notify();
    }
}