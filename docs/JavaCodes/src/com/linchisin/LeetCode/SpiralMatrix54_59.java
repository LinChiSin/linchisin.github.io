package com.linchisin.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SpiralMatrix54_59 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
//            int m=scanner.nextInt();
//            int n=scanner.nextInt();
//            int[][]matrix=new int[m][n];
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j <n; j++) {
//                    matrix[i][j]=scanner.nextInt();
//                }
//            }
//            System.out.println(spiralOrder(matrix));
            int n=scanner.nextInt();
            int [][]matrix=generateMatrix(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j]+ " ");
                }
                System.out.println();
            }
        }
    }
    /*
     思路：仍然是找规律
     */
    private static int[][] generateMatrix(int n) {
        int[][]matrix=new int[n][n];
        //层数
        int level=(int)Math.ceil((double)n/2);
        int i,j,k=1;
        for (i = 0; i < level; i++) {
            //上行
            for (j = 0; j < n - 2 * i; j++) {
                matrix[i][i+j]=k++;
            }
            //右列
            for (j = 0; j <n-2*i-1; j++) {
                matrix[i+1+j][n-1-i]=k++;
            }
            //下行
            for (j = 0; j < n-2*i-1; j++) {
                matrix[n-1-i][n-2-i-j]=k++;
            }
            //左列
            for (j = 0; j < n - 2 * i - 2; j++) {
                matrix[n-2-i-j][i]=k++;
            }
        }
        return matrix;
    }

    /*
    思路：仍然是外层加内层遍历
    复杂度：O(mn)
    特殊输入：原始数组为空，原始数组为1个数，如果不存在四行（列）怎么办？
    本质：找规律
    错误:矩阵的层数又想当然了


     */
    private static List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0) return Collections.emptyList();
        List<Integer>list=new ArrayList<>();
        int m=matrix.length;
        int n=matrix[0].length;
        int level=(int)Math.ceil((double)Math.min(m,n)/2);  //矩阵的层数，注意此处不是选中某一层
        for (int i = 0; i < level; i++) {
                //上行
            for (int j = 0; j < n - 2 * i; j++) {
                list.add(matrix[i][i+j]);
            }
                //右列
            for (int j = 0; j <m-1-2*i; j++) {
                list.add(matrix[i+1+j][n-1-i]);
            }
                //下行
            for (int j = 0; m-1-i>i&&j <n-2*i-1; j++) {
                list.add(matrix[m-1-i][n-2-i-j]);
            }
                //左列
            for (int j = 0; i<n-1-i&&j <m-2-2*i; j++) {
                list.add(matrix[m-2-i-j][i]);
            }
        }
        return list;
    }
}
