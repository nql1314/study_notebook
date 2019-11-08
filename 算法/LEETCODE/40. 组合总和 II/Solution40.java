import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution40 {
    int len;
    int[] candidates;
    List<List<Integer>> results;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.len = candidates.length;
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.results = new ArrayList<>();
        findCombinationSum(target,0,new Stack<Integer>());
        return results;
    }

    private void findCombinationSum(int sum, int start, Stack<Integer> pre) {
        if (sum <0) return;
        if(sum == 0) {
            results.add(new ArrayList<>(pre));
        }
        for (int i=start;i<len && sum-candidates[i]>=0;i++) {
            if (i>start && candidates[i] == candidates[i-1]) continue;
            pre.add(candidates[i]);
            findCombinationSum(sum-candidates[i],i+1,pre);
            pre.pop();
        }
    }
    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        Solution40 solution = new Solution40();
        List<List<Integer>> combinationSum = solution.combinationSum2(candidates, target);
        System.out.println(combinationSum);
    }
}
