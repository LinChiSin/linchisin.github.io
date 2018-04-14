package com.linchisin.Examination;

import java.util.Scanner;

public class HuaweiHignestMark {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int N=scanner.nextInt();
            int M=scanner.nextInt();
            int []marks=new int[N];
            for (int i = 0; scanner.hasNext()&&i <N ; i++) {
                marks[i]=scanner.nextInt();
            }
            for (int i = 0; scanner.hasNext()&&i <M ; i++) {
                char c=scanner.next().charAt(0);
                int A=scanner.nextInt();
                int B=scanner.nextInt();
                if(c=='Q'){
                    System.out.println(getHighMark(A,B,marks));
                }else{
                    changeMark(A,B,marks);
                }
            }
        }
    }

    private static int getHighMark(int A,int B,int []marks){
        int min=A<=B?A:B;
        int max=A>B?A:B;
        int high=marks[min-1];
        for (int i = min-1; i<=max-1 ; i++) {
            high=marks[i]>high?marks[i]:high;
        }
        return high;
    }
    private static void changeMark(int A,int B,int []marks){
        marks[A-1]=B;
    }
}
