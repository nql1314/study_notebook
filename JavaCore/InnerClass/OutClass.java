package InnerClass;
interface Contents {
    int value();
}
public class OutClass{
    // 创建一个继承自Contents的匿名类的对象
    public Contents getContents(){
        return new Contents(){
            private int i = 11;
            public int value(){
                return i;
            }
        }; //注意有分号，标记表达式的结尾
    }
}