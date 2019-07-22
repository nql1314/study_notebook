package DesignPattern;

/**
 * 双重检查锁 + volatile实现
 */
public class Singleton1 {
    public static volatile Singleton1 singleton;
    public static Singleton1 getSingleton(){
        if(singleton ==null){
            synchronized (Singleton1.class){
                if(singleton ==null){
                    singleton = new Singleton1();
                }
            }
        }
        return singleton;
    }
    private Singleton1(){
    }
}
