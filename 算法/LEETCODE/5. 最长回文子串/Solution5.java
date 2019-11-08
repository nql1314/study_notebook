public class Solution5 {
    public String longestPalindrome(String s) {
        char[] c = s.toCharArray();
        int length = s.length();
        int maxLen = 0;
        String res = "";
        boolean[][] p = new boolean[length][length];
        for (int len = 1; len <= length; len++) {
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                if (end >= length) break;
                p[start][end] = ((len == 1) || (len == 2) || p[start + 1][end - 1]) && c[start] == c[end];
                if (p[start][end] && len > maxLen) {
                    maxLen = len;
                    res = s.substring(start, end + 1);
                }
            }
        }
        return res;
    }
}
