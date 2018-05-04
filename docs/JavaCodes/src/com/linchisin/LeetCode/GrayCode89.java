package com.linchisin.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GrayCode89 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            System.out.println(grayCode(scanner.nextInt()));
        }
    }


    public List<Integer> grayCodeFast(int n) {
        List<Integer> result = new ArrayList<>();

        for(int i = 0 ; i< 1<<n ; i++){
            result.add(i^i>>1);  //与自身的一半异或
        }

        return result;
    }

    /*
    描述：生成指定数字n的所有可能的格雷码对应的十进制数
    思路：深搜遍历，回溯
    提示：以二进制为0值的格雷码为第0项，第一项改变最右边的位元（a^1),第二项改变右起第一个为1的位元的左边位元(a^？）
         第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码（2^n-1)
     */
    public static List<Integer> grayCode(int n) {
        if(n<0) return Collections.emptyList();
        List<Integer>result=new ArrayList<>();
        result.add(0);
        int num=0;
        int totalNum=1;
        for (int i = 0; i < n; i++) {//左移n位，计算2^n
            totalNum=totalNum<<1;
        }
        totalNum-=1; //所有操作次数
        for (int i = 0; i < totalNum; i++) {
            if(i%2==0){
                //改变最右边位元
                num=num^1;
            }else{
                //改变右起第一个为1的位元的左边位元(a^？）
                int digit=findLeftOfFirstRightOne(num);
                int binaryDigit=1;
                for (int k = 0; k < digit; k++) {//左移n位，计算2^n
                    binaryDigit=binaryDigit<<1;
                }
                num=num^binaryDigit;
            }
            result.add(num);
        }
        return result;
    }

    private static int findLeftOfFirstRightOne(int num) {
        String binaryNum=Integer.toString(num,2);
        int length=binaryNum.length();
        int i=length-1;
        int digit=0;
        while(i>=0){
            if(binaryNum.charAt(i)=='1'){
                digit+=1;
                return digit;
            }
            i--;
            digit++;
        }
        return length;
    }
}
