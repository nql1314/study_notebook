public class Solution26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (i != j) nums[j] = nums[i];
            j++;
        }
        return j;
    }
}
