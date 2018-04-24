package com.linchisin.LeetCode;

import java.util.Scanner;

public class LengthOfLastWord58 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextLine()){
            System.out.println(lengthOfLastWord(scanner.nextLine()));
        }
    }

    /*
    思路：计数，遇到上一处空格并下一处非空格处重新计为1
         遇到上一处非空且此处也没空，计数加1
    复杂度：O（s.length）;
    特殊输入：输入为空，输入全部为空格，输入最后几个数为空格
     */
    private static int lengthOfLastWord(String s) {
//        String[] strings=s.split(" ");
//        if(strings.length==0) return 0;
//        return strings[strings.length-1].length();
        if(s.length()==0) return 0;
        int length=s.charAt(0)==' '?0:1;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i-1)==' '){
                if(s.charAt(i)!=' '){
                    length=1;
                }
//                else{
//                    length=0;
//                }
            }else{
                if(s.charAt(i)!=' '){
                    length++;
                }
            }
        }
        return length;
    }
}
