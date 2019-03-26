public class Singleton {
    private volatile static Singleton singleton; //注意volatile 保证内存可见性
    private Singleton(){ }
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) { //双重校验
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
