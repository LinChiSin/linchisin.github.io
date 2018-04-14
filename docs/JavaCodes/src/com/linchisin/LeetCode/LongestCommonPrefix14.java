package com.linchisin.LeetCode;

import java.util.Scanner;

public class LongestCommonPrefix14 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            String[]strs=new String[n];
            for (int i = 0; i <strs.length ; i++) {
                strs[i]=scanner.nextLine();
            }
            System.out.println(longestCommonPrefix(strs));
        }

    }

    /*
    思路：长度为m的字符串前缀有（m+1）个；
         暴力遍历大小为n的字符串数组的所有前缀需要O(n*MaX(m)),判断k1和k2长度的两个字符串是否相等最长需要O(Min(k1,k2))；
         因此最终公共前缀需要O(n^3)，不能满足题意。

         双指针，右、下两个指针，从右（最短串的右边）往左遍历，从上往下遍历，O(n^2)
     */
    private static String longestCommonPrefix(String[] strs) {
        //找出最短串
        if(strs.length==0)
            return "";
        int minLen=strs[0].length();
        int strNum=strs.length;
        for (int i = 1; i <strs.length; i++) {
            if(minLen>strs[i].length())
                minLen=strs[i].length();
        }
        if(minLen==0)
            return "";
        int right=minLen-1;
        int down,end=right+1;
        while(right>=0){
            down=1;
            while(down<=strNum-1){
                if(strs[down].charAt(right)!=strs[down-1].charAt(right)){
                    end=right;
                    break;
                }
                down++;
            }
            right--;
        }
        return strs[0].substring(0,end);
    }
}
