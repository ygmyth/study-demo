package myth;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class WithBlockingQueue {
    private int maxSize = 5;
    private ArrayBlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(maxSize);

    public static void main(String[] args) {
        WithBlockingQueue t = new WithBlockingQueue();
        new Thread(t.new Producer()).start();
        new Thread(t.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    bq.put(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                     bq.take() ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
