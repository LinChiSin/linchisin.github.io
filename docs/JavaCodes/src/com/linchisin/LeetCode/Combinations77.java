package com.linchisin.LeetCode;

import java.util.*;

public class Combinations77 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int k=scanner.nextInt();
            System.out.println(combine(n,k));
        }
    }
    /*
     描述：列举从1到n中k个数的所有可能组合
           相当于在全排列的问题上剪纸
     思路：典型的回溯问题。递归、回溯
          递归起始条件：
          递归过程：
          递归结束条件：
          回溯机制：
     */
    public static List<List<Integer>> combine(int n, int k) {
        if(k<=0) return Collections.emptyList();
        List<List<Integer>>result=new ArrayList<>();
        int []nums=new int[n];
        for (int i = 1; i <=n; i++) {
            nums[i-1]=i;
        }
        int m=0;
        backtracking(result,nums,k,m);
        System.out.println("测试");
        return result;
    }

    private static void backtracking(List<List<Integer>> result, int []nums, int k,int m) {
        if(m==k){ //此处一定为k,而不是k-1
            List<Integer>list= new ArrayList<>();
            for (int i = 0; i < k; i++) {
                list.add(nums[i]);
            }
            result.add(list);
            return;
        }
        //怎么剪枝？
        for (int i = m; i<nums.length; i++) { //外层遍历
            swap(nums,i,m);
            if((i<=nums.length-2&&nums[m]<nums[m+1])||(i==nums.length-1)){
                backtracking(result,nums,k,m+1);
            }
            swap(nums,i,m);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }
}
