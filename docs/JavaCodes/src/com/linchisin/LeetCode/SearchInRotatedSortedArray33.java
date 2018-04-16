package com.linchisin.LeetCode;

import java.util.Scanner;

public class SearchInRotatedSortedArray33 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int [] nums=new int[n];
            for (int i = 0; i <n; i++) {
                nums[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            System.out.println(search(nums,target));
        }
    }
    /*

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
     */


    /*
    思路：要求TC为O(log n),则不能通过遍历的方式进行搜索。
         最直观的方法则是二分搜索，但二分搜索的条件在于已知边界的有序数组，但该数组在未知位置被分割为左右两个有序子序列，且不能试图直接找出分割点。（因为那样的话时间复杂度会为O(n)）
         但该数组拥有另一个有用的性质：数组第一个元素一定是左子序列的最小值，数组最后一个元素一定是右子序列的最大值，此时比较目标数字和两个极值，

         若目标数字大于中间值小于最右边的值，则目标数字一定在右子序列，此后便可使用二分搜索。
         若目标数字大于中间值大于最右边的值？在目标数字位置待定，此时中间值到最右边的值仍然构成左右两个子序列，令中间值为新的最左边的值，则可递归判断
         若目标数字小于中间值且大于最左边的值？则目标数字一定在左子序列，此后便可使用二分搜索。
         若目标数字小于中间值且小于最左边的值，则目标数字位置待定，此时最左边的值到中间值仍然构成左右两个子序列，令中间值为新的最右边的值，则可递归判断。


         什么时候会返回-1？
   时间复杂度：TC(O(logn)), SC O(logn)
   特殊输入：数组长度为0
     */

    private static int search(int[] nums, int target) {
        if (nums.length==0)
            return 0;
        int start=0;
        int end=nums.length-1;
        return helper(nums,target,start,end);
    }

    private static int helper(int[] nums, int target, int start, int end) {
        if(start>end)
            return -1;
        if(nums[start]==target)
            return start;
        if(nums[end]==target)
            return end;
        int mid=(start+end)/2;
        if(nums[mid]==target)
            return mid;
        return -1;


    }
}
