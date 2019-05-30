package Concurrent.Excutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 四种线程池demo
 */
public class ThreadPoolTest {
    static class test1{
        public static void main(String[] args){
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                try {
                    Thread.sleep(index * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cachedThreadPool.execute(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println(index);
                    }
                });
            }
        }
    }
    static class test2{
        public static void main(String[] args){
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
            for (int i = 0; i < 10; i++) {
                final int index = i;
                fixedThreadPool.execute(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            System.out.println(index);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
    static class test3{
        public static void main(String[] args){
            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
            scheduledThreadPool.schedule(new Runnable() {

                @Override
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);
        }
    }
    static class test4{
        public static void main(String[] args){
            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    System.out.println("delay 1 seconds, and excute every 3 seconds");
                }
            }, 1, 3, TimeUnit.SECONDS);
        }

    }
    static class test5{
        public static void main(String[] args){
            ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                singleThreadExecutor.execute(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            System.out.println(index);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
