package com.linchisin.LeetCode;

import java.util.Scanner;

public class MergeSortedArray88 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int m=scanner.nextInt();
            int m2=scanner.nextInt();
            int []nums1=new int[m2];
            for (int i = 0; i < m2; i++) {
                nums1[i]=scanner.nextInt();
            }
            int n=scanner.nextInt();
            int []nums2=new int[n];
            for (int i = 0; i < n; i++) {
                nums2[i]=scanner.nextInt();
            }
            merge(nums1,m,nums2,n);
            for(int x:nums1){
                System.out.print(x+ " ");
            }
        }
    }

    /*
    描述：合并两个有序数组，具体是将nums2合并到nums1中
    思路：遍历nums中所有元素，将其插入到nums1中，使得nums1一种保持有序
    复杂度：
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int j=0;
        for (int i = 0; i < n; i++) {
            while (nums1[j]<=nums2[i]&&j<m+i){
                j++;
            }
            //将第j位到第m+i位往后挪一位
            for (int k = 0; k <m+i-j ; k++) {
                nums1[m+i-k]=nums1[m+i-1-k];
            }
            nums1[j]=nums2[i];
            j++;
        }
    }
}
