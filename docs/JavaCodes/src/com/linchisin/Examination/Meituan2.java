package com.linchisin.Examination;

import java.util.Scanner;

public class Meituan2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []digits=new int[n];
            for (int i = 0; i <n ; i++) {
                digits[i]=solve(scanner.nextInt());
            }
            for(int i:digits)
                System.out.println(i);
        }

    }

    private static int solve(int n) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 1; i <=n; i++) {
            stringBuilder.append(String.valueOf(i));
        }

        int digits=0;
        int m=n;
        while(m!=0){
            m/=10;
            digits++;
        }
        int k=(int)Math.pow(10,digits-1);
        int diff=n-k+1;
        int digit=0;
        for (int i = 1; i <digits ; i++) {
            digit+=9*Math.pow(10,i-1)*i;
        }
        digit+=diff*digits;

        return digit;
    }
}
