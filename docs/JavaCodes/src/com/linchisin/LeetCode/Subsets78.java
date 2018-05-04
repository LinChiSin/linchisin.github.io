package com.linchisin.LeetCode;

import java.util.*;

public class Subsets78 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int []nums=new int[n];
            for (int i = 0; i < n; i++) {
                nums[i]=scanner.nextInt();
            }
            System.out.println(subsets(nums));
        }
    }

    /*
    描述：求一个数组的所有子集,且不允许出现重复子集，需要去重
    思路：典型的回溯问题
     */
    public static List<List<Integer>> subsets(int[] nums) {
        if(nums.length<=0) return Collections.emptyList();
        List<List<Integer>>result=new ArrayList<>();
        HashMap<List,Integer>hashMap=new HashMap<>();
        List<Integer>temp=new ArrayList<>();
        Arrays.sort(nums); //需要先排序
        dfs(hashMap,result,temp,nums,0);
        return result;
    }

    private static void dfs(HashMap<List,Integer>hashMap,List<List<Integer>> result, List<Integer> temp, int[] nums, int i) {
        if(i==nums.length){  //递归结束机制
            List<Integer>list= new ArrayList<>();
            for (int j = 0; j < temp.size(); j++) {
                if(temp.get(j)!=null) list.add(temp.get(j));
            }
            if(!hashMap.containsKey(list)){
                result.add(list);
                hashMap.put(list,0);
            }
            return;
        }
        //增加去重机制
        for (int k = 0; k < 2; k++) {//外层遍历
            if(k==0){
                temp.add(null);
            }else{
                temp.add(nums[i]);
            }
            dfs(hashMap,result,temp,nums,i+1);  //此处是i+1还是j+1?, 不需要两层遍历，否则会出现重复子集，因此一定是i+1
            temp.remove(temp.size()-1); //回溯机制

        }
    }
}
