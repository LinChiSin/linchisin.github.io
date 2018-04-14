package com.linchisin.LeetCode;

import java.util.Scanner;

public class IntegerToRoman12 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            System.out.println(intToRoman(scanner.nextInt()));
        }
    }
    /*
    思路：首先确定num的最高位，然后从最高位递减，根据位数和数字确定不同的罗马数字；
     */
    private static String intToRoman(int num) {
        StringBuilder stringBuilder=new StringBuilder();
        int n=num;
        int digits=0;
        while (n!=0){
            n=n/10;
            digits++;
        }
        while(digits>0){
            stringBuilder.append(digitToRoman(digits,num/(int)(Math.pow(10,digits-1))));
            num=num%(int)(Math.pow(10,digits-1));
            digits--;
        }
        return stringBuilder.toString();
    }
    private static String digitToRoman(int digit,int num){
        StringBuilder stringBuilder=new StringBuilder();
        String [][]strings={{"IX","V","IV","I"},{"XC","L","XL","X"},{"CM","D","CD","C"}};
        switch (digit){
            case 4:
                while (num-->0){
                    stringBuilder.append("M");
                }
                break;
            default:
                while (num>0){
                    if(num==9){
                        stringBuilder.append(strings[digit-1][0]);
                        break;
                    }
                    if(num>=5){
                        stringBuilder.append(strings[digit-1][1]);
                        num-=5;
                        while(num>0){
                            stringBuilder.append(strings[digit-1][3]);
                            num--;
                        }
                        break;
                    }
                    if (num==4){
                        stringBuilder.append(strings[digit-1][2]);
                        break;
                    }else{
                        stringBuilder.append(strings[digit-1][3]);
                        num--;
                    }
                }
                break;
        }
        return stringBuilder.toString();
    }
}
