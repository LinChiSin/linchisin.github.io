package com.linchisin.Examination;

import java.util.Scanner;

public class Webank3 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        while(scanner.hasNextLong()){
            Long N=scanner.nextLong();
            System.out.println(solution(N));
        }
    }

    private static long solution(Long n) {
        long count=1;
        String string="0";
        string="1"+string+"1";
        while(Long.valueOf(string,2)<=n){
            count++;
            string="1"+string+"1";
        }

        string="0";
        string="10"+string+"01";
        while(Long.valueOf(string,2)<=n){
            count++;
            string="10"+string+"01";
        }
        string="1";
        count++;
        string="1"+string+"1";
        while(Long.valueOf(string,2)<=n){
            count++;
            string="1"+string+"1";
        }
        string="1";
        string="10"+string+"01";
        while(Long.valueOf(string,2)<=n){
            count++;
            string="10"+string+"01";
        }
        string="";
        string="1"+string+"1";
        while(Long.valueOf(string,2)<=n){
            count++;
            string="1"+string+"1";
        }
        string="";
        string="10"+string+"01";
        while(Long.valueOf(string,2)<=n){
            count++;
            string="01"+string+"01";
        }
        return count;
    }
}
