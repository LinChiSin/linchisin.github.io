package com.linchisin.LeetCode;


import java.util.Arrays;
import java.util.HashMap;

public class TestApi {
    public static void main(String[] args) {


        char[]chars="cba".toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i <chars.length; i++) {
            System.out.print(chars[i]);
        }
        String charString=chars.toString();
        String charString2=new String(chars);
        System.out.println(charString);
        System.out.println(charString2);



    }
}
