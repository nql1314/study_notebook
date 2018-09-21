## Excutors
### Callable,Future
https://blog.csdn.net/qq_19431333/article/details/77483763
 * 带返回值，能抛出异常
 * Future接口代表运算的结果，get方法阻塞，直到返回结果
 * Future接口是提供异步计算的结果的任务
 * FutureTask 状态：NEW(0),COMPLETING(1),NORMAL(2),EXCEPTIONAL(3),CANCELLED(4),INTERRUPTING(5),INTERRUPTED(6).

### 线程中断
https://www.cnblogs.com/yangming1996/p/7612653.html
  * java 的支持：public boolean isInterrupted(), public void interrupt(),public static boolean interrupted()
    isInterrupted不会清除中断标志位，而interrupted会
  * 线程一共6种状态，分别是NEW，RUNNABLE，BLOCKED，WAITING，TIMED_WAITING，TERMINATED（Thread类中有一个State枚举类型列举了线程的所有状态） 
  * NEW 和 TERMINATED状态不会设置中断标志位，中断操作会无效
  * RUNNABLE状态中断会设置中断标志位但不会实际中断，具体操作是在run方法中交给程序去判断
  * BLOCKED,阻塞状态中断无影响，但仍会设置中断标志位
  * WAITING/TIMED_WAITING,中断会抛出InterruptedException，并清空中断标志位
  
### ThreadFactory
线程工厂
https://www.cnblogs.com/zhujiabin/p/5404771.html
 * newFixedThreadPool
    * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newCachedThreadPool
    * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool
    * 创建一个定长线程池，支持定时及周期性任务执行。
 * ExcutorCompletionService
    * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
### Folk/Join
http://ifeve.com/talk-concurrency-forkjoin/
 * 切分任务，合并结果
 

