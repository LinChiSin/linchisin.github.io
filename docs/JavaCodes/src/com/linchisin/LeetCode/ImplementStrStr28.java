package com.linchisin.LeetCode;

import java.util.Scanner;

public class ImplementStrStr28 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String haysatck=scanner.next();
            String needle=scanner.next();
            System.out.println(strStr(haysatck,needle));
        }
    }

    /*
    思路： 遍历字符串，直到找到首字母相同的情况，
    教训： 完备性考虑，重叠情况考虑

    程序：鲁棒性（特殊测试样例，分类思想的完备性）

    特殊情况: needle的长度大于haystack
            needle、haystack均为空
            needle、haystack部分为空
     */


    private static int strStr(String haystack, String needle) {
        int lenStack=haystack.length();
        int lenNeedle=needle.length();
        if(lenNeedle==0)
            return 0;
        if(lenStack==0)
            return -1;
        int i=0,j=0,index;
        while (i<lenStack){
            if(haystack.charAt(i)==needle.charAt(j)){
                index=i;
                while (i+j<lenStack&&j<lenNeedle&&haystack.charAt(i+j)==needle.charAt(j)){
                    j++;
                    if(j==needle.length()){
                        return index;
                    }
                }
                j=0;
            }
            i++;
        }
       return -1;
    }
}
