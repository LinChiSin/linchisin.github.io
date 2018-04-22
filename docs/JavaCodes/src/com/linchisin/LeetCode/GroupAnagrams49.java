package com.linchisin.LeetCode;

import java.util.*;

public class GroupAnagrams49 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        String[] strs=new String[n];
        for (int i = 0; i <n; i++) {
            strs[i]=scanner.nextLine();
        }
        System.out.println(groupAnagrams(strs));
    }

    /*
    思路：  哈希表，将字符串的所有字母全部放入哈希表，判断哈希表是否相等，哈希表中的哈希表
           关键：如何判断哈希表相同，HashMap的equals()方法怎么实现的 ---->继承的AbstractMap中的equals方法，只要HashMap中存放的键值对相同（无序）就相同


    完备性考虑： 当字符串中存在重复的Key值，写入哈希表中会进行Value替换，导致Key值覆盖
                存在反例如bob,boo会被判成相同的哈希表  ————> 解决办法：

    复杂度：O(m),m为所有字符数
    特殊输入：strs为空
     */
    private static List<List<String>> groupAnagramsNotCorrect(String[] strs) {
        if(strs==null) return Collections.emptyList();
        List<List<String>>result=new ArrayList<>();
        HashMap<HashMap<Character,Integer>,Integer> hashMapAll=new HashMap<>();
        int k=0;
        for (int i = 0; i <strs.length ; i++) {
            HashMap<Character,Integer> hashMap=new HashMap<>();
            for (int j = 0; j <strs[i].length() ; j++) {
                hashMap.put(strs[i].charAt(j),0);
            }
            if(hashMapAll.containsKey(hashMap)){  //存在重复
                List<String>list=result.get(hashMapAll.get(hashMap));
                list.add(strs[i]);
            }else{
                List<String>list=new ArrayList<>();
                list.add(strs[i]);
                hashMapAll.put(hashMap,k);
                k++;
                result.add(list);
            }
        }
        return result;
    }

   /*
   思路:将待判断数组进行排序（按字母表顺序进行排序），判断哈希表中是否存在排序后的字符串。
       如果哈希表中不存在该字符串，则将<String,k>放入哈希,k是用来记录不同组数的变量,初始值为0，如果有新的字符串，k++。
       并新建一个List存放该字符串，将该List放入最终的结果list中；
       如果哈希表中存在该字符串，则按字符串对应的k值找到结果集中对应的list,list添加这个重复的字符串即可。
   复杂度：字符串排序的复杂度是O(logm)，则最终的复杂度是O(n*logm)
   特殊输入：strs为空
    */
    private static List<List<String>> groupAnagrams(String[] strs) {
        if(strs==null) return Collections.emptyList();
        List<List<String>>result=new ArrayList<>();
        int k=0;
        HashMap<String,Integer> hashMap=new HashMap<>();
        for (int i = 0; i <strs.length; i++) {
            char[]chars=strs[i].toCharArray();
            Arrays.sort(chars);
            String s=new String(chars);
            if(!hashMap.containsKey(s)){
                hashMap.put(s,k);
                k++;
                List<String>list=new ArrayList<>();
                list.add(strs[i]);
                result.add(list);
            }else{
                List<String>list=result.get(hashMap.get(s));
                list.add(strs[i]);
            }
        }
        return result;
    }



}
