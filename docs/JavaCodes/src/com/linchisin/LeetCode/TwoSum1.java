package com.linchisin.LeetCode;

import java.util.HashMap;
import java.util.Vector;

public class TwoSum1 {

    public static Vector<Integer> indiceNums=new Vector<>();

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i <nums.length ; i++) {
            indiceNums.add(i);
        }
        int [] indice=new int[2];
        quickSort2(nums,0,nums.length-2);
        int left=0;
        int right=nums.length-1;
        while (left<nums.length&&right>0){
            if(nums[left]+nums[right]==target){
                int leftIndice=indiceNums.get(left);
                int rightIndice=indiceNums.get(right);
                indice[0]=leftIndice<rightIndice?leftIndice:rightIndice;
                indice[1]=leftIndice>rightIndice?leftIndice:rightIndice;
                break;
            }else if(nums[left]+nums[right]>target)
                right--;
            else
                left++;
        }

        return indice;
    }



    //快排并返回调换后的数组
    public static void quickSort2(int [] array, int left,int right){
        int pivot=right+1;
        if(right<left||left<0||left>=array.length||right<0||right>=array.length){
            return;
        }
        int i=left;
        int j=right;
        while(i<=j) {
            while (array[i] <=array[pivot]&&i<pivot) {
                i++;
            }
            while (array[j] >=array[pivot] && j >i) {
                j--;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                int iIndice=indiceNums.get(i);
                int jIndice=indiceNums.get(j);
                indiceNums.set(i,jIndice);
                indiceNums.set(j,iIndice);
                i++;
                j--;
            }else if(i>=j){
                int temp=array[pivot];
                array[pivot]=array[i];
                array[i]=temp;
                int iIndice=indiceNums.get(i);
                int pivotIndice=indiceNums.get(pivot);
                indiceNums.set(pivot,iIndice);
                indiceNums.set(i,pivotIndice);
            }
        }

        quickSort2(array,left,i-2);
        quickSort2(array,i+1,right);

    }

    public static int queryIndiceFromNums(int num,int[]array,int left, int right){
        int indice=left+(right-left)/2;
        if(left==right){
            if(num==array[indice]){
                return indice;
            }else if(num>array[indice]){
                return indice+1;
            }else{
                return indice-1;
            }
        }
        if (num==array[indice])
            return indice;
        else if ( num>array[indice])
            return queryIndiceFromNums(num,array,indice+1,right);
        else
            return queryIndiceFromNums(num,array,0,indice-1);
    }

    public static void main(String[] args) {

        int[]nums=new int[12600];
        for (int i = 0; i <nums.length-1; i++) {
            nums[i]=i*2;
        }
        nums[nums.length-1]=16021;

        int target=41217;
        int []newNums=twoSum(nums,target);
        for (int i = 0; i <newNums.length; i++) {
            System.out.print(newNums[i]+"\t");
        }


    }

    public int[] twoSumHash(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }
}
