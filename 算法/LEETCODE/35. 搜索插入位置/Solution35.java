public class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        int mid;
        while (start<end){
            mid = (start + end) >>> 1 ;
            if (nums[mid] < target) {
                start = mid + 1;
            } else end = mid;
        }
        return start;
    }
}
