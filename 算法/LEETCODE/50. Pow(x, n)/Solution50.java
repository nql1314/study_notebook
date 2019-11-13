public class Solution50 {
    public double myPow(double x, int n) {
        if (n==0) return 1;
        long N = n;
        if(N>0) return pow(x,N);
        else return 1/pow(x,-N);
    }

    private double pow(double x,long n) {
        if (n==1) return x;
        double t = pow(x,n/2);
        if(n%2==0) {
            return t*t;
        } else {
            return t*t*x;
        }
    }
}
