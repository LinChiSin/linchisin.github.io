package com.linchisin.LeetCode;

import java.util.Scanner;

public class UniquePathsAndObstacle62_63 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int m=scanner.nextInt();
            int n=scanner.nextInt();
            int [][]obstacleGrid=new int[m][n];
            for (int i = 0; i <m ; i++) {
                for (int j = 0; j <n ; j++) {
                    obstacleGrid[i][j]=scanner.nextInt();
                }
            }
            System.out.println(uniquePathsWithObstacles(obstacleGrid));
        }
    }



    /*
    思路：动态规划,设d[i][j]表示从[0,0]到达[i,j]的可能路线，由于机器人只能向右或向下移动，则当前时刻的位置只能由当前位置的上方和左方移动而来，故：
         1，d[0][j]=1,d[i][0]=1;
         2, d[i][j]=d[i-1][j]+d[i][j-1];
         最后返回d[m-1][n-1];
    复杂度：TC O(m*n), SC O(m*n)
    特殊输入：m=0,n=0?
     */

    private static int uniquePaths(int m, int n) {
        if(m<=0||n<=0)
            return 1;
        int [][]dp=new int[m][n];
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n; j++) {
                dp[i][j]=(i==0||j==0)?1:dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

   /*
    Problem Description:
    Follow up for "Unique Paths":

    Now consider if some obstacles are added to the grids. How many unique paths would there be?

    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    For example,

    There is one obstacle in the middle of a 3x3 grid as illustrated below.

    Follow up for "Unique Paths":

    Now consider if some obstacles are added to the grids. How many unique paths would there be?

    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    For example,

    There is one obstacle in the middle of a 3x3 grid as illustrated below.
    [
        [0,0,0],
        [0,1,0],
        [0,0,0]
        ]
    The total number of unique paths is 2.
        [0,0,0],
        [0,1,0],
        [0,0,0]
        ]
    The total number of unique paths is 2.

    */

    /*
     思路：延续上一问题的思路，d[i][j]表示[0,0]到[i,j]可能的路线数目，同样由于方向的限制，只能从由目标位置的左方和上方到达目标点，此时还需考虑栅格中是否包含障碍物，因此：
          1，dp[0][j]=a[o][j]==1?0:dp[0][j-1],dp[i][0]=a[i][0]==1?0:dp[i-1][0];
          2, dp[i][j]=a[i][j]==1?0:dp[i-1][j]+dp[i][j-1];
     复杂度： TC:O(n^2), SC:O(n^2)
     特殊输入：m=0,n=0?
     */
    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        if(m==0)
            return 1;
        int n=obstacleGrid[0].length;
        if(n==0)
            return 1;
        int [][]dp=new int[m][n];
        for (int i=0; i<m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(i==0||j==0){
                    if(i==0&&j==0)
                        dp[i][j]=obstacleGrid[i][j]==1?0:1;
                    else{
                        if(i==0)
                            dp[0][j]=obstacleGrid[i][j]==1?0:dp[0][j-1];
                        else
                            dp[i][0]=obstacleGrid[i][0]==1?0:dp[i-1][0];
                    }
                }else
                    dp[i][j]=obstacleGrid[i][j]==1?0:dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
