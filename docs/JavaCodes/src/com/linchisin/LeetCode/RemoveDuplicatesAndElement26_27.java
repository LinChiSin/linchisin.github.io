package com.linchisin.LeetCode;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class RemoveDuplicatesAndElement26_27 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=scanner.nextInt();
            }
            int val=scanner.nextInt();
            System.out.println(removeElement2(nums,val));
            for (int i = 0; i <nums.length ; i++) {
                System.out.print(nums[i]+" ");
            }
        }
    }


    /*
    Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.

   Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     */


    /*
    思路： (1)整体思路： a.哈希表记录（会开辟新的内存空间);
                       b. 排序，比较前后数字是否相同（利用快排实现）
          (2)特殊输入情况： 数组为空，数组已经全部有序，数组部分有序

    复杂度分析：
              a. TC: O(n),SC: O(n)
              b.Tc: 平均O(nlogn),最差O(n^2)，SC：O(1)

    测试样例：
     */

    private static int removeDuplicates(int[] nums) {
        if (nums.length<=1)
            return 0;
        int len= nums.length;
        int newLen=len;
        int front=nums[0];
        for (int i = 1; i<len ; i++) {
            if(nums[i]==front){
                newLen--;
            }
            front=nums[i];
        }
        return newLen;
    }

    private static void quickSort(int[] nums, int start,int end) {
        if(start<end){
            int p=partition(nums,start,end);
            quickSort(nums,start,p-1);
            quickSort(nums,p+1,end);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot=nums[end];
        int i=start-1;
        for (int j = start; j <end ; j++) {
            if(nums[j]<=pivot){
                i=i+1;
                swap(nums,i,j);
            }
        }
        swap(nums,i+1,end);
        return i+1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp=nums[j];
        nums[j]=nums[i];
        nums[i]=temp;
    }

    private static void quickSortUnrecursion(int[] a, int start, int end) {
        LinkedList<Integer> stack = new LinkedList<Integer>(); // 用栈模拟
        if (start < end) {
            stack.push(end);
            stack.push(start);
            while (!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int index = partition(a, l, r);
                if (l < index - 1) {
                    stack.push(index - 1);
                    stack.push(l);
                }
                if (r > index + 1) {
                    stack.push(r);
                    stack.push(index + 1);
                }
            }
        }
    }

    /*
     删除数组中所有指定元素，并将剩余元素移至前边，返回去重后的数组长度
     */

    /*
     思路： 先排序（冒泡、选择或者插入），使得相同元素近邻，遍历然后找到指定元素，统计出现个数k，返回新的长度为2，并将重复元素移至倒数k个位置
     复杂度：TC O(n^2), SC O(1)
     思路：  从左往右遍历，设置统计元素个数变量i=0,若找到指定元素，i+=1，并执行swap(i,n-i)，直到遍历结束，最后返回数组长度减i
            错误！没有考虑交换的元素也可能正好与它相同

            解决方案：若此时交换的元素与之相同，则待交换的位置往前挪一，并重复判断，直到不再相同或者已经与之相等
            可以更改为双指针描述，i指向左侧遍历元素，j指向与之对应的交换位置。
            初始值情况：i==0,j==length-1。若nums[i]==val,则判断此时num[j]是否为指定元素，若不是，swap(i，j)，否则j--，并重复判断，直到num[j]==num[i]或者j==i
            另设统计元素个数变量，找到指定元素则使num++（i,j处均有可能）。

            错误！没有考虑各种特殊情况 i=j时怎么办？

     复杂度：TC O(n), SC O(1)
     特殊输入：num,length==0/val不在数组中/数组中全是val
     */

    private static int removeElement(int[] nums, int val) {
        int num=0,len=nums.length;
        if(len<=0)
            return 0;
        int i=0,j=len-1;
        if(i==j){
            if(nums[i]==val) {
                return i;
            }else{
                return len;
            }
        }
        int k=0,m=nums[j];
        for (i=0; i <=j ; i++) {
            if(i==j&&nums[i]==val){
                num++;
                break;
            }
           if(nums[i]==val){
               num++;
               if(nums[i]==nums[j]){
                   while(nums[i]==nums[j]&&i<j){
                       k++;
                       j--;
                       num++;
                   }
                   swap(nums,i,j);
               }else{
                   swap(nums,i,j);
                   j--;
               }
           }
        }
        if(k==0&&m==val){
            num++;
        }
        return len-num;
    }

    public static int removeElement2(int[] nums, int val) {
        int tail = 0;
        for (int i = 0, len = nums.length; i < len; ++i) {
            if (nums[i] != val) {
                nums[tail++] = nums[i];
            }
        }
        return tail;
    }






}
