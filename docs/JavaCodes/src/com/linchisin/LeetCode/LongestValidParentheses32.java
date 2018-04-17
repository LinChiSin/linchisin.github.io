package com.linchisin.LeetCode;

import java.util.Scanner;
import java.util.Stack;


public class LongestValidParentheses32 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextLine()){
            System.out.println(longestValidParentheses(scanner.nextLine()));
        }
    }



    /*
    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

    Example 1:

    Input: "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()"
    Example 2:

    Input: ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()"
     */


    /*
    目标：求连续的括号对的最大长度
    思路：1.遍历，利用堆栈发现左括号便入栈，发现右括号便使栈顶出栈，计数加1  （此方法求出来的不是连续的）
         2.动态规划，
    复杂度: TC: O(n)，SC：O（n）
    特殊情况：输入为空
     */


    private static int longestValidParenthesesUncountious(String s) {
        int count=0;
        if(s.length()==0)
            return count;
        Stack<Character>stack=new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            char c=s.charAt(i);
            if(c=='(')
                stack.push(c);
            else{
                if(!stack.isEmpty()){
                    stack.pop();
                    count+=2;
                }
            }
        }
        return count;
    }
   /*
     分类一定要考虑完备！
    */

    private static int longestValidParentheses(String s) {
        if(s.length()==0)
            return 0;
        int count = 0;
        int k=0;
        Stack<Character> stack = new Stack<>();
        if(s.charAt(0)=='(')
            stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')'&&!stack.isEmpty()&&stack.pop()=='('){
                k+=2;
                count=count<k?k:count;
            }else{
                if(c==')')
                    k=0;
                if(c=='(')
                    stack.push(c);
            }
        }
        return count;
    }

    /*
    正确答案：
     */

    public int longestValidParenthesesCorrect(String s) {
        if (s == null || s.length() < 1)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0, left = -1;
        for (int i = 0; i < s.length(); i++) {
            //如果遍历到左括号，压入堆栈
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    if (!stack.isEmpty())
                        max = Math.max(max, i - stack.peek());
                    else
                        max = Math.max(max, i - left);
                } else
                    //如果堆栈为空，说明当前的有括号无法配对，需要重新设置left的值
                    left = i;
            }
        }
        return max;
    }


}
