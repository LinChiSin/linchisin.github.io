package com.linchisin.Examination;



import java.math.BigInteger;
import java.util.Scanner;

/*
输入例子1:
3 3
1 100
10 1000
1000000000 1001
9 10 1000000000

输出例子1:
100
1000
1001
 */
public class Main {
    private static int N;
    private static int M;
    private static BigInteger[][] DiPiArray;
    private static BigInteger [] AiArray;
    private static BigInteger [] PiArray;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String temp=scanner.nextLine();
        String []strings=temp.trim().split(" ");

        N=Integer.parseInt(strings[0]);
        M=Integer.parseInt(strings[1]);

        DiPiArray=new BigInteger[N][2];
        AiArray=new BigInteger[M];
        PiArray=new BigInteger[M];

        for (int i = 0; i <N; i++) {
            DiPiArray[i][0]=scanner.nextBigInteger();
            DiPiArray[i][1]=scanner.nextBigInteger();
        }

        for (int i = 0; i <M ; i++) {
            AiArray[i]=scanner.nextBigInteger();
        }
//        for (int i = 0; i <DiPiArray.length ; i++) {
//            for (int j = 0; j <DiPiArray[i].length ; j++) {
//                System.out.print(" "+DiPiArray[i][j]);
//            }
//            System.out.println();
//        }
//        for (int i = 0; i <AiArray.length ; i++) {
//            System.out.print(" "+AiArray[i]);
//        }
        for (int i = 0; i <M ; i++) {
            BigInteger ai=AiArray[i];
            int k=0;
            BigInteger pi=BigInteger.ZERO;
            while(k<N){
                if(DiPiArray[k][0].compareTo(new BigInteger(String.valueOf(ai)))<=0&&DiPiArray[k][1].compareTo(pi)>=0){
                    pi=DiPiArray[k][1];
                }
                k++;
            }
            PiArray[i]=pi;
        }
        for (int i = 0; i <PiArray.length ; i++) {
            System.out.println(PiArray[i]);
        }



    }
}
