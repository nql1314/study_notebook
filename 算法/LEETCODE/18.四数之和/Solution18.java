import java.util.*;
class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        for(int i=0;i<=nums.length - 4;i++) {
            if (i>0 && nums[i-1]==nums[i]) continue;
            for (int j=i+1;j<=nums.length - 3;j++) {
                if (j>i+1 && nums[j] == nums[j-1]) continue;
                int l = j+1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum < target) {
                        while (l<r && nums[l+1]==nums[l]) l++;
                        l++;
                    }
                    else if (sum > target) {
                        while (l<r && nums[r-1]==nums[r]) r--;
                        r--;
                    }
                    else {
                        result.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while (l<r && nums[l+1]==nums[l]) l++;
                        while (l<r && nums[r-1]==nums[r]) r--;
                        l++;
                        r--;
                    }
                }
            }
        }
        return result;
    }
}