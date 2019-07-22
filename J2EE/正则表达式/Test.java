import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String content = "I am noob " +
                "from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern2 = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern2);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }

        // String相关正则操作
        String s1 = "123ABC321";
        System.out.println(s1.matches("\\d+[A-Z]+\\d+"));
        System.out.println(s1.replaceAll("\\d+","number"));
        System.out.println(s1.replaceFirst("\\d+","number"));
        System.out.println(Arrays.toString(s1.split("[A-Z]+")));

        Pattern p1 = Pattern.compile("a*b");
        Matcher m1 = p1.matcher("aaaaab");
        boolean b = m1.matches();
        System.out.println(b);
        System.out.println(m1.find());
    }
}
