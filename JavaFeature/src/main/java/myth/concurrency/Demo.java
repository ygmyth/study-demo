package myth.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-29 18:46
 **/
public class Demo {
    public static void main(String[] args) {

        Map map = new HashMap<Integer, String>();
        map.put(null, "hello");
        map.put(new Integer(0), "world");
        map.put(new Integer(1), "wo");
        map.put(null, "world");
        System.out.println("atatatatatatatat".hashCode());
        System.out.println("c6atatatatatatbU".hashCode());
       /* ReentrantLock lock = new ReentrantLock();
        Object o = new Object();
        ExecutorService service = new ThreadPoolExecutor(1,10,10,TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)){
            public int prestartAllCoreThreads() {
                return 1;
            }
        };

        List<Integer> a = new ArrayList<Integer>() {
            {add(1);add(2);}
        };
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(a.size());*/
    }
    }
