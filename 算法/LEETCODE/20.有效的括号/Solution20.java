import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.values().contains(chars[i])) {
                stack.push(chars[i]);
            } else if (map.keySet().contains(chars[i])) {
                if (stack.isEmpty() || stack.pop() != map.get(chars[i]))
                    return false;
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        solution20.isValid("()");
    }
}
