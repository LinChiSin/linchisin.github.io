package com.linchisin.LeetCode;

import java.util.Scanner;

/*
Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class LongestSubstring3 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
        String string=scanner.nextLine();
        int length=lengthOfLongestSubstring(string);
        System.out.println(length);
        }
    }

    private static int lengthOfLongestSubstringBF(String s) {
        if(s.length()==0)
            return 0;
        int allSubstringNum=(s.length()*(s.length()+1))/2;
        String[]strings=new String[allSubstringNum];
        int []stringsLength=new int[allSubstringNum];
        int stringsNum=0;
        for (int k = 0; k <s.length() ; k++) {
            int i=k;
            StringBuilder substring=new StringBuilder();
            while(i<s.length()){
                while(i<s.length()&&!isContain(substring.toString(),s.charAt(i)))
                    substring.append(s.charAt(i++));
                strings[stringsNum]=substring.toString();
                stringsLength[stringsNum++]=substring.toString().length();
                substring.delete(0,substring.length());
            }
        }

         return getMax(stringsLength);

    }
    /*
    检查是否String string是否包含char c;
     */

    private static boolean isContain(String string, char c){
        boolean isContain=false;
        if(string==null)
            return isContain;
        for (int i = 0; i <string.length() ; i++) {
            if(string.charAt(i)==c)
                isContain=true;
        }
        return isContain;
    }
    /*
    获取int[]array中的最大值
     */
    private static int getMax(int[]array){
        int max=array[0];
        for (int i = 1; i <array.length ; i++) {
            if(array[i]>max){
                max=array[i];
            }
        }
        return max;
    }
    /*
    双指针（左指针为目标字符串的第一个字符的位置，滑动右指针为目标字符串的最后一个字符的位置，l=i-p+1），创建长度为128（ASCII码长度）的数组记录对应字符第一次出现时的位置，
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s.length()==0)
            return 0;
        int []hash=new int[128];
        int preP=0;
        int max=0;
        for (int i = 0; i <s.length() ; i++) {
            char c=s.charAt(i);
            if(hash[c]>preP)
                preP=hash[c];
            int l=i-preP+1;
            hash[c]=i+1;
            max=(l>max)?l:max;
        }
        return max;
    }
}
