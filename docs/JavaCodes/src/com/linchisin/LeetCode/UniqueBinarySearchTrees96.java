package com.linchisin.LeetCode;


import java.util.ArrayList;
import java.util.List;
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

    private static int numTreesNaive(int n) {
        List<List<Integer>> list=new ArrayList<>();
        List<Integer>curList=new ArrayList<>();
        curList.add(1);
        list.add(curList);
        for (int i = 1; i <n ; i++) {
            List<Integer>curList2=new ArrayList<>();
            for (int j:list.get(i-1)) {
                for (int k = 0; k <=j ; k++) {
                    curList2.add(k+1);
                }
            }
            list.add(curList2);
        }
        return list.get(n-1).size();
    }

    private static int numTrees(int n) {
        if (n <= 0)
            return 0;
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                res[i] += res[j - 1] * res[i - j];
            }
        }
        return res[n];
    }
}
