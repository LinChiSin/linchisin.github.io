package com.linchisin.LeetCode;

import java.util.Scanner;

public class AddBinary67 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextLine()){
            String a=scanner.nextLine();
            String b=scanner.nextLine();
            System.out.println(addBinary(a,b));
        }
    }

    /*
    描述：二进制字符串相加
    思路：从末尾比较，如果需要进位
    复杂度：O(n1,n2)
    特殊情况：两个给定均非空，最好先排序，小数加在大数上
     */
    private static String addBinary(String a, String b) {
        /*
        确保a比b长
         */
        if(a.length()<b.length()){
            String temp=b;
            b=a;
            a=temp;
        }
        /*
        将不等长字符串转换为等长字符数组
         */
        char[] chars=new char[a.length()];
        int length=chars.length;
        char[]charsA=a.toCharArray();
        char[]charsB=new char[a.length()];
        for (int i = 0; i < b.length(); i++) {
            charsB[length-1-i]=b.charAt(b.length()-1-i);
        }
        for (int i = b.length(); i <a.length(); i++) {
            charsB[length-1-i]='0';
        }

        int carry=0;//进位标志
        //从低位开始算起
        for (int i = 0; i <length; i++) {
            char a1=charsA[length-1-i];
            char b1=charsB[length-1-i];
            int ab=(int)a1-(int)'0'+(int)b1-(int)'0'+carry;
            switch (ab){
                case 0:
                    chars[length-1-i]='0';
                    carry=0;
                    break;
                case 1:
                    chars[length-1-i]='1';
                    carry=0;
                    break;
                case 2:
                    chars[length-1-i]='0';
                    carry=1;
                    break;
                case 3:
                    chars[length-1-i]='1';
                    carry=1;
                    break;
            }
        }
        if(carry==1){
           char []newChars=new char[length+1];
           newChars[0]='1';
            System.arraycopy(chars, 0, newChars, 1, newChars.length - 1);
            return new String(newChars);
        }
        return new String(chars);
    }
}
