package com.linchisin.LeetCode;

import java.math.BigInteger;
import java.util.Scanner;


/*
编写”长整数相乘”程序，实现两个任意长度的长整数(正数)相乘，输出结果.

输入描述:
第一行输入数字A的字符串，字符范围（0～9），第二行输入数字B的字符串，字符范围（0～9）。
输出描述:
输出A、B俩数相乘的结果，结果为字符串。
 */


public class MultiStrings43 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String s1=scanner.nextLine();
            String s2=scanner.nextLine();
            System.out.println(solutionMultiply(s1,s2));
        }

    }
/*
    public static String multiply(String num1, String num2) {
        StringBuffer resBuffer;
        StringBuffer baseBuffer;

        if(num1==""||num2=="")
            return "";

        int num2Len = num2.length();

        baseBuffer = multiplyWithChar(num1,num2.charAt(num2Len-1));
        for(int i = num2Len-2;i>=0;i--){
            resBuffer = multiplyWithChar(num1,num2.charAt(i));//反的
            baseBuffer = addString(baseBuffer,resBuffer,num2Len-i-1);//反着加
        }
        //再反转
        baseBuffer.reverse();
        //从前向后除去多余0
        int i;
        int tmpLen = baseBuffer.length();
        for(i = 0;i<tmpLen;i++){
            if(baseBuffer.charAt(i)!='0')
                break;
        }
        if(i==tmpLen)
            return "0";
        baseBuffer.delete(0,i);
        return baseBuffer.toString();
    }

    private static StringBuffer addString(StringBuffer baseBuffer,StringBuffer unBaseBuffer,int index){
        int baseLen = baseBuffer.length();
        int unBaseLen = unBaseBuffer.length();

        char[] baseChar = baseBuffer.toString().toCharArray();
        char[] unBaseChar = unBaseBuffer.toString().toCharArray();

        StringBuffer res = new StringBuffer();
        for(int i = 0;i<index;i++)
            res.append(baseChar[i]);

        int baseIndex = index;
        int unBaseIndex = 0;

        int carry = 0;
        while(baseIndex<baseLen||unBaseIndex<unBaseLen){
            char base;
            char unBase;

            int tmpVal = 0;
            if(baseIndex<baseLen) {
                base = baseChar[baseIndex++];
                tmpVal += base - 48;
            }
            if(unBaseIndex<unBaseLen) {
                unBase = unBaseChar[unBaseIndex++];
                tmpVal += unBase - 48;
            }

            tmpVal += carry;
            carry = tmpVal/10;
            res.append((char)(tmpVal%10+48));
        }

        if(carry!=0)
            res.append((char)(carry+48));

        return res;
    }
    private static StringBuffer multiplyWithChar(String num1,char c){
        StringBuffer res = new StringBuffer();
        char[] numChar = num1.toCharArray();
        int val = c-48;

        int base;
        int carry = 0;
        int tmpVal;
        if(val==0)
        {
            res.append('0');
            return res;
        }
        for(int i = numChar.length-1;i>=0;i--){
            base = numChar[i]-48;
            tmpVal = base*val+carry;
            base = tmpVal%10;
            carry = tmpVal/10;
            res.append((char)(base+48));
        }
        if(carry>0)
            res.append((char)(carry+48));
        return res;
    }


   */


/*
思路：
 */

public static String solution(String s1, String s2){
    BigInteger num1=new BigInteger(s1);
    BigInteger num2=new BigInteger(s2);
    BigInteger product=num1.multiply(num2);
    return String.valueOf(product);


}


/*
 思路：按位相乘
 */
public static String solutionMultiply(String s1, String s2) {

    int[] digits = new int[s1.length() + s2.length()];

    for(int i = s1.length()-1; i >= 0; i--){
        for(int j = s2.length()-1; j >= 0; j--){
            digits[i + j + 1] += (s1.charAt(i)-'0') * (s2.charAt(j)-'0');
        }
    }
    int c = 0;
    for(int k = digits.length-1; k >= 0; k--){
        digits[k] += c;
        c = digits[k] / 10;
        digits[k] = digits[k] % 10;
    }

    int n = 0;
    while(n < digits.length && digits[n] == 0){
        n++;
    }

    StringBuilder stringBuilder = new StringBuilder();
    for (int i = n; i < digits.length; i++){
        stringBuilder.append(digits[i]);
    }
    return stringBuilder.length() > 0 ? stringBuilder.toString() : "0";
}







}
