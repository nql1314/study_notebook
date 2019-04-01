#java 基础
* boolean/1,byte/8,char/16,short/16,int/32,float/32,long/64,double/64
* Integer 缓存池的大小默认为 -128~127
* String 不可变，缓存hash值，在JDK6及之前，存运行时常量池，JDK7.0后存堆
* StringBuilder 非线程安全，Stringbuffer线程安全
* java参数都是值传递，没有引用传递
  Java程序设计语言总是采用按值调用。也就是说，方法得到的是所有参数值的一个拷贝，也就是说，方法不能修改传递给它的任何参数变量的内容。
* Object 通用方法
  重写Equals()必须重写hashcode()
* 