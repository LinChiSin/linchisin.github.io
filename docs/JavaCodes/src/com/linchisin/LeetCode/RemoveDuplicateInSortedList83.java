import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RemoveDuplicateInSortedList83 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            int list1[] = new int[m];
            list1[0] = scanner.nextInt();
            ListNode head = createLinkedList(new ListNode(list1[0]));
            for (int j = 1; j < m; j++) {
                list1[j] = scanner.nextInt();
                head = addNodeAtEnd(head, new ListNode(list1[j]));
            }
            printLinkedList(head);
            printLinkedList(deleteDuplicates(head));

        }
    }

   /*
    删除升序单链表中重复的元素，只保留一个
    */
   /*
   思路：链表的删除会比数组的删除高效，只需更改指针即可，重点是指针的记录
   复杂度：O(n)
   特殊输入：原链表为空
    */
    public static ListNode deleteDuplicatesRetainSingle(ListNode head) {
        if(head==null) return null;
        ListNode prev=head,node=head.next,next;
        while (node!=null){
            next=node.next;
            if(node.val==prev.val){
                prev.next=next;
                node=next;
            }else{
                prev=node;
                node=next;
            }
        }
        return head;
    }

    /*
   删除升序单链表中重复的元素，不保留有重复的元素，只保留仅出现一次的元素
   */
   /*
   思路：链表的删除会比数组的删除高效，只需更改指针即可，重点是指针的记录
   复杂度：O(n)
   特殊输入：原链表为空
    */
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head==null) return null;
        ListNode newHead=null,prev=head,node=head.next,next, newNode=null; //记录新的头结点、前结点、现结点及后结点
        while(node!=null){
            next=node.next;
            if(node.val==prev.val){ //找到有重复的结点
                while(next!=null&&next.val==node.val){ //把重复的结点全部找出来，并使头结点指向后结点
                    node=next;
                    next=node.next;
                }
                if(newHead==null) {
                    newHead=next;
                    node=next;
                }
                else {
                    if(newNode!=null){
                        newNode=next;
                    }else{
                        newNode=newHead.next;
                    }
                    node=next;
                }
            }else{
                if(newHead==null) {
                    newHead=prev;
                    newNode=node;
                    newHead.next=newNode;
                }
                else {
                    if(newNode!=null){
                        newNode.next=node;
                        newNode=newNode.next;
                    }else{
                        newNode=newHead.next;
                    }
                }
                prev=node;
                node=next;
            }
        }
        return newHead;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode newHead=null,prev=head,node=head.next,next,newNode=null;
        while(node!=null){
            next=node.next;
            if(node.val==prev.val){
                while(next!=null&&next.val==node.val){
                    node=next;
                    next=node.next;
                }
                if(newHead==null) {
                    newHead=next;
                    newNode=newHead.next;
                }
                else {
                    if(newNode!=null)
                    newNode.next=next;
                }
            }else{
                if(newHead==null){
                    newHead=prev;
                    newNode=node;
                }else{
                    newNode.next=next;
                    newNode=newNode.next;
                }
                prev=node;
                node=next;
            }
        }
        return newHead;
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
            Stack<ListNode> stack=new Stack<>();
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



}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
