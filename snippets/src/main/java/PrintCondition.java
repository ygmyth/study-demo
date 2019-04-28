package com.myth.file;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class PrintCondition{
    private static int count = 0;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition even = lock.newCondition();
    private static final Condition odd = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (count <= 100) {
                try {
                    lock.lock();
                    System.out.println("偶: " + count);
                    count++;
                    even.await();
                    odd.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        executorService.execute(() -> {
            while (count <= 100) {
                try {
                    lock.lock();
                    System.out.println("奇: " + count);
                    count++;
                    even.signal();
                    odd.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}
