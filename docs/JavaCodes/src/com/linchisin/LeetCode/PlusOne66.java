package com.linchisin.LeetCode;

import java.util.Scanner;

public class PlusOne66 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []digits=new int[n];
            for (int i = 0; i < n; i++) {
                digits[i]=scanner.nextInt();
            }
            int []sum=plusOne(digits);
            for(int i:sum){
                System.out.print(i+" ");
            }
        }
    }
    /*
    描述：数组代表一个数字，令数组加1
    思路：主要是考虑进位
    复杂度：TC O(n)，SC O（1）
    特殊情况：数组为空，
     */
    private static int[] plusOne(int[] digits) {
        int length=digits.length;
        if(length==0) return null;
        while(digits[length-1]+1>9){
            if(length-2>=0){
                digits[length-1]=0;
                length--;
            }else{
                int []newDigits=new int[digits.length+1];
                newDigits[0]=1;
                return newDigits;
            }
        }
        digits[length-1]+=1;
        return digits;
    }
}
