public class Solution29 {
    public int divide(int dividend, int divisor){

        //判定商的符号
        boolean sign = (dividend > 0) ^ (divisor > 0);

        //将被除数和除数转化负数进行运算
        if(dividend > 0) dividend = - dividend;
        if(divisor > 0) divisor = - divisor;

        //进行循环求商
        int res = 0;
        while(dividend <= divisor){
            int temp_res = -1;
            int temp = divisor;
            while(dividend <= (temp << 1)){
                //如果移位出现负数越界
                if(temp < Integer.MIN_VALUE >> 1) break;
                temp = temp << 1;
                temp_res = temp_res << 1;
            }
            dividend -= temp;
            res += temp_res;
        }

        //当商是正数的时候的 进行取反操作
        if(sign == false){
            //防止正数越界 -2147483648 --> 2147483647(2147483648会越界)
            if(res <= Integer.MIN_VALUE) res = Integer.MAX_VALUE;
            else res = -res;
        }
        return res;
    }
}
