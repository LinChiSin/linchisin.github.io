package com.linchisin.Examination;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Webank2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(solution(scanner.nextInt()));
    }

    private static int solution(int n) {
        List<Integer>list=new ArrayList<>();
        int i,j;
        boolean m;
        for (i = 1; i <= n; i++) {
            m = true;
            for (j = 2; j < i; j++) {
                if (i % j == 0) {
                    m = false;
                    break;
                }
            }
            if (m) {
                list.add(i);
            }
        }
        int sum=0;
        double lgn=Math.log(n);
        for (i = 1; i <list.size() ; i++) {
            sum += (int)lgn / Math.log(i);
        }
        return sum;
        }

    }
