package myth;

public class PrinterWaitNotify {

  private int count = 0;
  private final Object lock = new Object();

  public void turning() throws InterruptedException {
    new Thread(new TurningRunner(), "偶数").start();
    Thread.sleep(1);
    new Thread(new TurningRunner(), "奇数").start();
  }

  class TurningRunner implements Runnable {

    @Override
    public void run() {
      while (count <= 100) {
        synchronized (lock) {
          System.out.println(Thread.currentThread().getName() + ": " + count++);
          lock.notifyAll();
          try {
            if (count <= 100) {
              lock.wait();
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

}