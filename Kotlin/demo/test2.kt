//常变量定义
fun main(args: Array<String>) {
    val a: Int = 1
    val b = 1
    val c: Int
    c = 1
    var x = 5
    x += 1
    //单行注释
    /*多行
    注释
     */
    //字符串模板
    var a2 = 1
    val s1 = "a is $a2"
    println(s1)
    a2 = 2
    val s2 = "${s1.replace("is", "was")},but now is $a2"
    println(s2)
    //null 检查机制
    var age: String? = "23"
    var ages = age!!.toInt()
    val ages1 = age?.toInt()
    val ages2 = age?.toInt() ?: -1
    //类型检测和自动类型转换
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            return obj.length
        }
        return null
    }
    //区间
    for (i in 1..4) print(i)
    for (i in 4..1) print(i)
    val i = 1
    if (i in 1..4) {
        print(i)
    }
    for (i in 1..4 step 2) print(i)
    for (i in 4 downTo 1 step 2) print(i)
    for (i in 1 until 10) {
        print(i)
    }
    //类型转换
    val b1: Byte = 1
    val i1: Int = b1.toInt()



}
