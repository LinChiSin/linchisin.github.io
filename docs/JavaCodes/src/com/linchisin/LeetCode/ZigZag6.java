package com.linchisin.LeetCode;

import java.util.Scanner;

public class ZigZag6 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            String string=scanner.next();
            int numRows=scanner.nextInt();
            System.out.println(convert2(string,numRows));
        }
    }

    private static String convert(String s, int numRows) {
        int Stringlength=s.length();
        if(Stringlength==0||numRows==1)
            return s;
        int zLength=2*numRows-2;
        int zNum=Stringlength/zLength;
        int remainder=Stringlength%zLength;
        boolean flag=remainder<=numRows;
        char [][]matrix;
        if(flag)
            if(remainder==0)
                matrix=new char[numRows][zNum*(numRows-1)];
            else
                matrix=new char[numRows][zNum*(numRows-1)+1];
        else
            matrix=new char[numRows][zNum*(numRows-1)+remainder-numRows+1];
        int k=0;
        for (int i = 0; i <zNum ; i++) {
            for (int j = 0; j <numRows ; j++) {
                matrix[j][i*(numRows-1)]=s.charAt(k++);
            }
            for (int j=0;j<numRows-2;j++){
                matrix[numRows-2-j][i*(numRows-1)+j+1]=s.charAt(k++);
            }
        }
        if(flag){
            for (int j = 0; j <remainder; j++) {
                matrix[j][zNum * (numRows - 1)] = s.charAt(k++);
            }
        }else{
            for (int j = 0; j <numRows; j++) {
                matrix[j][zNum * (numRows - 1)] = s.charAt(k++);
            }
            for (int j = 0; j <remainder-numRows; j++) {
                matrix[numRows-2-j][zNum*(numRows-1)+j+1]=s.charAt(k++);
            }
        }
        return matrixToString(matrix);

    }

    private static String matrixToString(char[][]matrix){
        StringBuilder stringBuilder=new StringBuilder();
        for (char[] aMatrix : matrix) {
            for (char anAMatrix : aMatrix) {
                if (anAMatrix !=0)
                    stringBuilder.append(anAMatrix);
            }
        }
        return stringBuilder.toString();
    }

    private static String convert2(String s, int numRows) {
        if(s.length()<=numRows||numRows==1)
            return s;
        char[]chars=new char[s.length()];
        int zLength=2*numRows-2;
        int zNum=s.length()/zLength;
        int remainder=s.length()%zLength;
        int k=0;
        int N=numRows-1;
        int M=numRows-2;
        int remainNum=remainder;
        int charNum=0;
        //第一行
        for (int i = 1; i <=zNum ; i++) {
            chars[charNum++]=s.charAt(k);
            k=k+zLength;
        }
        if(remainder!=0){
            chars[charNum++]=s.charAt(k);
            remainNum--;
        }
        //中间行
        for (int j = 1; j <=M; j++) {
            k=j;
            for (int i = 1; i <=zNum ; i++) {
                chars[charNum++]=s.charAt(k);
                chars[charNum++]=s.charAt(k+2*(N-j));
                k=k+zLength;
            }
            if(remainder>1&&remainNum>=1) {
                if (numRows + numRows-j-1> remainder) {
                    chars[charNum++]=s.charAt(k);
                    remainNum--;
                } else {
                    chars[charNum++]=s.charAt(k);
                    chars[charNum++]=s.charAt(k+2*(N-j));
                    remainNum--;
                }
            }
        }
        //最后一行
        k=N;
        for (int i = 1; i <=zNum ; i++) {
            chars[charNum++]=s.charAt(k);
            k=k+zLength;
        }
        if(remainder>=numRows){
            chars[charNum]=s.charAt(k);
        }
        return new String(chars);
    }
}
