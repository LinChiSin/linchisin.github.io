package com.linchisin.LeetCode;

import java.util.*;

public class MergeIntervals56 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int n=scanner.nextInt();
            List<Interval>list=new ArrayList<>();
            for (int i = 0; i <n; i++) {
                list.add(new Interval(scanner.nextInt(),scanner.nextInt()));
            }
            List<Interval>result=merge(list);
            for (Interval interval:result){
                System.out.print(interval.start+" "+interval.end);
                System.out.println();
            }
        }
    }


    /*
    题目描述：合并重叠区间
    思路：先对区间的左边界进行排序，保证往一个方向排列，从最左边的区间开始遍历：
        此时前后两个区间A(x1,x2)和B（y1,y2）（y1>=x1）只能有三种情况：
        B被包含在A内（x1<=y1<=y2<=x2）,此时A区间不变，抛弃B区间
        B与A完全重叠或部分重叠；此时A区间修改为（x1,y2），抛弃B区间
        B完全不与A重叠(x1<=x2<y1<=y2),此时A区间不变，新增B区间
        后续的空间都需要与前面的空间进行判断
    复杂度：O(n^2)  (错误!) O（nlogn），排序复杂度O(nlgn),比较复杂度O(n)
    特殊情况：输入为空
     */
    public static List<Interval> merge(List<Interval> intervals){
        if(intervals.isEmpty()) return Collections.emptyList();
        List<Interval>list=new ArrayList<>();
        //原集合排序
        intervals.sort(Comparator.comparingInt(o -> o.start));
        list.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval before=list.get(list.size()-1);
            Interval after=intervals.get(i);
            if(after.end<=before.end){  //完全包含
            }else if(before.end<after.start){   //完全不重叠
                list.add(after);
            }else{  //部分重叠
                before.end=after.end;
            }
        }
        return list;
    }
}

class Interval{
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
