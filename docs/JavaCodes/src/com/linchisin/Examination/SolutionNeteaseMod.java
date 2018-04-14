package com.linchisin.Examination;

import java.math.BigInteger;
import java.util.Scanner;

public class SolutionNeteaseMod {


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int l=scanner.nextInt();
        int r=scanner.nextInt();
        int num=0;
        int k=l;
        while(k<=r){
            BigInteger seriesNumK=getSeriesNum(k++);
            if(seriesNumK.remainder(BigInteger.valueOf(3)).compareTo(BigInteger.ZERO)==0)
                num++;
        }
        System.out.println(num);

    }

    public static BigInteger getSeriesNum(int k){
        StringBuilder stringBuilder=new StringBuilder(String.valueOf(1));
        int i=2;
        while(i<=k){
            stringBuilder.append(i++);
        }
        String numStrings=stringBuilder.toString();
        return new BigInteger(numStrings);

    }
}
