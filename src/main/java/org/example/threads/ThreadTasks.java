package org.example.threads;

import org.example.threads.SharedFightState;
import org.example.threads.ThreadFighterGame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ThreadTasks {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hi!");
        int result = doAttackWithThreads();
        System.out.println("result = " + result);
    }

    public static int doAttackWithThreads() throws InterruptedException {
        SharedFightState state = new SharedFightState(80,80,
                20, 50,
                0, 0,
                false,0);
        ThreadFighterGame2 game = new ThreadFighterGame2(state);
        int result = game.findWinner();
        return result;
    }

}

class SharedFightState {
    int firstHealth;
    int secondHealth;
    int firstDamagePerAttack;
    int scondDamagePerAttack;
    int attackerNumber;
    int count;
    boolean gameOver;
    int winner;

    public SharedFightState(int firstHealth, int secondHealth, int firstDamagePerAttack, int scondDamagePerAttack, int attackerNumber, int count, boolean gameOver, int winner) {
        this.firstHealth = firstHealth;
        this.secondHealth = secondHealth;
        this.firstDamagePerAttack = firstDamagePerAttack;
        this.scondDamagePerAttack = scondDamagePerAttack;
        this.attackerNumber = attackerNumber;
        this.count = count;
        this.gameOver = gameOver;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "SharedFightState{" +
                "firstHealth=" + firstHealth +
                ", secondHealth=" + secondHealth +
                ", firstDamagePerAttack=" + firstDamagePerAttack +
                ", scondDamagePerAttack=" + scondDamagePerAttack +
                ", attackerNumber=" + attackerNumber +
                ", count=" + count +
                ", gameOver=" + gameOver +
                ", winner=" + winner +
                '}';
    }
}
class ThreadFighter implements Runnable {
    org.example.threads.SharedFightState state;
    int createdFighterNumber;
    volatile boolean isOver =  false;

    public ThreadFighter(org.example.threads.SharedFightState state, int createdFighterNumber) {
        this.state = state;
        this.createdFighterNumber = createdFighterNumber;
    }

    public void doAttack(int number, org.example.threads.SharedFightState state) {
        if (number == 0) {
            state.secondHealth = state.secondHealth - state.firstDamagePerAttack;
            System.out.println("fist fighter attacks; second has " + state.secondHealth);
            if (state.secondHealth <= 0) {
                state.winner = 0;
                state.gameOver = true;
                isOver = true;
            }
        } else {
            state.firstHealth = state.firstHealth - state.scondDamagePerAttack;
            System.out.println("second fighter attacks; first has " + state.firstHealth);
            if (state.firstHealth <= 0) {
                state.winner = 1;
                state.gameOver = true;
                isOver = true;
            }
        }

    }

    @Override
    public void run() {

        while (!isOver) {
            synchronized (state) {

                if (state.gameOver) {
                    System.out.println("game ovr is true ,count = " + state.count);
                    return;
                }
//            state.count++;
                boolean isActive = state.count % 2 == this.createdFighterNumber;

                if (!isActive) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                doAttack(createdFighterNumber, state);
                state.count++;
                state.notify();
            }
        }
    }
}

class ThreadFighterGame {

    org.example.threads.SharedFightState state;
    Object gamOverLock = new Object();

    public ThreadFighterGame(org.example.threads.SharedFightState state) {
        this.state = state;
    }

    public int findWinner() throws InterruptedException {
//        SharedFightState state = new SharedFightState(80,80,
//                20, 50,
//                0, 0,
//                false,0);
        org.example.threads.ThreadFighter fighter0 = new org.example.threads.ThreadFighter(this.state,0);
        org.example.threads.ThreadFighter fighter1 = new org.example.threads.ThreadFighter(this.state,1);
        Thread thread0 = new Thread(fighter0,"fighter0");
        Thread thread1 = new Thread(fighter1,"fighter1");
        thread0.start();
        thread1.start();

//        Thread.sleep(10000);

        thread0.join();
        thread1.join();
        System.out.println("winner is " + state.winner);
//
//        System.out.println("state is " + state);
        return state.winner;
    }
}

class ThreadFighter2 implements Runnable {
    org.example.threads.SharedFightState state;
    int createdFighterNumber;
    volatile boolean isOver =  false;
    private CountDownLatch countDownLatch;


    public ThreadFighter2(org.example.threads.SharedFightState state, CountDownLatch countDownLatch, int createdFighterNumber) {
        this.state = state;
        this.createdFighterNumber = createdFighterNumber;
        this.countDownLatch = countDownLatch;

    }

    public void doAttack(int number, org.example.threads.SharedFightState state) {
        if (number == 0) {
            state.secondHealth = state.secondHealth - state.firstDamagePerAttack;
            System.out.println("fist fighter attacks; second has " + state.secondHealth);
            if (state.secondHealth <= 0) {
                state.winner = 0;
                state.gameOver = true;
                isOver = true;
            }
        } else {
            state.firstHealth = state.firstHealth - state.scondDamagePerAttack;
            System.out.println("second fighter attacks; first has " + state.firstHealth);
            if (state.firstHealth <= 0) {
                state.winner = 1;
                state.gameOver = true;
                isOver = true;
            }
        }

    }

    @Override
    public void run() {

        while (!isOver) {
            synchronized (state) {

                if (state.gameOver) {
                    System.out.println("game ovr is true ,count = " + state.count);
                    countDownLatch.countDown();
                    return;
                }
//            state.count++;
                boolean isActive = state.count % 2 == this.createdFighterNumber;

                if (!isActive) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                doAttack(createdFighterNumber, state);
                state.count++;
                state.notify();
            }
        }
        countDownLatch.countDown();
    }
}


class ThreadFighterGame2 {

    org.example.threads.SharedFightState state;
    Object gamOverLock = new Object();

    public ThreadFighterGame2(org.example.threads.SharedFightState state) {
        this.state = state;
    }

    public int findWinner() throws InterruptedException {
//        SharedFightState state = new SharedFightState(80,80,
//                20, 50,
//                0, 0,
//                false,0);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        org.example.threads.ThreadFighter2 fighter0 = new org.example.threads.ThreadFighter2(this.state, countDownLatch,0);
        org.example.threads.ThreadFighter2 fighter1 = new org.example.threads.ThreadFighter2(this.state, countDownLatch,1);
        Thread thread0 = new Thread(fighter0,"fighter0");
        Thread thread1 = new Thread(fighter1,"fighter1");
        List<Thread> threadList = new ArrayList<>();
        threadList.add(thread0);
        threadList.add(thread1);
//
//        thread0.start();
//        thread1.start();
//
////        Thread.sleep(10000);
//
//        thread0.join();
//        thread1.join();


        threadList.forEach(Thread::start);
        countDownLatch.await();

        System.out.println("winner is " + state.winner);
//
//        System.out.println("state is " + state);
        return state.winner;
    }
}


