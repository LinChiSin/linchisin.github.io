package com.linchisin.LeetCode;

import java.util.Scanner;

public class SqrtX_69 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            System.out.println(mySqrt(scanner.nextInt()));
        }
    }

    /*
    返回一个非负数的整数根
    思路：暴力O(n^(1/2))？
    复杂度：O(n^(1/2))
    特殊输入：原始输入为0
    未考虑情况：root*root溢出, long与int比较时，最好做强制类型转换

     */
    private static int mySqrtNaive(int x) {
        if(x==0)
            return 0;
        int root=1;
        long square=root*root;
        long m=(long)x;
        while(square<=m){
            if(square==m) return root;
            root++;
            square=(long)root*(long)root;
            System.out.println(square);
        }
        return root-1;
    }
    /*
    二分法
     */
    private static int mySqrt(int x) {
        if(x==0)
            return 0;
        return binarySearch(x,1,x);
    }

    /*
    错误：又在右移箭头上出了问题！
         又在加一、减一上出了问题
     */
    private static int binarySearch(int x, int start, int end) {
        if(start>=end-1) return start;
        if((long)start*(long)start==(long)x) return x;
        if((long)end*(long)end==(long)x) return end;
        int mid=start+((end-start)>>1);
        if((long)mid*(long)mid==(long)x){
            return mid;
        }else{
            if((long)mid*(long)mid>(long)x){
                return binarySearch(x,start,mid);
            }else{
                return binarySearch(x,mid,end);
            }
        }
    }
}
