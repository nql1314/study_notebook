/**
 * 题目描述：在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * 解题思路：要求复杂度为 O(N) + O(1)，也就是时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
 * 牛客网讨论区这一题的首票答案使用 nums[i] + length 来将元素标记，这么做会有加法溢出问题。
 * 这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上。
 */

public class Solution3 {
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(numbers==null||length<=0){
            return false;
        }
        for(int i=0;i<length;i++){
            while(numbers[i]!=i){ //第i个元素不等于i
                if(numbers[i]==numbers[numbers[i]]){//需要交换的位置已经存在正确位置的元素，说明该元素重复了
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers,i,numbers[i]);//如果不重复则交换位置
            }
        }
        return false;
    }
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
