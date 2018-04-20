package com.linchisin.LeetCode;

import java.util.Scanner;

public class CountAndSay38 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            System.out.println(countAndSay(scanner.nextInt()));
        }
    }

    /*
    这个题目在Leetcode上的描述不准确，导致很多人理解题目出现问题（这个题目的难度在于准确理解题目描述）。下面重新描述一下题目：

首先给出前10个数字的序列：

1.  1
2.  11
3.  21
4.  1211
5.  111221
6.  312211
7.  13112221
8.  1113213211
9.  31131211131221
10. 13211311123113112211
题意就是

n=1时，输出字符串1；

n=2时，数上次字符串中各个数值的个数，因为上个数字字符串中有1个1，所以输出11；

n=3时，由于上个字符串是11，有2个1，所以输出21；

n=4时，由于上个数字的字符串是21，有1个2和1个1，所以输出1211，依次类推......
    */


    /*
    思路：递归，递归的初始条件或者说退出条件为n=1

     */

    private static String countAndSay(int n) {
        if(n==1){
            return String.valueOf(1);
        }
        String string=countAndSay(n-1);
        int i=0;
        StringBuilder stringBuilder=new StringBuilder();
        while(i<string.length()){
            char c=string.charAt(i);
            int k=1;
            while(i<=string.length()-2&&string.charAt(i)==string.charAt(i+1)){
                k++;
                i++;
            }
            stringBuilder.append(k);
            stringBuilder.append(c);
            i++;
        }
        return stringBuilder.toString();
    }
}
