import java.util.ArrayList;
import java.util.List;

public class Solution22_2 {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        backstrack("",n,n,res);
        return res;
    }
    private void backstrack(String combination,int left,int right,List<String> res) {
        if(left==0&&right==0) {
            res.add(combination);
            return;
        }
        if(left>0) backstrack(combination+"(",left-1,right,res);
        if(right>0 && left<right) backstrack(combination+")",left,right-1,res);
    }
}
