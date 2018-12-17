package DesignPattern;

public class Singleton {
    public static volatile Singleton singleton;
    public static Singleton getSingleton(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
    private Singleton(){
    }
}
