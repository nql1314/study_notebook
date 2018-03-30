package concurrency.CAS;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * from https://www.cnblogs.com/rhyswang/p/7063816.html
 */
public class CasThread implements Runnable {

    private AtomicInteger count = new AtomicInteger(0);

    public void run() {
        while(count.get()<100){
            int current = count.get();
            int next = current+1;
            boolean result = count.compareAndSet(current, next);
            if(result)
                System.out.println("Thread"+Thread.currentThread().getName()+"operates " +
                        "the count,count now is"+next+","+new Date());
        }
    }
    public static void main(String args[]){

        Runnable r = new CasThread();
        for(int i=1;i<10000;i++){
            new Thread(r).start();
        }
    }

}