package demo

open class Person(var name: String, var age: Int) {
    open var sex: String = "unknow"
    init {
        println("基类初始化")
    }
}

// 子类的主构造方法的 name 前边也加了 var，这是不允许的，会报'name' hides member of supertype and needs 'override' modifier
class Student(/* var */ name: String, age: Int, var no: String, var score: Int) : Person(name, age) {
    override var sex: String = "male"
}