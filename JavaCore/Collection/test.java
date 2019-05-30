package Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        System.out.println(-1%5);
        Map<String,Student> map = new HashMap<>();
        Student student = new Student(1,"sam");
        map.put("key1",student);
        map.put("key2",student);
        System.out.println(map);
        student.setName("tom");
        System.out.println(map);

    }
}
