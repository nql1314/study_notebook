package DesignPattern;

/**
 * 采用静态内部类实现
 */
public class Singleton2 {
    private Singleton2(){}

    public static Singleton2 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }
}
