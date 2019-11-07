public class Solution27 {
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) continue;
            if (i != j) nums[j] = nums[i];
            j++;
        }
        return j;
    }
}
