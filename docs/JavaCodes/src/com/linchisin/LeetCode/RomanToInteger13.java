package com.linchisin.LeetCode;

import java.util.HashMap;
import java.util.Scanner;

public class RomanToInteger13 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(romanToInt(scanner.next()));
        }
    }
    /*
    思路：利用哈希建立互相联系
     */
    private static int romanToInt(String s) {
        HashMap<Character,Integer> hashMap=new HashMap<>();
        char[]romans={'I','V','X','L','C','D','M'};
        int[]numeral={1,5,10,50,100,500,1000};
        for (int i = 0; i <romans.length ; i++)
            hashMap.put(romans[i],numeral[i]);
        int len=s.length(),i=len-2;
        int num=hashMap.get(s.charAt(len-1));
        while (i>=0){
            if(hashMap.get(s.charAt(i))<hashMap.get(s.charAt(i+1)))
                num-=hashMap.get(s.charAt(i));
            else
                num+=hashMap.get(s.charAt(i));
            i--;
        }
        return num;
    }

    /*
    思路：先按照两个字符分解，是否包含："IX"、“IV”、“XC”、“XL”、“CM”、“CD”，如果有直接找出对应数字；
         否则按照一个字符分解，寻找对应的字符串；然后按照字符串找出对应的位数和数字，最后按照位数及其数字还原成数字。
         3999-MMMCMXCIX
     */
    private static int romanToIntComplex(String s) {
        char [] chars=s.toCharArray();
        int len=chars.length,i=len-2;
        int num=0;
        while(i>=0){
            String string=String.valueOf(chars[i]);
            string=string+String.valueOf(chars[i+1]);
            if(string.equals("IX")||string.equals("IV")||string.equals("XC")||string.equals("XL")||string.equals("CM")||string.equals("CD")){
                num+=romanCharToInt(string);
                i=i-2;
            }else{
                num+=romanCharToInt(String.valueOf(chars[i]));
                i=i-1;
            }
        }
        return num;
    }
    private static int romanCharToInt(String s){
        String [][]strings={{"IX","V","IV","I"},{"XC","L","XL","X"},{"CM","D","CD","C"}};
        int num=0;
        switch (s){
            case "M":
                num=1000;
                break;
            default:
                for (int i = 0; i <strings.length ; i++) {
                    for (int j = 0; j <strings[i].length ; j++) {
                        if(s.equals(strings[i][j])){
                            switch (j){
                                case 0:
                                    num=(int)Math.pow(10,i)*9;
                                    break;
                                case 1:
                                    num=(int)Math.pow(10,i)*5;
                                    break;
                                case 2:
                                    num=(int)Math.pow(10,i)*4;
                                    break;
                                case 3:
                                    num=(int)Math.pow(10,i);
                                    break;
                            }
                        }
                    }

                }
                break;
        }
        return num;
    }
}
