### Lock
* ReentrantLock 
    * 可重入锁，通过lock()加锁，finally里unlock解锁
    * lockInterruptibly(),加可中断锁,在等待时可以interrupt()
    * tryLock()或者tryLock(long timeout, TimeUtil unit)进行一次限时的锁等待，没获得就返回
    * new ReentrantLock(true)创建公平锁，按先后顺序获取锁
    * 通过condition 进行wait(),notify()等操作
    
     
### Atomic
### volatile
### ThreadLocal