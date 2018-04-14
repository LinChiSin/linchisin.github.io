package com.linchisin.LeetCode;

import java.util.*;

public class ThreeSum15 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            int n=scanner.nextInt();
            int[]nums=new int[n];
            for (int i = 0; i <n ; i++) {
                nums[i]=scanner.nextInt();
            }
            List<List<Integer>> triplets= threeSum(nums);
            for (List<Integer> triplet : triplets) {
                for (Integer aTriplet : triplet) {
                    System.out.print(" " + aTriplet);
                }
                System.out.println();
            }
        }
    }

    /*
    思路：暴力搜索，a,b,c三层搜索，O(n^3);
         按照LeetCode Problem1 TwoSum 的思路，Hash(a,-a)和hash(b,-a-c)?
         将数组选取所有两种元素相加，得到TwoSum的数组,大小为C(n,2)，对该数组建立Hash(a,-a)。
     */

    /*
    哈希包含的复杂度为O(n)
     */

    private static List<List<Integer>> threeSumBFHash(int[] nums) {
        List<List<Integer>>lists=new ArrayList<>();
        int len=nums.length;
        if(len<=2)
            return lists;
        int twoLen=len*(len-1)/2;
        int []twoSums=new int[twoLen];
        int k=0;
        HashMap<Integer,Index>hashMapIndex=new HashMap<>();
        for (int i = 0; i <len ; i++) {
            for (int j = i+1; j <len ; j++) {
                twoSums[k]=nums[i]+nums[j];
                hashMapIndex.put(k,new Index(i,j));
                k++;
            }
        }
        HashMap<Integer,Integer> hashMap1=new HashMap<>(),hashMap2=new HashMap<>();
        for (int i = 0; i <len; i++) {
            hashMap1.put(i,nums[i]);
        }
        for (int i = 0; i <twoLen; i++) {
            hashMap2.put(i,twoSums[i]);
        }
        int negNum,num2,num3;
        HashMap<Indexs,Integer>hashMapIndexs=new HashMap<>();
        k=0;
        for (int i = 0; i <len; i++) {
            negNum=0-hashMap1.get(i);
            if(hashMap2.containsValue(negNum)){
                for(Integer key:hashMap2.keySet()){
                    if(hashMap2.get(key)==(negNum)){
                        num2=hashMapIndex.get(key).getLeft();
                        num3=hashMapIndex.get(key).getRight();
                        if(i!=num2&&i!=num3){
                            List<Integer>listNum=new ArrayList<>();
                            listNum.add(nums[i]);
                            listNum.add(nums[num2]);
                            listNum.add(nums[num3]);
                            Collections.sort(listNum);
                            Indexs indexs=new Indexs(listNum);
                            if(!hashMapIndexs.containsKey(indexs)){
                                lists.add(listNum);
                                hashMapIndexs.put(indexs,k);
                                k++;
                            }
                        }
                    }
                }
            }
        }
        return lists;
    }


    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>lists=new ArrayList<>();
        int len=nums.length;
        if(len<=2)
            return lists;
        int i,left,right,k=0;
       Arrays.sort(nums);
       HashMap<Indexs,Integer> hashMap=new HashMap<>();
        for (i = 0;  i<len-1 ; i++) {
            left=i+1;right=len-1;
            while(left<right){
                if(nums[i]+nums[left]+nums[right]==0&&left<right) {
                    List<Integer>listNum=new ArrayList<>();
                    listNum.add(nums[i]);
                    listNum.add(nums[left]);
                    listNum.add(nums[right]);
                    Collections.sort(listNum);
                    Indexs indexs=new Indexs(listNum);
                    if(!hashMap.containsKey(indexs)){
                        lists.add(listNum);
                        hashMap.put(indexs,k);
                        k++;
                    }
                    left++;
                }else if(nums[i]+nums[left]+nums[right]>0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return lists;
    }
}

class Indexs{
    int left;
    int medium;
    int right;
    Indexs(List<Integer> list){
        this.left=list.get(0);
        this.medium=list.get(1);
        this.right=list.get(2);
    }

    @Override
    public int hashCode() {
        System.out.println("哈希值为："+super.hashCode());
        return super.hashCode();
    }

    //    @Override
//    public int hashCode() {
//        System.out.println("进入哈希函数: l="+left+" m="+medium+" r="+right);
//        int x=left+2*medium+3*right;
//        System.out.println("哈希值为："+String.valueOf(left+2*medium+3*right));
//        System.out.println("哈希值X为："+x);
//        return left+2*medium+3*right;
//
//    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("进入equals函数: l="+left+" m="+medium+" r="+right);
        boolean s=obj == this || obj instanceof Indexs && ((Indexs) obj).left == this.left && ((Indexs) obj).medium == this.medium && ((Indexs) obj).right ==this.right;
        System.out.println(String.valueOf(s));
        return obj == this || obj instanceof Indexs && ((Indexs) obj).left == this.left && ((Indexs) obj).medium == this.medium && ((Indexs) obj).right == this.right;
    }
}


   class Index{
    int left;
    int right;
    Index(int left,int right){
        this.left=left;
        this.right=right;
    }
    int getLeft(){
        return left;
       }
    int getRight(){
        return right;
    }
}


