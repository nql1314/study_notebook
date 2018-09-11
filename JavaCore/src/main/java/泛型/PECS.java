package 泛型;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PECS {
    /**
     * 存入数据：
     * 赋值是参数化类型为 Fruit 的集合和其子类的集合都可以成功，通配符类型无法实例化。
     *
     * 编译器会阻止将Strawberry类加入fruits。在向fruits中添加元素时，编译器会检查类型是否符合要求。因为编译器只知道fruits是Fruit某个子类的List，但并不知道这个子类具体是什么类，为了类型安全，只好阻止向其中加入任何子类。
     *
     * 那么可不可以加入Fruit呢？很遗憾，也不可以。事实上，不能往使用了? extends的数据结构里写入任何的值。
     *
     * 读取数据
     * 但是，由于编译器知道它总是Fruit的子类型，因此我们总可以从中读取出Fruit对象：
     *
     * Fruit fruit = fruits.get(0);
     */

    static class Test {
        static class Food {}
        static class Fruit extends Food {}
        static class Apple extends Fruit {}

        public static void main(String[] args) throws IOException {
            List<? extends Fruit> fruits = new ArrayList<>();
            fruits.add(new Food());     // compile error
            fruits.add(new Fruit());    // compile error
            fruits.add(new Apple());    // compile error

            fruits = new ArrayList<Fruit>(); // compile success
            fruits = new ArrayList<Apple>(); // compile success
            fruits = new ArrayList<Food>(); // compile error
            fruits = new ArrayList<? extends Fruit>(); // compile error: 通配符类型无法实例化

            Fruit object = fruits.get(0);    // compile success
        }
    }

    /**
     * 存入数据：
     * super 通配符类型同样不能实例化，Fruit 和其超类的集合均可赋值
     * 这里 add 时 Fruit 及其子类均可成功，为啥呢？因为已知 fruits 的参数化类型必定是 Fruit 或其超类 T，那么 Fruit 及其子类肯定可以赋值给 T。
     * 出于对类型安全的考虑，我们可以加入Apple对象或者其任何子类（如RedApple）对象（因为编译器会自动向上转型），但由于编译器并不知道List的内容究竟是Apple的哪个超类，因此不允许加入特定的任何超类型。
     * 读取数据
     * 编译器在不知道这个超类具体是什么类，只能返回Object对象，因为Object是任何Java类的最终祖先类。
     *
     * Object fruit = apples.get(0);
     */
    static class Test2 {
        static class Food {}
        static class Fruit extends Food {}
        static class Apple extends Fruit {}

        public static void main(String[] args) throws IOException {
            List<? super Fruit> fruits = new ArrayList<>();
            fruits.add(new Food());     // compile error
            fruits.add(new Fruit());    // compile success
            fruits.add(new Apple());    // compile success

            fruits = new ArrayList<Fruit>(); // compile success
            fruits = new ArrayList<Apple>(); // compile error
            fruits = new ArrayList<Food>(); // compile success
            fruits = new ArrayList<? super Fruit>(); // compile error: 通配符类型无法实例化

            Fruit object = fruits.get(0); // compile error
            Object object2 = fruits.get(0); // compile success
        }
    }
}
