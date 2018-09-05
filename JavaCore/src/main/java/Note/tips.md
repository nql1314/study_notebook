* 如果用transient声明一个实例变量，当对象存储时，它的值不需要维持。换句话来说就是，用transient关键字标记的成员变量不参与序列化过程。
* <? super T>表示包括T在内的任何T的父类，<? extends T>表示包括T在内的任何T的子类
* jdk1.8 接口中可以有static和default方法