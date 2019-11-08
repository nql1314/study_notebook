public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeIndex(nums, target, false)-1;

        return targetRange;
    }

    private int extremeIndex(int[] nums,int target,boolean isleft) {
        int start = 0;
        int end = nums.length;
        int mid;
        while (start<end) {
            mid = start + (end - start) / 2;
            if (nums[mid]>target || (isleft && nums[mid] == target)) {
                end = mid;
            } else start = mid + 1;
        }
        return start;
    }
}
