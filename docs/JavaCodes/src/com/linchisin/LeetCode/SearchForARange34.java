package com.linchisin.LeetCode;

import java.util.Scanner;

public class SearchForARange34 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i <n; i++) {
                nums[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            for(int i:searchRange(nums,target)){
                System.out.print(i);
            }
        }
    }
    /*
     给定一个降序排列的数组，要求在O(log n)时间范围内返回指定值在数组中的始末位置
     */


    /*
    思路：肯定需要二分搜索，但是二分搜索对存在包含相同数组的数组支持不太好。
         如果需要找出某个重复的元素，则需要指定是首次出现还是最后一次出现还是中间出现？（题目升级，如果指定是第二次出现呢？如果是倒数第二次出现呢？）
         如果需要求出首次出现的位置，则必定要求二分范围中的左边范围与指定元素相等。
         如果需要求出最后出现的位置，则必定要求二分范围中的右边范围与指定元素相等。
         其中特别要注意：一定要使得每次搜索范围需要减少，否则会出现永真调用，导致SOF。
         解决办法是除了通过首尾判断减少搜索范围外，还需要判断另一侧的临界值是否与指定元素相等来减少范围
         ！！！！
         错误！！！
         没有考虑相同元素跨过对半区域的情况！！！
         如 {1,3,3,3,4}寻找3
         则会返回2,3,而正确答案是1,3

         解决方案：
         必须特别注意中间值等于目标值时的区间划分：
         当需要找第一次出现的位置时，需要将中间值等于目标值的情况划分给左侧区域，而不是右侧区域
         当需要找最后一次出现的位置时，需要将中间值等于目标值的情况划分给右侧区域，而不是左侧区域

    复杂度：TC（Ologn），SC（Ologn）
    特殊输入：数组为空
     */
    private static int[] searchRange(int[] nums, int target) {
        if(nums.length==0)
            return new int[]{-1, -1};
        int[]index=new int[2];
        //先找第一次出现的位置
        index[0]= binarySearch(nums,target,0,nums.length-1,true);
        //再找最后出现的位置
        index[1]= binarySearch(nums,target,0,nums.length-1,false);
        return index;
    }

    private static int binarySearch(int[]nums, int target, int start, int end, boolean isAscend){  //原始数组升序排列
        if(start>end)
            return -1;
        if(isAscend){  //返回匹配的第一个值
            if(nums[start]==target) return start;  //当且仅当第一次出现时才返回
            int mid=start+((end-start)>>1);
            //特别注意，此时需要通过判断nums[mid]是否等于target来进一步收缩范围，
            //否则当指定数在数组范围外时，（小于最小值或大于最大值）会导致范围不再收缩，永远循环，造成SOF。

            //注意此处第二个关键点！
            //对nums[mid]==target的处理，必须将其划分为左侧区域，而不是右侧区域
            //此处不能改成if(nums[mid]<=target)
            if(nums[mid]<target) return nums[mid]==target?binarySearch(nums,target,mid,end,true):binarySearch(nums,target,mid+1,end,true);
            else return binarySearch(nums,target,start+1,mid,true);  //收缩范围
        }else{//返回匹配的最后一个值
            if(nums[end]==target) return end;   //当且仅当最后一次出现时才返回
            int mid=start+((end-start)>>1);
            // 特别注意，此时需要通过判断nums[mid]是否等于target来进一步收缩范围，
            //否则当指定数在数组范围外时，（小于最小值或大于最大值）会导致范围不再收缩，永远循环，造成SOF。

            //注意此处第二个关键点！
            //对nums[mid]==target的处理，必须将其划分为右侧区域，而不是左侧区域
            //此处不能改成if(nums[mid]<target)
            if(nums[mid]<=target) return binarySearch(nums,target,mid,end-1,false);  //收缩范围
            else return nums[start]==target?binarySearch(nums,target,start,mid,false):binarySearch(nums,target,start+1,mid,false);
        }
    }
}
