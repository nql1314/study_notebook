package 函数式编程;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LambdaTest {
    public static void main(String[] args) {
        Consumer<String> print = s -> System.out.println(s);
        print.accept("Hello World");
        BiFunction<Integer,Integer,Integer> add = (Integer x, Integer y) -> x + y;
        System.out.println(add.apply(1,2));
    }
}
