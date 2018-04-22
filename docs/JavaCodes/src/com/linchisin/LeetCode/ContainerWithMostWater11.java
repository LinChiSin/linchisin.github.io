package com.linchisin.LeetCode;

import java.util.Scanner;

public class ContainerWithMostWater11 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            int n=scanner.nextInt();
            int []height=new int[n];
            for (int i = 0; i <n ; i++) {
                height[i]=scanner.nextInt();
            }
            System.out.println(maxArea(height));

        }

    }

    /*
    思路：1.暴力搜索，两层遍历，O(n^2);
         2.
         3.注意相邻状态间没有直接关系，不是动态规划；
     */
    private static int maxAreaBF(int[] height) {
        int len=height.length;
        int N=len-1;
        int [][]dp=new int[len][len];
        int max=0,k;
        for (int i=N; i>=0 ; i--) {
            for (int j=1; j<=N-i;j++) {
                k=i+j;
                dp[i][k]=(j)*(height[i]<=height[k]?height[i]:height[k]);
                if(dp[i][k]>=max){
                    max=dp[i][k];
                }
            }
        }
        return max;
    }

    /*
    思路：双指针(左右指针初始为最左和最右)，通过指针的移动缩小选择范围。
         指针的移动取决于左右的最小高度，若左高度小于右高度，左指针向右移动到比当前高度大的值。类似地，右指针左移。
         经验：双指针的指针移动不能想当然
         注意：角标是否越界。
         注意：简代代码的水平
     */

    private static int maxArea(int[] height) {
        int len=height.length;
        int left=0,right=len-1,min,max=0;
        while (left<right){
            min=height[left]<=height[right]?height[left]:height[right];
            max=(right-left)*min>max?(right-left)*min:max;
            while(left<right&&height[left]<=min) left++;
            while(left<right&&height[right]<=min)right--;
        }
        return max;
    }

    /*
    以下为未简化代码前：
    可以看出自己的代码又臭又长
     */
    private static int maxAreaComplex(int[] height) {
        int len=height.length;
        int left=0,right=len-1,min,k;
        boolean flag;
        if(height[left]<=height[right]){
            min=height[left];
            flag=true;
        }else{
            min=height[right];
            flag=false;
        }
        int max=(right-left)*min;
        while (left<right){
            if(flag){
                k=1;
                while(left+k<len&&height[left+k]<=height[left]){
                    k++;
                }
                left=left+k;
            }else{
                k=1;
                while(right-k>=0&&height[right-k]<=height[right]){
                    k++;
                }
                right=right-k;
            }
            if(left>=len||right<0)
                return max;
            if(height[left]<=height[right]){
                min=height[left];
                flag=true;
            }else{
                min=height[right];
                flag=false;
            }
            max=(right-left)*min>max?(right-left)*min:max;
        }
        return max;
    }
}
