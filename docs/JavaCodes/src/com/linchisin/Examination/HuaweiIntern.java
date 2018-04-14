package com.linchisin.Examination;

import java.util.*;



/*
举例！假设原始字符串为：
eeefgghhh
则每种字符出现的次数分别是：
(1).eee        3次
(2).f          1次
(3).gg         2次
(4).hhh        3次
重排输出后的字符串如下：
efghegheh
编写程序，实现上述功能。
【温馨提示】
(1).原始字符串中仅可能出现“数字”和“字母”；
(2).请注意区分字母大小写。

 */

public class HuaweiIntern {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextLine()){
            String s=scanner.nextLine();
            System.out.println(solution(s));
        }

    }

    /*
    思路：用128位数组(哈希表)统计字符出现次数，最后构造<字符，次数>关联，加入链表，按照ASCII排序，最后按照ASCII码顺输出，并次数减1
     */

    private static String solution(String s) {
        LinkedHashMap<Character,Integer>map=new LinkedHashMap<>();
        int len=s.length();
        for (int i = 0; i <len ; i++) {
            char c=s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,1);
            }else{
                map.put(c,map.get(c)+1);
            }
        }
        //对记录进行排序
        List<Map.Entry<Character, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        int num=0;
        for(Integer value:map.values()){
            num+=value;
        }
        StringBuilder stringBuilder=new StringBuilder();
        while(num>0){

            Iterator<Map.Entry<Character, Integer>> entries = list.iterator();
            while (entries.hasNext()){
                Map.Entry<Character, Integer> entry = entries.next();
                if(entry.getValue()>0){
                    stringBuilder.append(entry.getKey());
                    entry.setValue(entry.getValue()-1);
                    num--;
                }
            }
        }

        return stringBuilder.toString();
    }
}
