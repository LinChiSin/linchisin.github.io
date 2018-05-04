package com.linchisin.LeetCode;

import java.util.Arrays;
import java.util.Scanner;

public class SortColors75 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i < n; i++) {
                nums[i]=scanner.nextInt();
            }
            sortColors(nums);
            for(int i:nums){
                System.out.print(i+" ");
            }
        }

    }

    /*
    不允许使用自带排序方法讲数字进行排序
    思路：实现常见排序算法
    错误：想当然认为应该从-1/0开始排序，实际应当是start-1,start
     */
    public static void sortColors(int[] nums) {
        quickSort(nums,0, nums.length-1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if(start<end){
            int q=partition(nums,start,end);
            quickSort(nums,start,q-1);
            quickSort(nums,q+1,end);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot=nums[end];
        int i=start-1,j;
        for (j = start; j <end ; j++) {
            if(nums[j]<=pivot){
                i++;
                swap(nums,i,j);
            }
        }
        swap(nums,end,i+1);
        return i+1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }
}
