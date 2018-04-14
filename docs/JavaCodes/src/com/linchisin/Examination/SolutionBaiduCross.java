package com.linchisin.Examination;

import java.util.Scanner;

public class SolutionBaiduCross {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int k=scanner.nextInt();
        int []n=new int[k];
        double []sum=new double[k];
        for (int i = 0; i <k; i++) {
            n[i]=scanner.nextInt();

        }
        for (int i = 0; i <k ; i++) {
            int m=i+1;
            System.out.println("Case #"+m+":");
            solution(n[i]);
        }
    }
    private static void solution(int n){
        int [][]matrix={{0,1,0},{1,1,1},{0,1,0}};
        int i=2;
        if(n==1){
            System.out.println("o");
            return;
        }

        while (i<n){
            i++;
           matrix=matrixCopy(matrix);
        }
        printMatrix(matrix);

    }
    private static int[][] matrixCopy(int[][] matrix){
        int n=matrix.length;
        int m=3*n;
        int [][]newMatrix=new int[m][m];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                newMatrix[i][j+n]=matrix[i][j];
                newMatrix[i+n][j]=matrix[i][j];
                newMatrix[i+n][j+n]=matrix[i][j];
                newMatrix[i+n][j+2*n]=matrix[i][j];
                newMatrix[i+2*n][j+n]=matrix[i][j];
            }
        }
        return newMatrix;

    }

    private static void printMatrix(int[][] matrix){
        for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                if(matrix[i][j]==1)
                    System.out.print("o");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

}
