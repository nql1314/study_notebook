import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution17 {
    HashMap<Character,String> c_map = new HashMap<Character, String>(){{
        put('2',"abc");
        put('3',"def");
        put('4',"ghi");
        put('5',"jkl");
        put('6',"mno");
        put('7',"pqrs");
        put('8',"tuv");
        put('9',"wxyz");
    }};
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return  res;
        backtrack("",digits,0);
        return res;
    }

    void backtrack(String combination,String digits,int start) {
        if (start == digits.length()) {
            res.add(combination);
            return;
        }
        char digit = digits.charAt(start);
        String letters = c_map.get(digit);
        for(int i=0;i<letters.length();i++) {
            backtrack(combination+letters.charAt(i),digits,start+1);
        }
    }
}
