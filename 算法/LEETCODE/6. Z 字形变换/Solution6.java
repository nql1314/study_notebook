public class Solution6 {
    public String convert(String s, int numRows) {
        String res = "";
        if (numRows <= 1) return s;
        int size = Math.min(numRows, s.length());
        String[] rows = new String[size];
        int flag = -1;
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (rows[r] != null) rows[r] += s.charAt(i);
            else rows[r] = "" + s.charAt(i);
            if (r == 0 || r == size - 1) flag = 0 - flag;
            r += flag;
        }
        for (String row : rows) {
            if (!row.isEmpty()) res += row;
        }
        return res;
    }
}
