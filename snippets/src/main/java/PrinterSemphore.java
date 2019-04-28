package com.myth.file;

import java.util.concurrent.Semaphore;

public class PrinterSemphore {

    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(() -> printer.printEven(), "even").start();
        new Thread(() -> printer.printOdd(), "odd").start();
    }

    static class Printer {
        private Semaphore semEven = new Semaphore(1);
        private Semaphore semOdd = new Semaphore(0);
        private int MAX = 50;
        void printEven() {
            for (int i = 0; i < MAX; i=i+2) {
                try {
                    semEven.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
                semOdd.release();
            }
        }

        void printOdd() {
            for (int i = 1; i < MAX; i = i+2) {
                try {
                    semOdd.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
                semEven.release();
            }

        }
    }
}


