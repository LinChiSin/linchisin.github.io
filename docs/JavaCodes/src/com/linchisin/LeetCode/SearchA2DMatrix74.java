package com.linchisin.LeetCode;

import java.util.Scanner;

public class SearchA2DMatrix74 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int m=scanner.nextInt();
            int n=scanner.nextInt();
            int [][]matrix=new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j]=scanner.nextInt();
                }
            }
            int target=scanner.nextInt();
            System.out.println(searchMatrix(matrix,target));
        }
    }
     /*
     描述：给定一个行有序二维数组，判断是否能在二维数组中找到一个目标值
     思路：二分，先对第一列二分，找到最右可能在的行号（终止条件是相邻只有一行了）；
          然后对所在的行二分，判断是否在该行内
          注意考虑完备性，小于最小值，大于最大值
     复杂度： TC,O(logm+logn) SC O(1)
     特殊输入：

      */
    private static boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        if(m==0) return false;
        int n=matrix[0].length;
        if(n==0) return false;
        //对一列二分
        int row= binarySearchInCol(matrix,0,m-1,target);
        System.out.println(row);
        if(row==-2) return false;
        if(row==-1) return true;
        //对第col行二分
        return binarySearchInRow(matrix,row,0,n-1,target);
    }


    //在一行内二分
    private static boolean binarySearchInRow(int[][] matrix, int row, int start, int end, int target) {
//        if(matrix[row][start]>target) return false;
        if(start>=end) return false;
        if(matrix[row][start]==target) return true;
        if(matrix[row][end]==target) return true;
        int mid=start+(end-start)>>1;
        if(matrix[row][mid]==target) return true;
        if(matrix[row][mid]<target) return binarySearchInRow(matrix,row,mid+1,end,target);
        else return binarySearchInRow(matrix,row,start,mid-1,target);
    }

    //对一列二分
    private static int binarySearchInCol(int[][] matrix, int start, int end, int target) {
        if(start>=end){
           if(start==0&&target<matrix[0][0]) return -2;
           else return start-1;
        }
        if(matrix[end][0]==target) return -1;
        if(matrix[end][0]<target) return end;
        if(end-start==1&&target<matrix[end][0]&&target>=matrix[start][0]) return start;
        int mid=start+(end-start)>>1;
        if(matrix[mid][0]==target) return -1;
        if(matrix[mid][0]<target){
            return binarySearchInCol(matrix,mid+1,end,target);
        }else{
            return binarySearchInCol(matrix,start,mid-1,target);
        }
    }
}
