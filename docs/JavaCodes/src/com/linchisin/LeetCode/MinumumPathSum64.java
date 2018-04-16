package com.linchisin.LeetCode;

import java.util.Scanner;

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */


/*
Example 1:
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
 */

public class MinumumPathSum64 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int m=scanner.nextInt();
            int n=scanner.nextInt();
            int[][]grid=new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }
            System.out.println(minPathSum(grid));

        }
    }
   /*
   思路：动态规划，d[i,j]表示从左上顶点到达坐标(i,j)的最短路径和，则：
        d[0,0]=grid[0,0];
        d[i,0]=d[i-1,0]+grid[i,0]; d[0,j]=d[0,j-1]+grid[0,j];
        d[i,j]=min([d[i-1,j],d[i,j-1])+grid[i,j];
        最终返回的为d[m-1,n-1];
   复杂度：TC O(n^2), SC O(n^2)
   特殊输入：m==0? n==0?
    */
    private static int minPathSum(int[][] grid) {
        int m=grid.length;
        if(m==0)
            return 0;
        int n=grid[0].length;
        if(n==0)
            return 0;
        int [][]dp=new int[m][n];
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n; j++) {
                if(i==0||j==0){
                    if(i==0&&j==0)
                        dp[i][j]=grid[i][j];
                    else if(i==0)
                        dp[0][j]=dp[0][j-1]+grid[0][j];
                    else
                        dp[i][0]=dp[i-1][0]+grid[i][0];
                }else{
                    dp[i][j]=dp[i-1][j]<=dp[i][j-1]?dp[i-1][j]+grid[i][j]:dp[i][j-1]+grid[i][j];
                }
            }
        }

        return dp[m-1][n-1];
    }
}
