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
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (count <= 10) {
                try {
                    lock.lock();
                    System.out.println("奇: " + count);
                    count++;
                    even.signal();
                    even.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        executorService.execute(() -> {
            while (count <= 10) {
                try {
                    lock.lock();
                    System.out.println("偶: " + count);
                    count++;
                    even.signal();
                    even.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        executorService.shutdown();
    }
}
