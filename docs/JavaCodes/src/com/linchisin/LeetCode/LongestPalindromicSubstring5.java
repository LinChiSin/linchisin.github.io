package com.linchisin.LeetCode;

import java.util.Scanner;

public class LongestPalindromicSubstring5 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
        String s=scanner.nextLine();
        System.out.println(longestPalindrome(s));
        }

    }

    /*
    暴力搜索，0(n^2)子字符串，o(n)检查是否为回文
     */
    private static String longestPalindromeBF(String s) {
        String palin=null;
        int max=0;
        if(s.length()==0)
                return String.valueOf(-1);
        for (int i = 0; i <s.length() ; i++) {
            for (int j = i; j <s.length() ; j++) {
                String substring=s.substring(i,j+1);
                if(isPalin(substring)){
                    if(max<substring.length()){
                        max=substring.length();
                        palin=substring;
                    }
                }
            }
        }
        return  palin;
    }

    //双指针
    private static String longestPalindromeDoublePointer(String s) {
        String palin=null,substring=null;
        int max=0;
        if(s.length()==0)
            return String.valueOf(-1);
        int left,right,j,k,breakNum;
        for (int i = 0; i <s.length() ; i++) {
            k=0;
            breakNum=0;
            while(k<1){
                left=i;
                right=s.length()-1-breakNum;
                j=right;
                while(left<right){
                    if(s.charAt(left)==s.charAt(right)){
                        left++;
                        right--;
                    }else{
                        if(left>i){
                            breakNum++;
                            break;
                        }
                        right--;
                        j--;
                    }
                }
                if(left>=right){
                    substring=s.substring(i,j+1);
                    k++;
                }else
                    substring=s.substring(i,i+1);
            }
            if(max<substring.length()){
                palin=substring;
                max=substring.length();
            }
        }
        return  palin;
    }

    private static String longestPalindrome(String s) {
        int length=s.length();
        if(length<=1)
            return s;
        int first=0,end=0,k;
        int lengthMinus1=length-1;
        boolean[][]dp=new boolean[length][length];
        char[] chars = s.toCharArray();
        for (int i = lengthMinus1; i>=0; i--) {
            for (int j =0; j<=lengthMinus1-i ;j++) {
                k=i+j;
                if(j==0)
                    dp[i][k]=true;
                else if(j==1){
                    dp[i][k]=chars[i]==chars[k];
                }else{
                    dp[i][k]=dp[i+1][k-1]&&(chars[i]==chars[k]);
                }
                if(dp[i][k]&&j>=end-first){
                    end=k;
                    first=i;
                }
            }
        }
        return  s.substring(first,end+1);
    }
    private static boolean getDP(String s,boolean[][]dp,int i,int j){
        if(j==i)
            dp[i][j]=true;
        else if(j==i+1){
            dp[i][j]=s.charAt(i)==s.charAt(j);
        }else{
            dp[i][j]=dp[i+1][j-1]&&(s.charAt(i)==s.charAt(j));
        }
        return dp[i][j];
    }


    //判断字符串是否为回文字符串
    private static boolean isPalin(String s){
        int n=s.length();
        if(n==0)
            return false;
        for (int i = 0; i <=(n-1)/2 ; i++) {
            if(s.charAt(i)!=s.charAt(n-1-i))
                return false;
        }
        return true;
    }
}
