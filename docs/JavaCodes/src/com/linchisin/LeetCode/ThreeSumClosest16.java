package com.linchisin.LeetCode;

import java.util.Arrays;
import java.util.Scanner;

public class ThreeSumClosest16 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            System.out.println(threeSumClosest(nums,target));
        }
    }

    private static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len=nums.length;
        int i,left,right,diff;
        int min=nums[0]+nums[1]+nums[len-1];
        for (i = 0; i <len-1 ; i++) {
            left=i+1;right=len-1;
            while (left<right){
                 diff=nums[i]+nums[left]+nums[right]-target;
                 min=Math.abs(min-target)<Math.abs(diff)?min:target+diff;
                 if(diff<0){
                     left++;
                 }else if(diff>0){
                     right--;
                 }else
                     return target;
            }
        }
        return min;
    }

}
