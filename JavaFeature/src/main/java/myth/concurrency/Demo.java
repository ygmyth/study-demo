package myth.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-29 18:46
 **/
public class Demo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Object o = new Object();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " is running");
                    Thread.sleep(30000);
                    System.out.println(Thread.currentThread().getName() + " stop");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        };
        Runnable r1 = new Runnable() {
            @Override
            public void run() {

                {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " stop");
                }

            }
        };
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        }
    }
