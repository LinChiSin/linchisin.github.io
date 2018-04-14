package com.linchisin.LeetCode;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class ValidParentheses20 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String s=scanner.nextLine();
            System.out.println(isValid(s));
        }
    }


    /*
    栈操作，遇到'(','{',']'则入栈，遇到')','}',']'出栈，比较出栈的符号是否与当前符号匹配；匹配可以用哈希表配对。
     */
    private static boolean isValid(String s) {
        char[] chars=s.toCharArray();
        Stack<Character>stack=new Stack<>();
        HashMap<Character,Character>hashMap=new HashMap<>();
        hashMap.put('(',')');
        hashMap.put('[',']');
        hashMap.put('{','}');
        for(char c:chars){
            if(hashMap.containsKey(c)){
                stack.push(c);
            }else{
                if(!stack.empty()){
                    char c2=stack.pop();
                    if(c!=hashMap.get(c2))
                        return false;
                }else
                    return false;
            }
        }
        return stack.empty();
    }
}
