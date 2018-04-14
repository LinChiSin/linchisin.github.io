package com.linchisin.LeetCode;

import java.util.*;

public class FourSum18 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            int n=scanner.nextInt();
            int[]nums=new int[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            List<List<Integer>>list=fourSum(nums,target);
            for(List<Integer>aList:list){
                for (int i:aList){
                    System.out.print(" "+i);
                }
                System.out.println();
            }
        }

    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        int len=nums.length;
        if(len<4)
            return Collections.emptyList();
        List<List<Integer>>lists=new ArrayList<>();
        int left,right,sum,k=0;
        HashMap<Nums,Integer> hashMap=new HashMap<>();
        Arrays.sort(nums);
        for (int i=0;i<len-2;i++){
            for (int j = i+1; j <len-1 ; j++) {
                left=j+1;
                right=len-1;
                while(left<right){
                    sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target){
                        int []nums2={nums[i], nums[j], nums[left], nums[right]};
                        Arrays.sort(nums2);
                        Nums nums1=new Nums(nums2[0],nums2[1],nums2[2],nums2[3]);
                        if(!hashMap.containsKey(nums1)){
                            List<Integer>intList = Arrays.asList(nums2[0],nums2[1],nums2[2],nums2[3]);
                            hashMap.put(nums1,k);
                            k++;
                            lists.add(intList);
                        }
                        left++;
                    }else if(sum<target){
                        left++;
                    }else
                        right--;
                }
            }
        }
        return lists;
    }
}
 class Nums{
    int first;
    int second;
    int third;
    int fourth;
    Nums(int first,int second,int third,int fourth){
        this.first=first;
        this.second=second;
        this.third=third;
        this.fourth=fourth;
    }
     @Override
     public int hashCode() {
         return (int)Math.pow(31,3)*first+(int)Math.pow(31,2)*second+(int)Math.pow(31,1)*third+4*fourth;
     }
     @Override
     public boolean equals(Object obj) {
         if(obj==this)
             return true;
         if(obj instanceof Nums){
             return ((Nums) obj).first==first&&((Nums) obj).second==second&&((Nums) obj).third==third&&((Nums) obj).fourth==fourth;
         }
         return false;
     }
 }
