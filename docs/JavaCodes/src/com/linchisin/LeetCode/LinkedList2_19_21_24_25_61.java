package com.linchisin.LeetCode;
import java.util.Scanner;
import java.util.Stack;

public class LinkedList2_19_21_24_25_61 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextInt()){
            int m=scanner.nextInt();
            int list1[]=new int[m];
            list1[0]=scanner.nextInt();
            ListNode head=createLinkedList(new ListNode(list1[0]));
            for (int j= 1; j <m ; j++) {
                list1[j]=scanner.nextInt();
                head=addNodeAtEnd(head,new ListNode(list1[j]));
            }
            int k=scanner.nextInt();
            printLinkedList(head);
            printLinkedList(rotateRight(head,k));
        }



//            System.out.println("链表长度为："+lengthOfLinkedList(head1));
//            printLinkedList(swapPairs(head1));


//            int k=scanner.nextInt();
//            int []list2=new int[k];
//            list2[0]=scanner.nextInt();
//            ListNode head2=createLinkedList(new ListNode(list2[0]));
//            for (int i = 1; i <k ; i++) {
//                list2[i]=scanner.nextInt();
//                head2=addNodeAtEnd(head2,new ListNode(list2[i]));
//            }
//            printLinkedList(head2);
//            System.out.println("链表长度为："+lengthOfLinkedList(head2));
//
//
//            ListNode head3=mergeTwoLists(head1,head2);
//            printLinkedList(head3);
    }


    /*
    描述：循环右移链表k次
    思路：链表循环右移：遍历链表，统计链表节点个数，并找到尾结点及其上一节点，使尾结点指向头结点，并置尾结点的上一节点指向null,返回尾结点作为头结点
    复杂度：O(n*K)
    特殊输入：原链表为空，原链表只有1个节点，k=0
     */
    private static ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;
        if(k==0) return head;
        ListNode node=head;
        ListNode headNode=head;
        int listNum=1;
        while(node.next!=null){
            node=node.next;
            listNum++;
        }
        for (int i = 0; i < k%listNum; i++) {
            headNode=rotateRightOnlyOnce(headNode);
        }
        return headNode;
    }

    private static ListNode rotateRightOnlyOnce(ListNode head) {
        if(head==null) return null;
        ListNode prev=null;
        ListNode node=head;
        ListNode headNode=head;
        while(node.next!=null){
            prev=node;
            node=node.next;
        }
        if(prev==null) return head;
        node.next=headNode;
        prev.next=null;
        headNode=node;
        return headNode;
    }



    //创建空链表，返回头结点
    public static ListNode createLinkedList(ListNode head){
        return  addNodeAtEnd(head,null);
    }

    //按照数组创建链表，返回头结点
    public static ListNode createLinkedList(int []nums){
        if(nums.length==0)
            return null;
        ListNode headNode= createLinkedList(new ListNode(nums[0]));
        if(nums.length>1){
            for (int i = 1; i <nums.length ; i++) {
                addNodeAtEnd(headNode,new ListNode(nums[i]));
            }
        }
        return headNode;
    }

    //链表末尾添加节点，返回头结点
    public static ListNode addNodeAtEnd(ListNode head, ListNode listNode){
        if(listNode==null)
            return head;
        ListNode listNode1=head;
        while(listNode1.next!=null){
            listNode1=listNode1.next;
        }
        listNode1.next=listNode;
        return head;
    }

    //移除链表最后一个节点，返回头结点
    public static ListNode removeNodeAtEnd(ListNode head){
       return removeNode(head,lengthOfLinkedList(head));
    }


    //移除指定第k个节点，返回头结点
    public static ListNode removeNode(ListNode head,int k){
        if(head==null){
            System.out.println("无法删除空链表！");
            return null;
        }
        int len=lengthOfLinkedList(head);
        if(k>len){
            System.out.println(k+"大于链表长度:"+len);
            return head;
        }
        if(k==1){
            head=head.next;
        }else{
        ListNode listNodePre=getNode(head,k-1);
        ListNode listNode=getNode(head,k);
        listNodePre.next=listNode.next;
        }
        return head;
    }

    //移除倒数第N个节点，返回头结点
    public static ListNode removeNthFromEnd(ListNode head, int n){
        return removeNode(head,lengthOfLinkedList(head)+1-n);
    }

    //在链表队首添加节点，返回头结点
    public static ListNode addNodeAtStart(ListNode head,ListNode listNode){
        listNode.next=head;
        return listNode;
    }

    //按自然顺序打印链表所有节点
    public static void printLinkedList(ListNode head){
        printLinkedList(head,true);
    }
    //按指定顺序打印链表所有节点, dir为指定方向，dir为true时为自然顺序。
    public static void printLinkedList(ListNode head,boolean dir){

        ListNode listNode=head;
        if(listNode==null){
            System.out.println("链表为空");
            return;
        }

        if(dir){ //正向打印
            while(listNode!=null){
                System.out.print(listNode.val+"->");
                listNode=listNode.next;
            }
        }else{ //反向打印
            //利用堆栈辅助
            Stack<ListNode>stack=new Stack<>();
            while(listNode!=null){
                stack.push(listNode);
                listNode=listNode.next;
            }
            while(!stack.empty()){
                System.out.print(stack.pop().val+"->");
            }
        }
        System.out.println();
    }

   //求链表长度
    public static int lengthOfLinkedList(ListNode head){
        if(head==null)
            return 0;
        int length=1;
        ListNode listNode=head;
        while(listNode.next!=null){
            listNode=listNode.next;
            length++;
        }
        return length;
    }

    //返回链表的正向第k个节点（从1开始）
    public static ListNode getNode(ListNode head, int k){
        if(k<=1)
            return head;
        if(k>lengthOfLinkedList(head)){
            System.out.println(k+"大于链表长度:"+lengthOfLinkedList(head));
            return head;
        }
        ListNode listNode=head;
        int i=1;
        while(i<k){
            listNode=listNode.next;
            i++;
        }
        return listNode;
    }


    //合并两个有序链表
    /*
    思路：遍历链表，先比较头指针，确定新的头指针，进而再连续比较,并移动链表。
         复杂度O(n1+n2)
     */
    public static ListNode mergeTwoLists(ListNode listNode1,ListNode listNode2){
        if(listNode1==null&&listNode2==null)
            return null;
        if(listNode1==null)
            return listNode2;
        else if(listNode2==null)
            return listNode1;
        ListNode head;
        //判断头指针
        if(listNode1.val<=listNode2.val){
            head=listNode1;
            listNode1=listNode1.next;
        }else{
            head=listNode2;
            listNode2=listNode2.next;
        }
        ListNode listNode=head;

        //判断后续指针
        while (listNode1!=null&&listNode2!=null){
          if(listNode1.val<=listNode2.val){
              listNode.next=listNode1;
              listNode1=listNode1.next;
              listNode=listNode.next;
          }else{
              listNode.next=listNode2;
              listNode2=listNode2.next;
              listNode=listNode.next;
          }
        }
        listNode.next=listNode1==null?listNode2:listNode1;
        return head;
    }

    //交换单链表中相邻节点
    //要求：只能使用常数大小的空间，不允许修改节点的值，仅允许改变节点本身
    /*
     思路：按相邻节点进行遍历，先考虑头指针为空和仅有一个结点的特殊情况
          进而再交换头节点对，需要先找出末节点的Next节点，修改末节点的指向为头结点，进而让头节点指向原末节点的Next节点
          此后遍历（previous->前->末->Next）节点，更改为（prev->末->前->Next）
     */

    public static ListNode swapPairs(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode temp=head.next.next;
        ListNode after=head.next;
        after.next=head;
        head.next=temp;
        ListNode prevNode=head;
        ListNode frontNode=temp;
        ListNode  afterNode;
        ListNode nextNode;
        while (frontNode!=null){
            afterNode=frontNode.next;
            if(afterNode!=null){
                nextNode=afterNode.next;
                prevNode.next=afterNode;
                afterNode.next=frontNode;
                frontNode.next=nextNode;
                prevNode=frontNode;
                frontNode=nextNode;
            }else{
                return after;
            }
        }
        return after;
    }

    //按照k个结点对反转链表，即swapPairs的加强版
    /*
      思路：
     */


    //按序合并排列k个已经排列好的链表
    /*
    思路：分治，先两个两个合并，递归，直到合并成最后一个
     */
    public static ListNode mergeKLists(ListNode[]lists){
        return lists.length==0?null:mergeKListsRecursive(lists,0,lists.length-1);
    }

    private static ListNode mergeKListsRecursive(ListNode[] lists, int start, int end) {
        ListNode head1,head2,head;
        if(start==end)
            return lists[start];
        if(end==start+1){
            head=mergeTwoLists(lists[start],lists[end]);
            return head;
        }
        int len=end-start,mid=(len>>1)+start;
        head1=mergeKListsRecursive(lists,start,mid);
        head2=mergeKListsRecursive(lists,mid+1,end);
        return mergeTwoLists(head1,head2);
    }

    /*
    思路：链表调整过程中需要记录以下几个结点：prev、node、nextNode、nextNextNode, 注意最后应当返回prev
    复杂度：O(n)
    特殊输入：链表为空，链表仅有一个
     */

    private static ListNode reverseLinkedList(ListNode head){
       if(head==null)
           return null;
       ListNode listNode=head;
       ListNode prev=null;
       ListNode nextNode=listNode.next;
       while(nextNode!=null){
           listNode.next=prev;
           ListNode nextNextNode=nextNode.next;
           prev=listNode;
           listNode=nextNode;
           nextNode=nextNextNode;
       }
       listNode.next=prev;
       prev=listNode;
       return prev;
    }



}
