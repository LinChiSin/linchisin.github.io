package com.linchisin.LeetCode;

import java.util.*;

public class Permutations46_47 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i <n; i++) {
                nums[i]=scanner.nextInt();
            }
            System.out.println(permute(nums));
        }
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        dfsWithoutDuplicates(result,nums,0);
        return result;
    }

    /*
     回溯：递归（DFS深搜），确保递归结束条件，遍历条件（swap(i,j)），回溯机制(swap(i,j))
     */

    private static void dfs(List<List<Integer>> result, int[] nums, int i) {
        //递归的退出条件
        if(i==nums.length){
            List<Integer>temp=new ArrayList<>();
            for(int k:nums) temp.add(k);
            result.add(temp);
        }
        for (int j = i; j <nums.length ; j++) {   //外层遍历
            swap(nums,j,i);   //外层遍历
            dfs(result,nums,i+1);  //递归,注意此处一定是i+1,为什么呢？
            swap(nums,j,i);  //回溯机制
        }
    }

    private static void dfsWithoutDuplicates(List<List<Integer>> result, int[] nums, int i) {
        //递归的退出条件
        if(i==nums.length){
            List<Integer>temp=new ArrayList<>();
            for(int k:nums) temp.add(k);
            result.add(temp);
        }
        Set<Integer> hashSet=new HashSet<>();
        for (int j = i; j <nums.length ; j++) {   //外层遍历
            if(hashSet.add(nums[j])) {  //确保同层树节点不重复
                swap(nums, j, i);   //外层遍历
                dfsWithoutDuplicates(result, nums, i + 1);  //递归,注意此处一定是i+1,为什么呢？
                swap(nums, j, i);  //回溯机制
            }
        }
    }


    /*
    思路：递归（深度优先搜索），退出递归的条件是遍历指针等于数组长度，则输出结果
         递归的过程是：将当前元素位置与当前子数组的第一个元素交换，并对子数组持续遍历
         回溯机制：逆交换回去
         去重机制：同层级的子结点不允许重复，可用哈希表或者哈希set存储

     */
    private List<List<Integer>>permuteUnique(int []nums){
        List<List<Integer>> result=new ArrayList<>();
        //参数需要一个遍历指针
        dfsReWithoutDuplicates(result,nums,0);
        return result;
    }

    private void dfsReWithoutDuplicates(List<List<Integer>> result, int[] nums, int i) {
        //递归退出的条件
        if(i==nums.length){
            List<Integer>temp=new ArrayList<>();
            for(int num:nums) temp.add(num);
            result.add(temp);
        }
        //同层级之间需要用HashSet判断
        Set<Integer>hashSet=new HashSet<>();
        //外层遍历
        for (int j = i; j <nums.length ; j++) {
            if(hashSet.add(nums[j])) {
                swap(nums, i, j);//外层遍历机制
                dfs(result, nums, i + 1);  //此处一定是i+1，不是j+1;
                swap(nums,i,j); //回溯机制
            }
        }
    }


    private static void swap(int[] nums, int i, int j) {
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }
}
