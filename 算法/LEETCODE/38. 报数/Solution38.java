public class Solution38 {
    public String countAndSay(int n) {
        String[] dp = new String[n];
        dp[0] = "1";
        for (int i=1;i<n;i++) {
            dp[i] = generate(dp[i-1]);
        }
        return dp[n-1];
    }
    private String generate(String number) {
        if(number.length() == 0) return "";
        char cur = ' ';
        StringBuilder res = new StringBuilder();
        int sum = 0;
        for(int i=0;i<number.length();i++) {
            if (number.charAt(i) == cur) sum++;
            else {
                if(sum != 0) {
                    res.append(sum).append(cur);
                }
                cur = number.charAt(i);
                sum = 1;
            }
        }
        if(sum != 0) {
            res.append(sum).append(cur);
        }
        return res.toString();
    }
}
