package com.linchisin.LeetCode;

import java.util.Scanner;

public class ClimbingStairs70 {
    volatile int num=0;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            System.out.println(new ClimbingStairs70().climbStairs(scanner.nextInt()));
        }

    }
    /*
    描述：每次爬楼梯有走一步、走两步两种方式，给定n阶楼梯，求到达最上层的爬楼梯共有多少种不同的方式
    思路：回溯？
        确保回溯的几个机制：递归（递归开始条件、退出条件、递归机制）
                         回溯方式？(遍历、回溯)
                         需要剪枝吗？
    复杂度：实际上是暴力算法，遍历（2^n）次方 ——————> 时间复杂度过高
     */
    private int climbStairsBacktracking(int n) {
        int[]steps={1,2};
        backtracking(n,steps);
        return num;
    }

    private void backtracking(int n, int[] steps) {
        if(n==0) num++;
        for (int i = 0; i < steps.length && n > 0; i++) { //递归终止条件
            backtracking(n-steps[i],steps);
        }
    }

    /*
    思路：动态规划， 令d[i]表示从0跳到第i个台阶的可能性数目,\
         由于每次只能走一步或者两部，故当前状态只与前一个状态和前两个状态有关,且必须与这两者有关
         d[0]=0,d[1]=1,d[2]=d[0]+d[1]
         d[i]=d[i-1]+d[i-2];
    复杂度：O(n)
    特殊输入：n>=1;
     */
    public int climbStairs(int n){
        int []dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <=n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
