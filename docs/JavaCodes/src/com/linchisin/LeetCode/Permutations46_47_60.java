package com.linchisin.LeetCode;

import java.util.*;

public class Permutations46_47_60 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
//            int n=scanner.nextInt();
//            int []nums=new int[n];
//            for (int i = 0; i <n; i++) {
//                nums[i]=scanner.nextInt();
//            }
//            System.out.println(permute(nums));
            int n=scanner.nextInt();
            int k=scanner.nextInt();
            System.out.println(getPermutation(n,k));
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




    /*
    题意描述：求n!排列中第k个排列
    思路：找规律？
        或者是迭代求NextPermutation(), 复杂度
    复杂度：TC：O（n），SC:O(n)
    特殊输入：（k<=n!）

     */


    public static String getPermutation(int n,int k){
        List<Integer>list=new ArrayList<>();
        for (int i = 1; i<= n; i++) {
            list.add(i);
        }
        k=k-1;
        int m=getFactorial(n);
        StringBuilder stringBuilder=new StringBuilder();
        while(k!=0){
            m=m/n;
            n--;
            stringBuilder.append(list.get(k/m));
            list.remove(k/m);
            k=k%m;
        }
        for (Integer aList : list) {
            stringBuilder.append(aList);
        }
       return stringBuilder.toString();
    }

    //计算n！阶乘
    public static int getFactorial(int n){
        if(n==0)
            return 1;
        else{
            return getFactorial(n-1)*n;
        }
    }


    public static String getPermutationIteration(int n,int k){

        int []nums=new int[n];
        for (int i = 0; i < n; i++) {
            nums[i]=i+1;
        }
        for (int i = 0; i < k-1; i++) {
            nextPermutation(nums);
        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <n; i++) {
            stringBuilder.append(nums[i]);
        }
        return stringBuilder.toString();
    }


    private static void nextPermutation(int[] nums) {
        int i=nums.length-2;
        int j,k,index;
        while(i>=0){              //找出不再呈降序排列的第一个数
            if(nums[i]<nums[i+1])  //注意此处：不能是小于等于，只能是小于。（反例：2,2，2,2）
                break;
            i--;
        }
        if(i<0){
            reverse(nums,0,nums.length-1);  //原始数组已经完全降序排列，直接反转全部数组
        }else{
            j=i;            //从找出比nums[i]大的第一个数
            k=nums[i+1];
            index=i+1;
            while(j<=nums.length-2){
                if(nums[j+1]>nums[i]){
                    if(k>=nums[j+1]){  //注意此处：不能是大于，只能是大于等于，否则不能构成k后边为降序排列
                        k=nums[j+1];
                        index=j+1;
                    }
                }
                j++;
            }
            swap(nums,i,index);
            reverse(nums,i+1,nums.length-1);
        }
    }

    /*
    交换数组中第i位和第j位
     */
    private static void swap(int[] nums, int i, int j) {
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }
    /*
    将数组中第i位到第j位反转
     */
    private static void reverse(int[] nums, int i, int j) {
        for (int k = 0; k <=(j-i)>>1 ; k++) {
            swap(nums,i+k,j-k);
        }
    }
}
