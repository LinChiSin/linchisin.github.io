package com.linchisin.Examination;

import java.util.Scanner;

/*
输入
        第一行包含两个整数N和a,表示矩阵的大小以及填数的方式。1≤N≤100，1≤a≤4

        输出
        输出N行，每行包含N个整数。表示对应的矩阵。
        */
public class Baidu2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int a=scanner.nextInt();
        int [][]matrix=solution(N,a);
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static int[][] solution(int N,int a){
        int [][]matrix=new int[N][N];
        int i,k,m,base,col=0,row=0;
        switch (a){
            case 1:
                i=0;
                while(i<N){
                    for (int j = 0; j <N ; j++) {
                        matrix[i][j]=i*N+j+1;
                    }
                    i=i+2;
                }
                i=1;
                while(i<N){
                    for (int j = 0; j <N ; j++) {
                        matrix[i][j]=2*i*N-j;
                    }
                    i=i+2;
                }
                break;
            case 2:
                i=0;
                while(i<N){
                    for (int j = 0; j <N ; j++) {
                        matrix[j][i]=i*N+j+1;
                    }
                    i=i+2;
                }
                i=1;
                while(i<N){
                    for (int j = 0; j <N ; j++) {
                        matrix[j][i]=2*i*N-j;
                    }
                    i=i+2;
                }
                break;
            case 3:
                m=1;
                base=0;
                k=N;
               while(k>=2){
                   for (int j = 0; j <k ; j++) {
                       matrix[base][j+base]=m++;
                   }
                   for (int j=1; j <k; j++) {
                       matrix[j+base][N-1-base]=m++;
                   }
                   for (int j=N-2-base; j >=base; j--) {
                       matrix[N-1-base][j]=m++;
                   }
                   for (int j=N-2-base; j >=base+1; j--) {
                       matrix[j][base]=m++;
                       row=j;
                       col=base;
                   }
                   k=k-2;
                   base++;
               }
               if(k==1){
                   matrix[row][col+1]=m;
               }
                break;
            case 4:
                m=1;
                base=0;
                k=N;
                while(k>=2){
                    for (int j = 0; j <k ; j++) {
                        matrix[j+base][base]=m++;
                    }
                    for (int j=1; j <k; j++) {
                        matrix[N-1-base][j+base]=m++;
                    }
                    for (int j=N-2-base; j >=base; j--) {
                        matrix[j][N-1-base]=m++;
                    }
                    for (int j=N-2-base; j >=base+1; j--) {
                        matrix[base][j]=m++;
                        row=base;
                        col=j;
                    }
                    k=k-2;
                    base++;
                }
                if(k==1){
                    matrix[row+1][col]=m;
                }
                break;
        }
        return matrix;
    }

}
