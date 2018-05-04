package com.linchisin.LeetCode;

import java.util.Scanner;

public class RemoveDuplicates80 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int [] nums=new int[n];
            for (int i = 0; i < n; i++) {
                nums[i]=scanner.nextInt();
            }
            System.out.println(removeDuplicates(nums));
            for(int k:nums){
                System.out.print(k+" ");
            }
        }
    }

    /*
    描述:给定一个有序数组，移除数组中必要的元素，使得数组前n位的数出现不超过两次，要求原址操作，不得开辟新的内存空间
    思路：双指针，指针i维护满足题意的小数组，指针j维护原来的大数组，将nums[j,j+1,...,n-1]移至nums[i+1,i+2,...,n-j+i]。
         指针m用于指向调整后的小数组
         类似数组的删除和插入元素，需要O(n)复杂度
    复杂度：O(n^2)
    本质：维护两个区域，并且保证细节完善
     */
    public static int removeDuplicates(int[] nums) {
        int i=1,j=1;
        int m=j;
        while(j<nums.length){
            int k=1;
            while(k<2&&j<nums.length&&nums[i]==nums[i-1]){
                i++;
                k++;
                j++;
                m++;
                if(j==nums.length) return i;
            }
            //改进此处,此处应当用nums[m]==nums[m-1]进入循环，即从i到j还存在其他元素
            while(nums[m]==nums[m-1]&&j<nums.length&&nums[m]==nums[i]){
                j++;
                m++;
                if(j==nums.length) return i;
            }
            insert(nums,i,m);
            m=++i;
            j++;
        }
        return i;
    }

    // 将nums[j,j+1,...,n-1]前移至nums[i+1,i+2,...,n-j+i]。
    //此函数复杂度为O(n-j)
    private static void insert(int[] nums, int i, int j) {
        if(j>i){
            for (int k = 0; k <nums.length-j; k++) {
                int temp=nums[i+k];
                nums[i+k]=nums[j+k];
                nums[j+k]=temp;
            }
        }
    }
}
