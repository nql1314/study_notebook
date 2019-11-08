public class Solution42 {
    public int trap(int[] height) {
        if(height.length ==0) return 0;
        int left = 0;
        int right = height.length - 1;
        int left_max = height[0];
        int ans = 0;
        int right_max = height[right];
        while(left <right) {
            if (height[left] >= height[right]) {
                if (height[right] >= right_max) right_max = height[right];
                else ans += right_max-height[right];
                right--;
            } else {
                if (height[left] >= left_max) left_max = height[left];
                else ans += left_max-height[left];
                left++;
            }
        }
        return ans;
    }
}
