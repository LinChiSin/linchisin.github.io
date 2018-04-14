package com.linchisin.LeetCode;

import java.math.BigInteger;
import java.util.Scanner;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int dividend=scanner.nextInt();
            int divisor=scanner.nextInt();
            System.out.println(divideCorrect(dividend,divisor));
        }
    }

    /*
    思路：两个正整数的除法等效于循环的减法，将被除数循环减去除数，统计减法次数，直到差小于除数，循环停止。
         负数的除法可以转换为整数的除法的相反数。
    复杂度：O(dividend/divisor)   //当dividend=Max_Int,divisor=1时运算次数过多，会超出运算时间限制
    特殊情况：被除数为0/除数为0/除数比被除数小
     */

    /*
    思路2：快速除法，找出除数与2的幂次的成绩中比被除数a大的最小数对应的幂次，此时2的幂次p，q是二分搜索的下界，此后在p,q中不断二分搜索，直到
     */

    private static int divide(int dividend, int divisor) {
        //先仅考虑正整数的除法
        if(divisor==0)
            return Integer.MAX_VALUE;
        int newDividend=abs(dividend),newDivisor=abs(divisor);
        int diff=newDividend-newDivisor;
        int quotient=diff<0?0:1;
        while (diff>=newDivisor){
            diff-=newDivisor;
            quotient++;
        }
        if((dividend>=0&&divisor>0)||(dividend<0&&divisor<0)){
            return quotient;
        }else{
            return opposite(quotient);
        }
    }
    private static int quickDivision(int dividend,int divisor){
        //先仅考虑正整数的除法
        if(divisor==0)
            return Integer.MAX_VALUE;
        if(dividend==Integer.MIN_VALUE&&divisor==-1){
            return Integer.MAX_VALUE;
        }
        int newDividend=abs(dividend),newDivisor=abs(divisor);
        int count=0,power=1,mid;
        while (multiply(power,newDivisor)<=newDividend){
            count++;
            power=power<<1;
        }
        int p=power,q=power>>1;
        for (int i = 1; i <=count-1 ; i++) {
            int mean=(p+q)>>1;
            mid=multiply(mean,newDivisor);
            if(mid<=newDividend){
                q=mean;
            }else{
                p=mean;
            }
        }
        if((dividend>=0&&divisor>0)||(dividend<0&&divisor<0)){
            return q;
        }else{
            return opposite(q);
        }
    }

    public static  int divideCorrect(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long dvd = Math.abs((long) dividend);
        long dvr = Math.abs((long) divisor);
        int res = 0;
        while (dvd >= dvr) {
            long temp = dvr, multiple = 1;
            while (dvd >= temp << 1) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            res += multiple;
        }
        return (dividend < 0) ^ (divisor < 0) ? -res : res;
    }


    /*
    采用移位实现正整数的乘法：
    例子：5*3，其中3=0011,则5*3=5<<0+5<<1;
     */
    private static int multiply(int num1,int num2){
       int newNum2=abs(num2);
       int length=getBits(newNum2);
       int index=0;
       int base=num1;
       int sum=0;
       while (index<length){
           int bit=getIndexBit(newNum2,index);
           if(bit==1){
               sum+=(base<<index);
           }
           index++;
       }
       return num2<0?opposite(sum):sum;
    }

    //获取十进制数的二进制位数
    private static int getBits(int num){
        if(num==0)
            return 1;
        int numLen=0;
        while (num!=0){
            numLen++;
            num=num>>1;
        }
        return numLen;
    }
    //获取从右到左的第pos位置的值1/0
    private static int getIndexBit(int num, int pos){
        int index=1<<pos;
        if(((num&index)>>pos)==1)
            return 1;
        else
            return 0;
    }
    private static int abs(int num){
        return num<0?(0-num):num;
    }
    private static int opposite(int num){
        return 0-num;
    }
}
