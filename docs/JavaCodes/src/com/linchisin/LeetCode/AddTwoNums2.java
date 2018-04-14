package com.linchisin.LeetCode;

import java.math.BigInteger;

public class AddTwoNums2 {


    public static void main(String[] args) {

        AddTwoNums2 addTwoNums2 =new AddTwoNums2();
        ListNode listNode1= addTwoNums2.creatLinkList(new int[]{9});
        ListNode listNode2= addTwoNums2.creatLinkList(new int[]{1,9,9,9,9,9,9,9,9,9});
        addTwoNums2.printLinkList(listNode1);
        addTwoNums2.printLinkList(listNode2);
        ListNode sumList= addTwoNums2.addTwoNumbers(listNode1,listNode2);
        addTwoNums2.printLinkList(sumList);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){

        String ls1=getListString(l1);
        String ls2=getListString(l2);
        BigInteger num1=reverseInteger(ls1);
        BigInteger num2=reverseInteger(ls2);
        BigInteger sum=num1.add(num2);
        int []digits=splitInteger(sum);
        int [] reverseDigits=reverseNumsArray(digits);
        ListNode sumList=creatLinkList(reverseDigits);
        return sumList;
    }

    public int getListLength(ListNode listNode){
        if(listNode==null)
            return 0;
        else {
            int k = 1;
            while (listNode.next != null) {
                k++;
                listNode=listNode.next;
            }
            return k;
        }
    }
    public String getListString(ListNode listNode){
        ListNode listNode1=listNode;
        if (listNode1==null)
            return null;
        else {
            StringBuilder stringBuilder=new StringBuilder();
            while(listNode1!=null){
                stringBuilder.append(listNode1.val);
                listNode1=listNode1.next;
            }
            return  stringBuilder.toString();
        }
    }
    public BigInteger reverseInteger(String num){
        char[] numArray=num.toCharArray();

        for (int i = 0; i <numArray.length/2 ; i++) {
            char temp=numArray[numArray.length-1-i];
            numArray[numArray.length-1-i]=numArray[i];
            numArray[i]=temp;
        }
        String string=new String(numArray);
        return new BigInteger(string);

    }
    public int[] splitInteger(BigInteger num){
        char[] charArray=String.valueOf(num).toCharArray();
        int[] digits=new int[charArray.length];
        for (int i = 0; i <charArray.length ; i++) {
            digits[i]=Integer.parseInt(String.valueOf(charArray[i]));
        }
        return digits;
    }
    public int[] reverseNumsArray(int[]nums){

        for (int i = 0; i <nums.length/2 ; i++) {
            int temp=nums[nums.length-1-i];
            nums[nums.length-1-i]=nums[i];
            nums[i]=temp;
        }
        return nums;
    }

    public ListNode creatLinkList(int[]nums){
        ListNode listNode=null;
        for (int i = 0; i <nums.length ; i++) {
            listNode=addNode(listNode,new ListNode(nums[i]));
        }
        return listNode;
    }

    public ListNode addNode(ListNode headNode, ListNode newNode){
        if(headNode==null){
            headNode=newNode;
            return headNode;
        }else{
            ListNode temp=headNode;
            while (temp.next!=null){
                temp=temp.next;
            }
            temp.next=newNode;
            return headNode;
//            while(headNode.next!=null){
//                headNode=headNode.next;
//            }
//            headNode.next=newNode;
//            return headNode;
        }
    }

    public void printLinkList(ListNode listNode){
        if(listNode==null){
            System.out.println("链表为空");
        }else{
            while (listNode!=null){
            System.out.print(""+listNode.val+'\t');
            listNode=listNode.next;
            }
        }
    }
}

 class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val=x;
    }
}


