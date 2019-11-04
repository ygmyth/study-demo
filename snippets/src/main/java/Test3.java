import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test3 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier barrier = new CyclicBarrier(5);
        for(int i=0;i<4;i++) {
            Thread childThread= new Thread(() -> {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("子线程被执行");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            
            childThread.start();
            
        }

        barrier.await();//阻塞当前线程直到latch中的值
        System.out.println("主线程被执行");
    }
}