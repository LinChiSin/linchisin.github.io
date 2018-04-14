package com.linchisin.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LetterCombination17 {
    public static String [] map={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String digits=scanner.next();
            List<String> list=letterCombinations(digits);
            for(String strings:list){
                System.out.println(strings+" ");
            }
        }
    }

    private static List<String> letterCombinations(String digits) {
        List<String>list=new ArrayList<>();
        if(digits.length()==0)
            return Collections.emptyList();
        backtracking(list,digits,"");
        return list;
    }

    private static void backtracking(List<String> list, String digits, String ans) {
        if(ans.length()==digits.length()){
            list.add(ans);
            return ;
        }
        for(char c: map[digits.charAt(ans.length())-'2'].toCharArray()){
            backtracking(list,digits,ans+c);
        }
    }

}
