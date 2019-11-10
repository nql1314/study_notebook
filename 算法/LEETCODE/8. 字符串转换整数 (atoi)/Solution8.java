public class Solution8 {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0;
        boolean flag = true;
        int res = 0;
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i == str.length()) return 0;
        char ch = str.charAt(i);
        if (ch == '+') {
            i++;
        }
        if (ch == '-') {
            flag = false;
            i++;
        }
        while (i < str.length()) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
            res = res * 10 + str.charAt(i) - '0';
            if (i + 1 < str.length() && flag && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'
                    && res > Integer.MAX_VALUE / 10)
                return Integer.MAX_VALUE;
            if (i + 1 < str.length() && flag  && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'
                    && res == Integer.MAX_VALUE / 10 && str.charAt(i + 1) - '0' > Integer.MAX_VALUE % 10)
                return Integer.MAX_VALUE;
            if (i + 1 < str.length() && !flag  && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'
                    && -res < Integer.MIN_VALUE / 10)
                return Integer.MIN_VALUE;
            if (i + 1 < str.length() && !flag  && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'
                    && -res == Integer.MIN_VALUE / 10 && -(str.charAt(i + 1) - '0') < Integer.MIN_VALUE % 10)
                return Integer.MIN_VALUE;
            i++;
        }
        return flag ? res : -res;
    }
}
