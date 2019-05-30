package Concurrent.Excutors;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 */
public class ThreadFactoryTest {

    static class MyThreadFactory implements ThreadFactory {

        private int counter;
        private String name;
        private List<String> stats;

        public MyThreadFactory(String name) {
            counter = 0;
            this.name = name;
            stats = new ArrayList<String>();
        }

        @Override
        public Thread newThread(Runnable run) {
            Thread t = new Thread(run, name + "-Thread-" + counter);
            counter++;
            stats.add(String.format("Created thread %d with name %s on%s\n",t.getId(), t.getName(), new Date()));
            return t;
        }

        public String getStas() {
            StringBuffer buffer = new StringBuffer();
            Iterator<String> it = stats.iterator();
            while (it.hasNext()) {
                buffer.append(it.next());
                buffer.append("\n");
            }
            return buffer.toString();
        }

    }

    static class MyTask implements Runnable {

        private int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Task "+ num+" is running");
            try {
                Thread.sleep(2*10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        System.out.println("main thread beging");
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");

        Thread thread = null;
        for(int i = 0; i < 10; i++) {
            thread = factory.newThread(new MyTask(i));
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",factory.getStas());
        System.out.println("main thread end");
    }

}