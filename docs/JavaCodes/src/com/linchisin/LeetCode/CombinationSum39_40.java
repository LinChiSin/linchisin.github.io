package com.linchisin.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CombinationSum39_40 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []candidates=new int[n];
            for (int i = 0; i < n; i++) {
                candidates[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            System.out.println(combinationSum2(candidates,target));
        }
    }
    /*
    思路：
    递归（树的深度优先搜索）、回溯、剪枝、去重
    递归的结束条件是，target==0,将临时集中加入到结果集中
    剪枝的思路是先排序（数字的大小按规则排放），然后判断目标值是否大于现有值，如果已经大于则不再继续，即剪枝
    回溯的思路是去除临时集中的最后一个元素
    外层遍历则是遍历足够多的树
    去重的思路是：遍历从下一个元素开始，不再重复从本元素进入。同时，根据题意，即使两个数字位置不同，元素个数仍然相同，也记作重复，因此需要判断前后元素是否相等
    复杂度：本质上是暴力搜索
    特殊输入：原始数组为空，无法组成无解

     */
    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>>result=new ArrayList<>();
        List<Integer>temp=new ArrayList<>();
        Arrays.sort(candidates); //排序数组，用于剪枝
        //最后一个参数用于遍历
        dfsWithoutDuplicates(result,temp,candidates,target,0);
        return result;


    }

    private static void dfs(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int i) {
        //递归的结束条件
        if(target==0){
            result.add(new ArrayList<>(temp));
        }
        //递归过程
        for(int j=i;j<candidates.length&&target>=candidates[j];j++){
            temp.add(candidates[j]);
            dfs(result,temp,candidates,target-candidates[j],j);
            temp.remove(temp.size()-1);
        }
    }

    private static void dfsWithoutDuplicates(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int i) {
        //递归的结束条件
        if(target==0){
            result.add(new ArrayList<>(temp));
        }
        //递归过程：持续向树的底层搜索
        for(int j=i+1;j<candidates.length&&target>=candidates[j];j++){  //去重
            if(candidates[j]!=candidates[j-1]){
                temp.add(candidates[j]);
                dfsWithoutDuplicates(result,temp,candidates,target-candidates[j],j);
                temp.remove(temp.size()-1);
            }
        }
    }








    /*
    思路：Naive算法:给定数字为n，考虑1到n个数字的和，转化nSum问题
         本题中允许数字重复
         1.判断整除关系，如果能整除直接返回商个除数
         2.判断余数关系，如果给定一个i,i整除target后的余数也在数组中，返回商个除数再加上余数
         3.判断输出列表是否重复？——>判断列表是否相等
         4.上面这种思路首先就只适用于两个数字组合的情况，如果有大于2个组合呢？，其次可能还会有漏解的情况。


    复杂度：O(n)+o(n^2)+...+O(n^(n-1))，
    特殊输入：不存在解？
     */

    private static List<List<Integer>> combinationSumWrong(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList<>();
        for (int i = 0; i <candidates.length ; i++) {
            //判断是否整除
            int remainder=target%candidates[i];
            if(remainder==0) {
                List<Integer> list = new ArrayList<>();
                int k = target / candidates[i];
                for (int j = 0; j < k; j++) {
                    list.add(candidates[i]);
                }
                result.add(list);
            }else{ //不整除，判断余数是否在数组中
                for (int j = 0; j <candidates.length ; j++) {
                    if(candidates[j]==remainder){
                        List<Integer> list = new ArrayList<>();
                        int k = target / candidates[i];
                        for (int l = 0; l < k; l++) {
                            list.add(candidates[i]);
                        }
                        list.add(candidates[j]);
                        result.add(list);
                    }
                }


            }
        }

        return result;
    }



    /*
    思路：回溯（递归加剪枝）
     */
    public static List<List<Integer>> combinationSum(int[] candidates,
                                              int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    public static void getResult(List<List<Integer>> result,
                          List<Integer> current, int[] candiates, int target, int start) {
        if (target > 0) {
            for (int i = start; i < candiates.length && target >= candiates[i]; i++) {
                current.add(candiates[i]);
                getResult(result, current, candiates, target - candiates[i], i);
                current.remove(current.size() - 1);
            }
        } else if (target == 0) {
            result.add(new ArrayList<Integer>(current));
        }
    }




}
