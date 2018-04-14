package com.linchisin.LeetCode;

import java.util.Scanner;

public class ReverseInteger7 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            int x=scanner.nextInt();
            System.out.println(reverse(x));
        }
    }
/*
 思路：借助Java现有函数String.valueOf(int)和Integer.parseInt();
      将int数组转换为字符串及字符数组，通过反转字符数组实现数据反转。
      需要考虑两个问题：1.符号位不参与反转(分类考虑)；2.数据溢出时应当捕获异常返回0（try/catch语句，数据输入异常）；
 */
    private static int reverseJava(int x) {
        String s=String.valueOf(x);
        char[]chars=s.toCharArray();
        if(chars[0]=='-'){
            for (int i = 1; i <=chars.length/2 ; i++) {
                char temp=chars[chars.length-i];
                chars[chars.length-i]=chars[i];
                chars[i]=temp;
            }
        }else{
            for (int i = 0;i<=(chars.length-1)/2 ; i++) {
                char temp=chars[chars.length-1-i];
                chars[chars.length-1-i]=chars[i];
                chars[i]=temp;
            }
        }
        s=new String(chars);
        try{
        return Integer.parseInt(s);
        }catch(Exception e){
            return 0;
        }
    }
    /*
    思路：利用数学性质，反复除10和对10取余.
         需要考虑一个问题：int数据溢出处理机制。(预存在long型数据里，最后判断是否溢出||进行新数重复检验)
     */
    private static int reverse(int x){
        long newNum=0,remainder;
        while(x!=0){
            remainder=x%10;
            x=x/10;
            newNum=newNum*10+remainder;
            /*
            if((newNum-remainder)/10!=num)
               return 0;
             */
        }
        return newNum>Integer.MAX_VALUE||newNum<Integer.MIN_VALUE?0:(int)newNum;

    }
}
