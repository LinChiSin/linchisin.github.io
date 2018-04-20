package com.linchisin.LeetCode;

import java.util.HashMap;

public class ValidSudoku36 {
    public static void main(String[] args) {


    }

    /*
    Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     */


    /*
    思路：Naive算法，遍历9*9数组，对于存在数值的空格，比较其所在的行、所在的列和所在的小方格
         关键点：获取所在的行、列、小方格及其对应的元素
                行，a[i][k]
                列，a[k]p[j]
                小方格， a[i/3+0+1/+2/+3][j/3+0+1+2]
    复杂度：O（n^3）,空间复杂度 HashMap?
    特殊输入：全部没有填充，全部填充？
     */

    private boolean isValidSudoku(char[][]board){
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {

                char c=board[i][j];
                if(c!='.'){
                    //比较所在的行
                    for (int k = 0; k <9 ; k++) {
                        if(k==j) continue;
                        char c2=board[i][k];
                        if(c2==c) return false;
                    }
                    //比较所在的列
                    for (int k = 0; k <9 ; k++) {
                        if(k==i) continue;
                        char c2=board[k][j];
                        if(c2==c) return false;
                    }
                    //比较所在的小方格
                    int rows=i/3;
                    int cols=j/3;
                    for (int k = rows*3; k <rows*3+3 ; k++) {
                        for (int l = cols*3; l <cols*3+3 ; l++) {
                            if(k==i&&l==j) continue;
                            char c2=board[k][l];
                            if(c2==c) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
