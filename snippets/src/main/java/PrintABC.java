package com.myth.file;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.ConsoleHandler;
import java.util.stream.Collectors;

public class PrintABC implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    private static List<String> arr = Arrays.asList("A", "B", "C");
    private static List<Condition> conds = arr.stream().map(i -> lock.newCondition()).collect(Collectors.toList());
    private static int token = 0;
    private int index;

    public PrintABC(int index) {
        this.index = index;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            threads[i] = new Thread(new PrintABC(i), arr.get(i));
            threads[i].start();
        }
    }


    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 20; i++) {
                while (token != index) {
                    conds.get(index).await();
                }
                System.out.println(Thread.currentThread().getName());
                token = (token + 1) % 3;
                conds.get(token).signal();
            }
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

}
