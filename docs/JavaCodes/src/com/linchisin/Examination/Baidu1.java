package com.linchisin.Examination;

import java.util.Scanner;

/*
输入
        第一行包含一个字符串s，表示街道。s只包含字符‘E’和‘W’，长度不超过105。

        输出
        输出对应的答案。


        样例输入
        EEW

        样例输出
        1

        */
public class Baidu1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int sum=solution(s);
        System.out.println(sum);

    }
    private static int solution(String s){
        char[] chars=s.toCharArray();
        int []num=new int[s.length()+1];
        for (int i = 0; i <=chars.length; i++) {
            String left;
            String right;
            if(i==0){
                left=null;
                right=s;
            } else if(i==chars.length){
                left=s;
                right=null;
            }else{
            left=s.substring(0,i);
            right=s.substring(i,s.length());
            }
            int leftE=getCharNum(left,'E');
            int rightW=getCharNum(right,'W');
            num[i]=leftE+rightW;
        }
        return getMin(num);

    }

    private static int getCharNum(String s,char c){
        int count=0;
        if(s==null){
            return count;
        }
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)==c)
                count++;
        }
        return count;
    }

    public static int getMinIndex(int [] array){
        int minIndex=0;
        for (int i = 1; i <array.length ; i++) {
            if (array[minIndex]>array[i]) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static int getMin(int [] array){
        int minIndex=getMinIndex(array);
        return array[minIndex];
    }
}
