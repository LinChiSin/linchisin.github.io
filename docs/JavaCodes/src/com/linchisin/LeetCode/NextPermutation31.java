package com.linchisin.LeetCode;

import java.util.Scanner;

public class NextPermutation31 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=scanner.nextInt();
            }
            nextPermutation(nums);
            for(int i:nums){
                System.out.print(i+" ");
            }
        }
    }

    /*
    思路：
    已经完全按降序排列的数组不存在下一个排列，或者说其下一个排列为完全按照升序排列的数组
    当前排列的下一个排列总是按照字典序
    举个例子：6,3,5,4,2,1，要找这个数的下一个字典序，下列步骤完成：
    1.从右往左找到第一个比左边数小的小的数，这个例子中为3。若没找到，则直接返回该数组的反转。原理：找出逆序段
    2.从该数（3）的右边（5）从左往右找到第一个比它大的数，这个例子中为4。原理：逆序段进位。
    3.交换3,4，此时后续为全降序排列
    4.将4以后的数反转，即改为全升序排列
    复杂度：TC:O（n）,SC:O(1)
    特殊输入：原始数组已经全部降序排列？
             原始数组中存在相同数据？
    */

    private static void nextPermutation(int[] nums) {
        int i=nums.length-2;
        int j,k,index;
        while(i>=0){              //找出不再呈降序排列的第一个数
            if(nums[i]<nums[i+1])  //注意此处：不能是小于等于，只能是小于。（反例：2,2，2,2）
                break;
            i--;
        }
        if(i<0){
            reverse(nums,0,nums.length-1);  //原始数组已经完全降序排列，直接反转全部数组
        }else{
            j=i;            //从找出比nums[i]大的第一个数
            k=nums[i+1];
            index=i+1;
            while(j<=nums.length-2){
                if(nums[j+1]>nums[i]){
                    if(k>=nums[j+1]){  //注意此处：不能是大于，只能是大于等于，否则不能构成k后边为降序排列
                        k=nums[j+1];
                        index=j+1;
                   }
                }
                j++;
            }
            swap(nums,i,index);
            reverse(nums,i+1,nums.length-1);
        }
    }

    /*
    交换数组中第i位和第j位
     */
    private static void swap(int[] nums, int i, int j) {
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }
    /*
    将数组中第i位到第j位反转
     */
    private static void reverse(int[] nums, int i, int j) {
        for (int k = 0; k <=(j-i)>>1 ; k++) {
            swap(nums,i+k,j-k);
        }
    }
}
