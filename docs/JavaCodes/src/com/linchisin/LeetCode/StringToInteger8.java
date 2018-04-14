package com.linchisin.LeetCode;

import java.util.Scanner;

public class StringToInteger8 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String s=scanner.next();
            System.out.println(myAtoi(s));
        }
    }
    /*
     思路：字符串转字符数组，利用StringBuilder检测有效字符（+，-，0,1，...,9，要求+，-仅取数字前的第一位），获取有效字符数组。
         然后按照字符出现的顺序进行累乘；判断是否字符是否溢出，正溢出或者负溢出
     教训：long型整数存储可能会太小
     */
    public static int myAtoi(String s) {
        if(s.length()==0)
            return 0;
        StringBuilder stringBuilder=new StringBuilder();
        int signNum=0;
        int isNum=0;
        int charNum=0;
        s=s.trim();
        for (int i = 0; i <s.length() ; i++) {
            char c=s.charAt(i);
            if(((signNum>0&&(c=='+'||c=='-'))))
                return 0;
            if(((c<='9'&&c>='0')||c=='+'||c=='-')){
                if((c=='+'||c=='-')&&signNum<1&&isNum<1){
                    signNum++;
                    stringBuilder.append(c);
                }else if((c<='9'&&c>='0')&&(charNum==0)){
                    isNum++;
                    stringBuilder.append(c);
                }
            }else{
                charNum++;
            }
        }
        String newString=stringBuilder.toString();
        if(newString.length()==1&&(newString.equals("+")||newString.equals("-")))
            return 0;
        char c;
        double num=0;
        if(newString.startsWith("+")||newString.startsWith("-")){//包含+，-符号
            for (int i = 1; i <newString.length() ; i++) {
                c=newString.charAt(i);
                num=num*10+(c-'0');
            }
            if(newString.startsWith("-"))
                num=-1*num;
        }else{// 不包含+，-符号
            for (int i = 0; i <newString.length() ; i++) {
                c=newString.charAt(i);
                num=num*10+(c-'0');
            }
        }
        if(num>Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if(num<Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }else
            return (int)num;

    }
}