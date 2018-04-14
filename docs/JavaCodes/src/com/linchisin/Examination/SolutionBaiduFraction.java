package com.linchisin.Examination;

import java.util.Scanner;

public class SolutionBaiduFraction {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int k=scanner.nextInt();
        int []n=new int[k];
        double []sum=new double[k];
        for (int i = 0; i <k; i++) {
            n[i]=scanner.nextInt();
            sum[i]=solution(n[i]);
        }
        for(double i:sum){
            String string=String.format("%.4f",i);
            System.out.println(string);
        }
    }
    private static double solution(int n){
        int numerator=2;
        int denominator=1;
        int temp;
        double sum=numerator/denominator;
        if(n==1)
            return sum;
        int i=2;
        while (i<=n){
            temp=numerator;
           numerator+=denominator;
           denominator=temp;
           sum+=(double)numerator/denominator;
           i++;
        }
        return sum;

    }


}
