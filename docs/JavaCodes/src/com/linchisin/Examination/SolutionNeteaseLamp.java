package com.linchisin.Examination;

import com.sun.deploy.util.StringUtils;

import java.util.Scanner;

/*
输入例子1:
2
3
.X.
11
...XX....XX

输出例子1:
1
3
 */
public class SolutionNeteaseLamp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();   //(1 <= t <= 1000)
        int [] minLamps=new int[t];
        for (int i = 0; i <t; i++) {
            int n=scanner.nextInt();
            String string=scanner.next();
            minLamps[i]=getMinLamps(n,string);

        }
        for (int i = 0; i <t; i++) {
            System.out.println(minLamps[i]);
        }

    }

    private static int getMinLamps(int n, String string) {

//        String[]stringsDots= StringUtils.splitString(string,"X");  //按照X分割
        String[]stringsDots=splitString(string,"X");
        int k=0;
        for (int i = 0; i <stringsDots.length ; i++) {
            if(!stringsDots[i].equals(""))
                k++;
        }

        int consistentDots=stringsDots.length;
        int [] consistentDotsLamps=new int[consistentDots];
        int minLamps=0;
        for (int i = 0; i <consistentDots; i++) {
            consistentDotsLamps[i]=getMinLampsOfConsistentLamps(stringsDots[i]);
            minLamps+=consistentDotsLamps[i];
        }

//        String[] stringsX=StringUtils.splitString(string,"\\.");
        String[] stringsX=splitString(string,"\\.");

        for (int i = 0; i <stringsX.length ; i++) {
            if(stringsX[i].equals("X")&&i!=0&&i!=string.length()-1)
                minLamps--;
        }
        return minLamps;

    }

    private static int getMinLampsOfConsistentLamps(String stringsDot) {
        return (int)Math.ceil((double)stringsDot.length()/(double)3);
    }
    private static String[] splitString(String string, String regex){
        String[] strings=string.split(regex);
        int k=0;
        for (int i = 0; i <strings.length ; i++) {
            if(!strings[i].equals(""))
                k++;
        }
        String[] newStrings=new String[k];
        k=0;
        for (int i = 0; i <strings.length ; i++) {
            if(!strings[i].equals("")) {
                newStrings[k] = strings[i];
                k++;
            }
        }
        return newStrings;

    }
}
