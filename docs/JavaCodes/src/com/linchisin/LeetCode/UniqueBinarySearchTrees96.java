package com.linchisin.LeetCode;


import java.util.Scanner;

public class UniqueBinarySearchTrees96 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            System.out.println(numTrees(scanner.nextInt()));
        }
    }

    /*
    动态规划：
     */

    private static int numTrees(int n) {
        int []dp=new int[n];
        dp[0]=1;

        return dp[n-1];
    }
}
