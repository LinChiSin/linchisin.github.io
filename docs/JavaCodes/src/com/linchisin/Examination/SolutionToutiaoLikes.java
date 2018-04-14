package com.linchisin.Examination;

import java.util.Scanner;

public class SolutionToutiaoLikes {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int []likes=new int[n];
        for (int i = 0; i <n; i++) {
            likes[i]=scanner.nextInt();
        }
        int q=scanner.nextInt();
        int []l=new int[q];
        int []r=new int[q];
        int []k=new int[q];
        int []num=new int [q];
        for (int i = 0; i <q; i++) {
            l[i]=scanner.nextInt();
            r[i]=scanner.nextInt();
            k[i]=scanner.nextInt();
            num[i]=solution(l[i],r[i],k[i],likes);
        }
        for(int i:num)
            System.out.println(i);

    }
    private static int solution(int l,int r,int k,int [] likes){
        int num=0;
        for (int i = l-1;i <r ; i++) {
            if (likes[i]==k)
                num++;
        }
        return num;
    }
    }

