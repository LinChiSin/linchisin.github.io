package com.linchisin.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CombinationSum39 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []candidates=new int[n];
            for (int i = 0; i < n; i++) {
                candidates[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            System.out.println(combinationSum(candidates,target));
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
