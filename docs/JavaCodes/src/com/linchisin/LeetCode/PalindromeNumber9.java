package com.linchisin.LeetCode;

import java.util.Scanner;

public class PalindromeNumber9 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            System.out.println(isPalindrome(scanner.nextInt()));
        }
    }

    /*
    思路：1.将整型数字转换为字符数组，利用回文数性质a[i]==a[n-i]检验，需要额外空间；
         2.检验最高位是否等于最低位，如不等于，直接返回false,否则最高位右移，最低位左移，需要首先遍历一遍数字，确定数字的最高位，复杂度O(log(10)(n))；
         3.将原数字反转，判断反转的数字是否与原数字相同。小技巧：可以通过判断计算过程中的那个逆序数是否比不断除 10 的数大，减少结束计算即可。
           注意可能带来的问题是：10的某些倍数如10010，也会被判断为true;可以通过最先判断是否为10的倍数，10的倍数不可能为回文数。
         注意负数不能是回文数；注意某些基本数据不占用其它空间。
     */
    private static boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int digits=0;
        int num=x;
        while(num!=0){
            num/=10;
            digits++;
        }
        while(x!=0&&digits>=2){
            if ((int)(x/Math.pow(10,digits-1))!=x%10)
                return false;
            x=(x/10)%(int)Math.pow(10,digits-2);
            digits=digits-2;
        }
        return true;
    }
}
