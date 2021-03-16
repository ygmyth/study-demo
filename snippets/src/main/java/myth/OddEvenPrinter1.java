package myth;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter1 {
    private int end;
    private int start;
    private Lock lock = new ReentrantLock();

    public OddEvenPrinter1(int start, int end){
        this.start = start;
        this.end = end;
    }
    public void print(String name, int mode) {
        while(start <= end) {
            lock.lock();
            if ( start % 2 == mode && start <= end) {
                System.out.println(name + ":" +start);
                start++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter1 printer = new OddEvenPrinter1(0, 100);
        new Thread(() -> printer.print("jishu", 1)).start();
        new Thread(() -> printer.print("oushu", 0)).start();
    }

}
