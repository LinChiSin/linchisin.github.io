package com.linchisin.Examination;

import java.util.Scanner;

public class Tencent1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int M=scanner.nextInt();
        String [] strings=new String[N];
        char[][]chars=new char[N][M];
        for (int i = 0; i <N ; i++) {
             strings[i]=scanner.next();
             char[] charsArray=strings[i].toCharArray();
            for (int j = 0; j <charsArray.length ; j++) {
                chars[i][j]=charsArray[j];
            }
        }
        System.out.println(soulution(N,M,chars));

    }

    private static int soulution(int n, int m, char[][]chars) {
        int num=0;
        int YNum=0;
        int BNum=0;
        char a,b;
        for (int i = 0; i <m ; i++) {
            a='Y';
            b='G';
            char p=chars[0][i];
            for(int k=1+i;k<n;k++){
                char q=chars[k][k+i];
                if((q==a||q==b)&&(p==q)){
                    YNum++;
                }else{
                    num++;
                }
                p=q;
            }
            num++;

        }
        for (int i = m-1; i >=0 ; i--) {
            a='B';
            b='G';
            char p=chars[0][n-1-i];
            for(int k=1+i;k<n;k++){
                char q=chars[k][n-1-(k+i)];
                if((q==a||q==b)&&(p==q)){
                    BNum++;
                }else{
                    num++;
                }
                p=q;
            }
        }
        return num;
    }
}
