package com.linchisin.LeetCode;

import java.util.Scanner;

public class MaximumSubarray53 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i < n; i++) {
                nums[i]=scanner.nextInt();
            }
            System.out.println(maxSubarray(nums));
        }
    }

    /*
     作业帮原题，剑指offer原题
     思路1：数组从左往右遍历，
     复杂度：
     特殊输入：
     做题时未考虑到的特殊情况:全是负数
     */
    private static int maxSubarray(int[] nums) {
        int max=nums[0];
        int sum=nums[0];
        for (int i = 1; i < nums.length; i++) {
            //本质在于这个判断，即如果当前值比之间的累积和大，并且累积和小于0，累积和从当前值重新计算。
            if(nums[i]>=sum&&sum<0){
                sum=nums[i];
            }else{
                sum=sum+nums[i];
            }
            max=max<sum?sum:max;
        }
        return max;
    }
}
