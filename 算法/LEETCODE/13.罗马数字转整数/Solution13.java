import java.util.HashMap;
import java.util.Map;

public class Solution13 {
    public int romanToInt(String s) {
        Map<String,Integer> romanMap = new HashMap<>();
        romanMap.put("I",1);
        romanMap.put("IV",3);
        romanMap.put("V",5);
        romanMap.put("IX",8);
        romanMap.put("X",10);
        romanMap.put("XL",30);
        romanMap.put("L",50);
        romanMap.put("XC",80);
        romanMap.put("C",100);
        romanMap.put("CD",300);
        romanMap.put("D",500);
        romanMap.put("CM",800);
        romanMap.put("M",1000);

        int vaule = 0;
        for (int i=0;i<s.length();i++) {
            if (i > 0 && romanMap.containsKey(s.substring(i-1,i+1))) {
                vaule += romanMap.get(s.substring(i-1,i+1));
            } else {
                vaule += romanMap.get(s.substring(i,i+1));
            }
        }
        return vaule;
    }
}
