package com.linchisin.LeetCode;

import java.util.Scanner;


/*
给出一组正整数，你从第一个数向最后一个数方向跳跃，每次至少跳跃1格，每个数的值表示你从这个位置可以跳跃的最大长度。计算如何以最少的跳跃次数跳到最后一个数。

输入描述:
第一行表示有多少个数n
第二行开始依次是1到n个数，一个数一行
输出描述:
输出一行，表示最少跳跃的次数。
 */

public class JumpGame55_45 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        while (scanner.hasNext()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=scanner.nextInt();
            }
            System.out.println(canJump(nums));
        }
    }

    /*
    思路：动态规划
     */

    public static int jump(int[] nums) {

        int stepCount = 0;
        int lastMaxJump = 0;
        int currentMaxJump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currentMaxJump = Math.max(currentMaxJump, i + nums[i]);
            if (i == lastMaxJump) {
                stepCount++;
                lastMaxJump = currentMaxJump;
            }
        }
        return stepCount;
    }

    /*
    思路:动态规划，dp[i,j]=nums[j]>j-i? 1:1+dp[i+nums[i],j)];   错误思想
     */


    public  static int solution(int []nums){
        int [][]dp=new int[nums.length][nums.length];
        for (int i =nums.length-1;i>=0; i--) {
            dp[i][i]=0;
            for (int j = i+1; j <nums.length ; j++) {
                dp[i][j]=nums[i]>=j-i?1:1+dp[i+nums[i]][j];
            }
        }

        return dp[0][nums.length-1];
    }


      /*
    思路:动态规划：d[i,j]=nums[i]>j-i?1:min(1+dp(k,j)),其中k为所有 i+1<=k<=i+nums[i],复杂度O(n^3)
     */

      public static int jump2(int []nums){
          int [][]dp=new int[nums.length][nums.length];
          for (int i = nums.length-1; i >=0 ; i--) {
              dp[i][i]=0;
              for (int j = i+1; j <nums.length ; j++) {
                  if(nums[i]>=j-i){
                      dp[i][j]=1;
                  }else{
                      int k=i+1,min=1+dp[k][j];
                      while(k<=i+nums[i]&&k<=j){
                          min=1+dp[k][j]<min?1+dp[k][j]:min;
                          k++;
                      }
                      dp[i][j]=min;
                  }
              }
          }
          return dp[0][nums.length-1];
      }

      /*
      思路：贪心算法，遍历数组，每一步确定能到达的最远范围，当遍历指针i等于上次的最远范围时，更新最远范围，并计步
       */

      public static int jumpGreedy(int []nums){
          int step=0,curEnd=0,curFarest=0;
          for (int i = 0; i <nums.length-1; i++) {
              curFarest=i+nums[i]>curFarest?i+nums[i]:curFarest;
              if(i==curEnd){
                  step++;
                  curEnd=curFarest;
              }
          }
          return step;
      }

      /*
       题目描述：判断是否能到达最右
       思路：贪心，判断当前位置能到达的最远距离，当最远距离大于等于(n-1),直接返回true
       特殊情况：nums=[0],nums为空
       */
      public static boolean canJump(int []nums){
          if(nums.length==0) return false;
          int largest=nums[0];
          int i=1;
          while (largest>=i&&i<nums.length-1){  //此处判断是否还能继续前进
              largest=largest<i+nums[i]?i+nums[i]:largest;
              if(largest>=nums.length-1) return true;
              i++;
          }
          return largest>=nums.length-1;   //此处不能直接返回false，而是应当再判断
      }


    }
