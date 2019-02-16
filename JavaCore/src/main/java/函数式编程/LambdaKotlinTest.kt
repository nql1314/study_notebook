package 函数式编程


class LambdaKotlinTest {

fun main(){
    //Kotlin
//包含两个字段的数据类
    data class Person(val name: String, val age: Int)
//people的类型是ArrayList<Person>，这里Kotlin自动推导出了类型
    val people = arrayListOf(Person("Alan", 10), Person("Bob", 20), Person("Cort", 30))
    //使用filter筛选出年龄大于15岁的人
    println(people.filter { it.age > 15 })

    //使用map打印所有人名的列表
    println(people.map { it.name })

    //使用reduce拼接所有人名首字母 （"$acc$name"是Kotlin中的字符串拼接方式）
    println(people.map { it.name.substring(0, 1) }.reduce { acc , name -> "$acc$name" } )

    //集合中是否都是成年人
    println(people.all { it.age >= 18 })
//集合中是否存在未成年人
    println(people.any { it.age < 18 })
//集合中未成年人的数量
    println(people.count { it.age < 18 })
//找到集合中第一个成年人
    println(people.find { it.age >= 18 })
//找到集合中年龄最大/最小的人
    println(people.maxBy { it.age })
    println(people.minBy { it.age })

    //根据名字的长度分组
    println(people.groupBy { it.name.length })

    //得到所有人名的组成字母List
    println(people.flatMap { it.name.toList() })
}
}