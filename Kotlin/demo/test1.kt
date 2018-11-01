/*
函数定义
 */
fun test() {}
class Runoob {}

fun sum(a: Int, b: Int): Int {
    return a+b
}

fun sum2(a: Int, b: Int) = a + b

public fun sum3(a: Int, b: Int): Int = a + b

fun printSum(a: Int, b: Int): Unit {
    print(a + b)
}
public fun printSum2(a: Int, b: Int) {
    print(a + b)
}
fun vars(vararg v: Int) {
    for(vt in v) {
        print(vt)
    }
}
fun main(args: Array<String>) {
    vars(1,2,3,4,5)
    val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
    println(sumLambda(1,2))
}



