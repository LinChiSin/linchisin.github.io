package com.linchisin.LeetCode;

import java.util.*;

public class GenerateParentheses22 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            System.out.println(generateParenthesisHelper(n));
//            List<Integer>list=new ArrayList<>();
//            for (int i = 0; i <n ; i++) {
//                list.add(scanner.nextInt());
//            }
//            System.out.println(checkAndConvertParenthesis(list));
        }
    }



    /*
    思路：n个括号对的顺序对应数字1,2,...,n按照序号出入栈（即数字小的必须先入栈），则问题可以转化为：判断一个数字排列是否满足该条性质
         穷举n!的排列，逐一判断该排列是否满足出入栈顺序，满足的则输出对应的括号对顺序。

         穷举n!排列的思路：
         第一个数字可以是集合{1,2,3,...,n}中的任意一个，则该操作对应遍历i，找到i后，从集合中删除该元素，重复该操作，直到集合为空
     */


    /*
    暴力法
     */
    private static List<String> generateParenthesisSlow(int n) {

        List<String>list=new ArrayList<>();
        List<List<Integer>> lists=getPermutation(n);
        for(List<Integer> list1:lists){
            if(isValidOrder(list1))
                list.add(convertToParenthesis(list1));
        }
        return list;

    }
    private static List<String> generateParenthesis(int n){
        List<String>list=new ArrayList<>();
        List<List<Integer>> lists=getPermutation(n);
        String parenthesis;
        for(List<Integer> list1:lists){
            parenthesis=checkAndConvertParenthesis(list1);
            if(parenthesis!=null){
                list.add(parenthesis);
            }
        }
        return list;
    }

    /*
    判断数字是否符合出入栈顺序，若符合直接转换为括号对字符，否则返回空。
    由于判断数组是否符合进出栈顺序和根据合理的进出栈顺序生成括号对有相同操作，因此可以同时进行，减少重复操作
     */

    private static String checkAndConvertParenthesis(List<Integer>list){
        StringBuilder stringBuilder=new StringBuilder();
        Stack<Integer>stack=new Stack<>();
        int stackNum=0,m,k;
        for(int i:list){
            if(i>stackNum){
                k=stackNum+1;
                while (k<=i){
                    if(i>stackNum){
                        stack.push(k);
                        stringBuilder.append("(");
                        stackNum=k;
                        k++;
                    }
                }
            }
            m=stack.pop();
            if(m!=i){
                return null;
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }


    public static List<String> generateParenthesisHelper(int n) {
        List<String> list = new ArrayList<>();hexo
        helper(list, "", 0, n);
        return list;
    }

    //递归实现
    private static void helper(List<String> list, String str, int rightNeed, int leftRest) {
        if (rightNeed == 0 && leftRest == 0) {
            list.add(str);
            return;
        }
        if (rightNeed > 0) helper(list, str + ")", rightNeed - 1, leftRest);
        if (leftRest > 0) helper(list, str + "(", rightNeed + 1, leftRest - 1);
    }



    /*
    思路：迭代思想

    找规律：
    f(0): “”
    f(1): “(“f(0)”)”
    f(2): "(“f(0)”)"f(1), “(“f(1)”)”
    f(3): "(“f(0)”)"f(2), "(“f(1)”)"f(1), “(“f(2)”)”
    ...

    可以递推出 f(n) = "(“f(0)”)"f(n-1) , "(“f(1)”)"f(n-2) "(“f(2)”)"f(n-3) … "(“f(i)”)“f(n-1-i) … “(f(n-1)”)”

     */

    public List<String> generateParenthesisIteration(int n) {
        HashMap<Integer, List<String>> hashMap = new HashMap<>();
        hashMap.put(0, Collections.singletonList(""));
        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String fj : hashMap.get(j)) {
                    for (String fi_j_1 : hashMap.get(i - j - 1)) {
                        list.add("(" + fj + ")" + fi_j_1);// calculate f(i)
                    }
                }
            }
            hashMap.put(i, list);
        }
        return hashMap.get(n);
    }










    //根据链表转换为括号对字符
    private static String convertToParenthesis(List<Integer> list1) {
        HashMap<Integer,Boolean>hashMap=new HashMap<>();
        StringBuilder stringBuilder=new StringBuilder();
        int max=1;
        for(int i:list1){
            if(!hashMap.containsKey(i)){
                int k=max;
                max=max>i?max:i;
                while (k<=i){
                    if(!hashMap.containsKey(k)){
                        stringBuilder.append("(");
                        hashMap.put(k,true);
                    }
                    k++;
                }
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    //判断链表中的数字是否符合出入栈性质
    /*
    思路：利用堆栈性质判断顺序是否符号要求，同时利用哈希表记录进栈元素，避免重复进栈。
        （改进，由于数字只进出栈一次，而且按数字大小有对应的进展顺序，可以只用一个局部变量记录进栈数字即可，避免重复判断）
     */
    private static boolean isValidOrder(List<Integer> list1) {
        Stack<Integer> stack=new Stack<>();
        HashMap<Integer,Boolean>hashMap=new HashMap<>();
        int m,max=1;
        for(int i:list1){
            if(!hashMap.containsKey(i)){
                int k=max;
                max=max>i?max:i;
                while (k<=i){
                    stack.push(k);
                    hashMap.put(k,true);
                    k++;
                }
            }
            m=stack.pop();
            if(m!=i){
                return false;
            }
        }
        return true;
    }

    /*
    求数字1...n的全排列
     */
    public static List<List<Integer>> getPermutation(int n){
        List<List<Integer>>permutation=new ArrayList<>();
        int [] array=new int[n];
        for (int i = 1; i <=n; i++) {
            array[i-1]=i;
        }
        backtracking(permutation,array,0,n-1);
        return permutation;
    }


    /* 错误算法，因为list会不停变动
    public static void backstracking(List<Integer>list, List<Integer>list1, List<List<Integer>>backtracking){
        if(list.isEmpty()){
           backtracking.add(list1);
           list1.clear();
        }else{
            List<Integer>list2=list;
           for(int i:list2){
               list2.remove(Integer.valueOf(i));
               list2.add(i);
               backstracking(list2,list1,backtracking);
           }
        }
    }

    */

    /*
    回溯求全排列：递归思想，利用数组实现
                 但要保证全局变量不会随之变动，因此考虑利用数组和数组中的元素交换实现
     */

    public static void backtracking(List<List<Integer>>list, int []array,int start, int end){
        if(end<1){
            List<Integer>list1=new ArrayList<>();
            for (int i:array){
                list1.add(i);
            }
            list.add(list1);
            return;
        }
        if(start==end){
            List<Integer>list1=new ArrayList<>();
            for (int i:array){
                list1.add(i);
            }
            list.add(list1);
        }
        else{
            for (int i = start; i <=end ; i++) {
                swap(array,i,start);
                backtracking(list,array,start+1,end);
                swap(array,i,start);
            }
        }
    }

    private static void swap(int[] array, int i, int start) {
        int temp=array[i];
        array[i]=array[start];
        array[start]=temp;
    }


    //利用字符串实现
    public static void getPermutation(String str) {
        backtracking("", str);
    }


    private static void backtracking(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                backtracking(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }





    //计算n！阶乘
    public static int getFactorial(int n){
        if(n==0)
            return 1;
        else{
            return getFactorial(n-1)*n;
        }
    }
}
