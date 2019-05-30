package Concurrent.Excutors;

import java.util.concurrent.ExecutionException;

import java.util.concurrent.ForkJoinPool;

import java.util.concurrent.Future;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask {

    private static final long THRESHOLD = 3;//阈值

    private long start;

    private long end;

    public CountTask(long start, long end) {

        this.start = start;

        this.end = end;

    }

    @Override

    protected Long compute() {

        long sum = 0;

        //如果任务足够小就计算任务

        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {

            for (long i = start; i <= end; i++) {

                sum += i;

            }

        } else {

            //如果任务大于阀值，就分裂成两个子任务计算

            long middle = (start + end) / 2;

            CountTask leftTask = new CountTask(start, middle);

            CountTask rightTask = new CountTask(middle + 1, end);

            //执行子任务

            leftTask.fork();

            rightTask.fork();

            //等待子任务执行完，并得到其结果

            long leftResult = (long) leftTask.join();

            long rightResult = (long) rightTask.join();

            //合并子任务

            sum = leftResult + rightResult;

        }

        return sum;

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //生成一个计算任务，负责计算1~100 sum

        CountTask task = new CountTask(1, 10000000);

        //执行一个任务

        Future result = forkJoinPool.submit(task);

        try {

            System.out.println(result.get());

        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
        System.out.println(System.currentTimeMillis()-start);
    }


}