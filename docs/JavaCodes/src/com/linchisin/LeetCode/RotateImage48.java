package com.linchisin.LeetCode;

import java.util.Scanner;

public class RotateImage48 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            rotate(matrix);
            for (int []nums:matrix){
                for (int i: nums){
                    System.out.print(i+" ");
                }
                System.out.println();
            }
        }
    }

    /*
     将一个矩阵顺时针旋转90度
     */
    /*
    思路：对矩阵进行分层，外层嵌套内层，每一层进行旋转遍历，需要设置临时变量交换元素
    复杂度：O(n^2)
    特殊输入：矩阵为空，矩阵为1？
     */
    private static void rotate(int[][] matrix) {
        if(matrix==null) return;
        //计算层数
        int n=matrix[0].length;
        int level=(int)Math.ceil((double)n/2);
        //按层遍历
        for (int i = 0; i <level; i++) {
            //按行、列中元素遍历
            for (int j = 0; j <n-2*i-1; j++) {
                int temp = matrix[i][i+j];
                matrix[i][i+j] = matrix[n - 1 - i-j][i];
                matrix[n - 1 - i-j][i] = matrix[n - 1 - i][n - 1 - i-j];
                matrix[n - 1 - i][n - 1 - i-j] = matrix[i+j][n - 1 - i];
                matrix[i + j][n - 1 - i] = temp;
            }
        }
    }
}
