public class Solution10_1 {
    /**
     * 动态规划
     * @param n
     * @return
     */
    public int Fibonacci1(int n) {
        if (n <= 1)
            return n;
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        return fib[n];
    }

    /**
     * 只存储第i-1和i-2项
     * @param n
     * @return
     */
    public int Fibonacci2(int n) {
        if (n <= 1)
            return n;
        int pre2 = 0, pre1 = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = pre2 + pre1;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }

    /**
     * 所求小于40 所以直接先把前40算出来
     */
    private int[] fib = new int[40];

    public Solution10_1() {
        fib[1] = 1;
        fib[2] = 2;
        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
    }

    public int Fibonacci(int n) {
        return fib[n];
    }
}
