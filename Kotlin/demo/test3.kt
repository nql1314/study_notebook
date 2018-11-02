fun main (args: Array<String>) {
    val a: Int = 1
    val b: Int = 2
//    val max = if (a > b) a else b
    val max = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
    println(max)

    val b2 = Array(3, { i -> (i * 2) })
    println(b2[2])

    val text = """
    |多行字符串
    |多行字符串${'$'}
    """.trimMargin( )
    println(text)   // 输出有一些前置空格
}
