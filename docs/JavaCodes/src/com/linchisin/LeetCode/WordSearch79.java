package com.linchisin.LeetCode;

import javafx.geometry.Pos;

import java.util.*;

public class WordSearch79 {

    public static void main(String[] args) {

        char[][]board={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word="ABCCED";
//        String word="SEE";
        String word="ABCB";
        System.out.println(new WordSearch79().exist(board,word));

    }
    /*
    board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
     */

    /*
    思路：动态规划，dp[i]表示字符串的第0位到字符串的第i位是否能在board中找到
         dp[0]=判断第0个字符是否在数组中，若不在，直接返回false，若在，利用队列记录出现的位置；
         dp[i]=dp[i-1]&&word.charAt(i)在上个位置的左右
         要求相同位置的不能使用两次
     */
    public  boolean exist(char[][] board, String word) {
        boolean[]dp=new boolean[word.length()];
        Queue<Position> list=new LinkedList<>();
        int m=board.length;
        int n=board[0].length;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]==word.charAt(0)){
                    dp[0]=true;
                    list.add(new Position(i,j));
                }
            }
        }
        HashMap<Position,Integer> hashMap=new HashMap<>();
        int currentSize=list.size();
        if(!dp[0]) return false;
        for (int i = 1; i <word.length(); i++) {
            for (int j = 0; j < currentSize; j++) {
                Position position = list.remove();
                if (!hashMap.containsKey(position)) {
                    hashMap.put(position,0);
                    Position topPosition = new Position(position.i - 1, position.j);
                    Position bottomPosition = new Position(position.i + 1, position.j);
                    Position leftPosition = new Position(position.i, position.j - 1);
                    Position rightPosition = new Position(position.i, position.j + 1);
                    if (position.i >= 1  && !hashMap.containsKey(topPosition)&&board[position.i - 1][position.j] == word.charAt(i)) {
                        dp[i] = true;
                        list.add(topPosition);
                    }
                    if (position.i < m - 1 &&!hashMap.containsKey(bottomPosition)&& board[position.i + 1][position.j] == word.charAt(i)) {
                        dp[i] = true;
                        list.add(bottomPosition);
                    }
                    if (position.j >= 1 && !hashMap.containsKey(leftPosition)&&board[position.i][position.j - 1] == word.charAt(i)) {
                        dp[i] = true;
                        list.add(leftPosition);
                    }
                    if (position.j < n - 1 &&!hashMap.containsKey(rightPosition)&&board[position.i][position.j + 1]== word.charAt(i)) {
                        dp[i] = true;
                        list.add(rightPosition);
                    }
                }
            }
            if (!dp[i]) return false;
            currentSize = list.size();
        }
        return dp[word.length()-1];
    }
    private class Position{
        public int i;
        public int j;
        public Position(int i,int j){
            this.i=i;
            this.j=j;
        }

        @Override
        public int hashCode() {
            return 31*i+j;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj==this) return true;
            if(obj instanceof Position){
                return (((Position) obj).i==this.i)&&(((Position) obj).j==this.j);
            }
            return false;
        }
    }
}
