import java.util.ArrayList;
import java.util.List;

public class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length==0) return res;
        List<Integer> combination = new ArrayList<>();
        backstrack(combination,0,nums,res);
        return res;
    }

    private void backstrack(List<Integer> combination,int i,int[] nums,List<List<Integer>> res) {
        if (i==nums.length) {
            res.add(combination);
            return;
        }
        for (int j=i;j< nums.length;j++) {
            List<Integer> list = new ArrayList<>(combination);
            list.add(nums[j]);
            swap(nums,i,j);
            backstrack(list,i+1,nums,res);
            swap(nums,i,j);
        }
    }

    private void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
