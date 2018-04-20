package com.linchisin.Examination;

public class Meituan1 {
    public static void main(String[] args) {

    }

    static public int gcd1_1(int x, int y)   //非递归的辗转相除法
    {
        int temp;

        do{
            temp = x % y;
            x = y;
            y = temp;
        }while(temp != 0);

        return x;
    }
}
