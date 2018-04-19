package com.linchisin.LeetCode;

import java.util.Scanner;

public class SearchInsertPosition35 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i < n; i++) {
                nums[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            System.out.println(searchInsert(nums,target));
        }

    }

    /*
     数组有序排列，若指定元素在数组中，则找出指定元素的位置，否则找出将它插入数组后的位置
     数组中无重复元素
     */

    /*
    思路1：遍历数组，如指针元素与目标值相等，输出指针索引，否则的话判断前后是否满足a[i-1]<target<a[i+1];
          需要考虑指针越界的情况，a[-1]和a[N]
    复杂度：TC O(n)， SC O(1)
    思路2：创建新的数组a[N+1],将目标值放在最后，利用快速排序，得到有序数组，此时再利用二分搜索得到目标位置
    复杂度：TC O(nlogn),SC O(n)
    思路3：二分搜索？(需要判断数组的排序方向，升序还是降序？——————> 判断方法：数组第一个元素和数组最后一个元素比较)
          返回之后start=end的位置？
          通过设置边界扩展的条件，最后返回start>end是的start,可以证明此时start一定只比end+1
   复杂度：TC O(logn),SC O（logn）
    特殊输入：原始数组为空
     */
    private static int searchInsert(int[] nums, int target) {
        if(nums.length==0) return 0;
        boolean isAscend=nums[0]<=nums[nums.length-1];
        return binarySearch(nums,target,0,nums.length-1,isAscend);
    }

    private static int binarySearch(int[] nums, int target, int start, int end, boolean isAscend) {
        if(start>end)
            return start;
        if(nums[start]==target) return start;
        if(nums[end]==target) return end;
        int mid=start+((end-start)>>1);
        if(isAscend){
            if(nums[mid]>target) return binarySearch(nums,target,start,mid-1,true);  //只能使mid-1,即减小左半部分的最右点，保证有收缩，不会无线循环
            else return nums[mid]==target?mid:binarySearch(nums,target,mid+1,end,true);  //只能使mid+1,即减小右半部分的最左点，保证有收缩，不会无线循环
                                                                                                       //同样，还不能使end-1或者 mid,end均不减，因为会存在最后相邻节点的情况，导致mid==end
        }else{
            if(nums[mid]>=target) return nums[mid]==target?mid:binarySearch(nums,target,mid+1,end,false);
            else return binarySearch(nums,target,start,mid-1,false);
        }
    }
}
