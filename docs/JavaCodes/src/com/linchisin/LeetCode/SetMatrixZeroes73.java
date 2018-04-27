package com.linchisin.LeetCode;

import java.util.Scanner;

public class SetMatrixZeroes73 {

    static volatile int [][]matrix;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int m=scanner.nextInt();
            int n=scanner.nextInt();
            matrix=new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j]=scanner.nextInt();
                }
            }
            setZeros(matrix);
            for(int[] row:matrix){
                for (int i:row){
                    System.out.print(i+" ");
                }
                System.out.println();
            }
        }
    }
    /*
    描述：将0元素所在的行列的全部元素置0,要求原址操作
    思路：显然可以用遍历实现，复杂度O(mn)，不建议
         能否找到O(m+n)的实现方式
         关键在于怎么找到零元素的位置


     */
    private static void setZeros(int[][] matrix) {

        int m=matrix.length;
        int n=matrix[0].length;
        boolean firstRow=false,firstCol=false;
        for (int i = 0; i < n; i++) {
            if(matrix[0][i]==0){
                firstRow=true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if(matrix[i][0]==0){
                firstCol=true;
                break;
            }
        }
        for (int i = 1; i <m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][j]==0){
                        matrix[0][j]=0;
                        matrix[i][0]=0;
                }
            }
        }

        for (int i = 1; i <n; i++) {
            if(matrix[0][i]==0){
                for (int j = 1; j <m ; j++) {
                    matrix[j][i]=0;
                }
            }
        }
        for (int i = 1; i <m; i++) {
            if(matrix[i][0]==0){
                for (int j = 1; j <n; j++) {
                    matrix[i][j]=0;
                }
            }
        }
        if(firstRow){
            for (int i = 0; i < n; i++) {
                matrix[0][i]=0;
            }
        }
        if(firstCol){
            for (int i = 0; i < m; i++) {
                matrix[i][0]=0;
            }
        }
    }

}
