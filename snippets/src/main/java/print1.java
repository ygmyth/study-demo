import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class print1 {

  private static final Semaphore odd = new Semaphore(1);
  private static final Semaphore even = new Semaphore(0);
  private static int count = 0;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(() -> {
      while (count < 100) {
        try {
          odd.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + count);
        count++;
        even.release();
      }
    });
    executorService.execute(() -> {
      while (count < 100) {
        try {
          even.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + count);
        count++;
        odd.release();
      }
    });
    executorService.shutdown();
  }
}
