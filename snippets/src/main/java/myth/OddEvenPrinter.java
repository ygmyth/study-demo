package myth;

public class OddEvenPrinter {

    private Object monitor = new Object();
    private final int limit;
    private volatile int count;

    OddEvenPrinter(int initCount, int times) {
        this.count = initCount;
        this.limit = times;
    }

    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter(0, 100);
        new Thread(printer::print, "odd").start();
        new Thread(printer::print, "even").start();
    }

    private void print() {
        synchronized (monitor) {
            while (count < limit) {
                try {
                    System.out.println(count);
                    count++;
                    monitor.notifyAll();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            monitor.notifyAll();
        }
    }}