package reflection.simpledemo;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test {
    @SuppressWarnings({"rawtypes","unchecked"})
    public static void main(String args[]) throws Exception{
        File file = new File("src/main/resources/simpledemo.properties");
        Properties springConfig = new Properties();
        springConfig.load(new FileInputStream(file));
        String className = (String)springConfig.get("class");
        String method = (String)springConfig.get("method");

        //根据类名创建类对象
        Class clazz = Class.forName(className);
        //根据方法名称，获取方法
        Method m = clazz.getMethod(method);
        //获取构造器
        Constructor c = clazz.getConstructor();
        //根据构造器，实例化对象
        Object service = c.newInstance();
        //调用对象的指定方法
        m.invoke(service);
    }
}
