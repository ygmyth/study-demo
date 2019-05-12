package myth;

public class PrinterWaitNotify {
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(() -> printer.printEven(), "even").start();
        new Thread(() -> printer.printOdd(), "odd").start();
    }

    static class Printer {
        private boolean isOdd;
        private int max = 100;
        private int number = 0;

        synchronized void printEven() {
            while (number < max) {
                while (!isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + number);
                isOdd = false;
                number++;
                notify();
            }


        }

        synchronized void printOdd() {
            while (number < max) {
                while (isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + number);
                isOdd = true;
                number++;
                notify();
            }

        }
    }

}