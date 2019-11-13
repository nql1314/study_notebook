/*
  给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 */
public class Solution268 {
    public int missingNumber(int[] nums) {
        int n = -1; //存第n个数
        for (int i=0;i<nums.length;i++) {
            while (nums[i] != i) {
                if (nums[i] != nums.length) {
                    swap(nums,nums[i],i);
                } else {
                    int temp = n;
                    n = nums[i];
                    nums[i] = temp;
                }
                if (nums[i] == -1) break;
            }
        }
        if (n== -1) return nums.length;
        else for (int j=0;j<nums.length;j++) {
            if (nums[j] == -1) return j;
        }
        return -1;
    }
    private void swap (int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

    }
}
