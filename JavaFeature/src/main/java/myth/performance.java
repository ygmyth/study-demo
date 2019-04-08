package myth;

import com.google.common.collect.Lists;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class performance {

    static void forTest() {
        StopWatch stopWatch = new StopWatch("test");
        Random seed = new Random();

        List<Integer> ints = Stream.generate(seed::nextInt).limit(500000).collect(Collectors.toList());
        int m = Integer.MIN_VALUE;
        int e = ints.size();
        Integer[] a = ints.toArray(new Integer[ints.size()]);
        stopWatch.start("foreach");
        for (int i = 0; i < e; i++) {
            if (a[i] > m) {
                m = a[i];
            }
        }
        stopWatch.stop();
        stopWatch.start("foreach1");
        for (int i = 0; i < e; i++) {
            if (a[i] > m) {
                m = a[i];
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.toString());
    }

    static double fibImpl(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be > 0");
        if (n ==0) return 0d;
        if (n == 1) return 1d;
        double d = fibImpl(n-2) + fibImpl(n-1);
        if (Double.isInfinite(d)) throw new ArithmeticException("overflow");
        return d;
    }

    static void forTest1() {
        Random seed = new Random();
        int N = 1000000;
        long sum = 0;
        StopWatch stopWatch = new StopWatch("test");

        stopWatch.start("foreach");
        for(int i=0;i<N;i++){
            if(5>3)
                new ArrayList<>(1);
        }
        stopWatch.stop();
        stopWatch.start("foreach1");
        sum = 0;
        if(5>3){
            for(int i=0;i<N;i++)
                new ArrayList<>(1);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.toString());
    }
    static void doTest() {
        double l;
        long time = System.currentTimeMillis();
        for (int i = 1; i < 50; i++){
            l = fibImpl(35);
        }

        long now = System.currentTimeMillis();
        System.out.println(now - time);
        //System.out.println(l);
    }
    public static void main(String[] args) {
        //forTest();
        forTest1();
    }
}
