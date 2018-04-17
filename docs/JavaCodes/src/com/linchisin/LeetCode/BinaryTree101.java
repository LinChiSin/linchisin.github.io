package com.linchisin.LeetCode;


import java.util.*;

public class BinaryTree101 {

    /*
                 1
               /   \
             2     3
            / \     \
           4  5     6
     */

    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.right=node6;
//        System.out.println(getNodeNumRec(node1));
//        System.out.println(getNodeNumDFS(node1));
//        System.out.println(getNodeNumBFS(node1));
//        System.out.println(getHeightRec(node1));
//        System.out.println(getHeight(node1));
//        preOrderTraverseRec(node1);
//        preOrderTraverse(node1);
//        midOrderTraverseRec(node1);
//        midOrderTraverse(node1);
//        postOrderTraverseRec(node1);
//        postOrderTraverse(node1);
        System.out.println(preOrderTraverseString(node1));
        System.out.println(preOrderReverseTraverseString(node1));
        System.out.println(isSymmetric(node1));




    }

   /*
   思路：非递归实现中序遍历，利用堆栈及其FILO性质
        原则：先输出左子树，再输出父节点，最后输出右子树，所以首先应当迭代进入最左侧的子结点
             此中的技巧是总是把每个节点看待成根节点，因此每次只需要输出一个根节点，并在输出“根节点”后处理右子树
   特殊输入：root本身为空
   */
    private static void midOrderTraverse(TreeNode root) {
        if(root==null)
            return;
        Stack<TreeNode>stack=new Stack<>();
        TreeNode treeNode=root;
        System.out.println();
        while(true){
            while (treeNode!=null){
                stack.push(treeNode);
                treeNode=treeNode.left;
            }
            if(stack.isEmpty())
                break;
            treeNode=stack.pop();
            System.out.print(treeNode.val+" ");
            treeNode=treeNode.right;  //处理右子树
        }
    }

    /*
    中序遍历，递归实现
     */
    private static void midOrderTraverseRec(TreeNode root) {
        if(root==null)
            return;
        midOrderTraverseRec(root.left);
        System.out.print(root.val+" ");
        midOrderTraverseRec(root.right);
    }

    /*
  思路：非递归实现后序遍历，利用堆栈及其FILO性质
  技巧： 要求输出顺序为（左-右-中，则可以利用（中-右-左）翻转实现，而（中-右-左）又可以通过前序遍历（非递归）实现（即与正常的前序遍历相反，左结点比右结点先入栈）
  复杂度: TC O(n)，SC O（n）//两个堆栈
  特殊输入：root本身为空
  */
    private static void postOrderTraverse(TreeNode root) {
        if(root==null)
            return;
        Stack<TreeNode>stack=new Stack<>();
        Stack<TreeNode>reverseStack=new Stack<>();
        TreeNode treeNode=root;
        System.out.println();
        stack.push(treeNode);
        while(!stack.isEmpty()){
            treeNode=stack.pop();
            reverseStack.push(treeNode);
            if(treeNode.left!=null)
                stack.push(treeNode.left);
            if(treeNode.right!=null)
                stack.push(treeNode.right);
        }
        while (!reverseStack.isEmpty())
            System.out.print(reverseStack.pop().val+" ");
    }

    /*
    后序遍历，非递归实现，不依赖第二个堆栈
    思路：判断上次访问的节点是位于左子树，还是右子树。
         若是位于左子树，则需跳过根节点，先进入右子树，再回头访问根节点
         若是位于右子树，则直接访问根节点
         因此需要两个指针记录当前访问的结点currentNode和上次访问的结点LastNode
     */

    private static void postOrderTraverseWithOneStack(TreeNode root){
        if(root==null)
            return;
        TreeNode currentNode=root;
        TreeNode lastNode=null;
        Stack<TreeNode>stack=new Stack<>();

        //把currentNode移至左子树最下边
        while(currentNode!=null){
            stack.push(currentNode);
            currentNode=currentNode.left;
        }
        //外层大循环，要求栈内不空
        while(!stack.isEmpty()){
            currentNode=stack.pop();
            //根节点被访问的前提：无右子树或者右子树已经被访问
            if(currentNode.right!=null&&currentNode.right!=lastNode){
                stack.push(currentNode);
                //此时再判断右子树是否为空，若空，直接返回，如不空，则进入右子树的左子树
                currentNode=currentNode.right;
                while(currentNode!=null){
                    stack.push(currentNode);
                    currentNode=currentNode.left;
                }
            }else{  //右子树为空，直接输出
                System.out.print(currentNode.val+" ");
                lastNode=currentNode;
            }
        }
    }



    /*
    后序遍历，递归实现
     */
    private static void postOrderTraverseRec(TreeNode root) {
        if(root==null)
            return;
        midOrderTraverseRec(root.left);
        midOrderTraverseRec(root.right);
        System.out.print(root.val+" ");
    }



    /*
  非递归计算二叉树高度(深度)
  思路：广搜，利用队列存储及其FIFO性质，在访问根节点后将其左右子结点（如果有的话）入列，此题的关键在于高度如何增加？应当在何时增加？
       定义两个count：（重点）
       定义当前楼层节点数目currentLevelCount,初始值为1(根节点)，当父节点队首出队时，currentLevelCount--;
       定义下一楼层节点数目nextLevelCount，初始值为0，当子结点在队尾入队时，nextCurrentCount++;
       当currentLevelCount为0时，高度count++,此时令currentLevelCount=nextLevelCount,nextLevelCount=0;
  特殊输入：root本身为空
  复杂度：O(n) n为节点个数
   */
    private static int getHeight(TreeNode root) {
        if(root==null)
            return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int count=0,currentLevelCount=1,nextLevelCount=0;
        while(!queue.isEmpty()){
            TreeNode treeNode=queue.remove();
            currentLevelCount--;
            if(treeNode.left!=null){
                queue.add(treeNode.left);
                nextLevelCount++;
            }
            if(treeNode.right!=null){
                queue.add(treeNode.right);
                nextLevelCount++;
            }
            if(currentLevelCount==0){
                currentLevelCount=nextLevelCount;
                nextLevelCount=0;
                count++;
            }
        }
        return count;
    }

    /*
    递归计算二叉树高度(深度)
    思路：从根节点往左右子结点调用，分别计算左右子数的高度，最后返回左右子树高度中的最大值+1；
    特殊输入：root本身为空
    复杂度：TC O(log n), SC O(lon n) (递归)
     */
    private static int getHeightRec(TreeNode root) {
        if(root==null)
            return 0;
        int leftHeight=getHeightRec(root.left);
        int rightHeight=getHeightRec(root.right);
        return Math.max(leftHeight,rightHeight)+1;
    }


    /*
    递归计算二叉树结点个数
    思路：从根节点往左右子结点递归调用，退出递归的条件是根节点为空。
    特殊输入：root本身为空
    复杂度：TC O(log n), SC O(lon n) (递归)
     */
    private static int getNodeNumRec(TreeNode root) {
        if(root==null)
            return 0;
        int leftNum=getNodeNumRec(root.left);
        int rightNum=getNodeNumRec(root.right);
        return leftNum+rightNum+1;
    }

    /*
    非递归计算二叉树结点个数
    思路：深搜，利用堆栈，利用堆栈及其FILO性质，要求左结点比右结点先输出（先找到），则需要先将右结点进栈。
    特殊输入：root本身为空
     */

    private static int getNodeNumDFS(TreeNode root){
        if(root==null)
            return 0;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        int count=0;
        TreeNode treeNode;
        while (!stack.isEmpty()){
            treeNode=stack.pop();
            count++;
            if(treeNode.right!=null)
                stack.push(treeNode.right);
            if(treeNode.left!=null)
                stack.push(treeNode.left);
        }
        return count;
    }

    /*
     非递归计算二叉树结点个数
     思路：广搜，层次遍历，利用队列存储及其FIFO性质，要求访问根节点的同时就将其子结点加入队列
     特殊输入：root本身为空
     */

    private static int getNodeNumBFS(TreeNode root) {
        if(root==null)
            return 0;
        Queue<TreeNode>queue=new LinkedList<>();
        int count=0;
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node=queue.remove();
            count++;
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
        }
        return count;
    }

    /*
    前序遍历，递归实现，类似递归计算二叉树结点个数，（实际等效为DFS）
     */
    public static void preOrderTraverseRec(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrderTraverseRec(root.left);
        preOrderTraverseRec(root.right);
    }

    /*
思路：非递归实现前序遍历，等效非递归深搜计算结点个数，利用堆栈及其FILO性质，注意右子结点需要比左子结点先入栈
特殊输入：root本身为空
 */
    private static void preOrderTraverse(TreeNode root) {
        if(root==null)
            return;
        Stack<TreeNode>stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode treeNode=stack.pop();
            System.out.println(treeNode.val+" ");
            //注意右子结点需要比左子结点先入栈
            if(treeNode.right!=null)
                stack.push(treeNode.right);
            if(treeNode.left!=null)
                stack.push(treeNode.left);
        }

    }

    /*
      求二叉树的镜像
      思路：遇到二叉树的问题，尤其是递归问题，大多数都可以将其转化为左右两个子树分治
           此题中，可以先求左子树的镜像，然后求右子树的镜像，最后交换左右子树
     */
      //在原树上修改
     private static TreeNode mirrorRec(TreeNode root){
         if(root==null)
             return null;
         TreeNode left=mirrorRec(root.left);
         TreeNode right=mirrorRec(root.right);
         root.right=left;
         root.left=right;
         return root;
     }

     /*
     判断一棵树是否为镜像树
     思路：1.遍历前左右和前右左，判断是否相同（可用字符串判断）   错误！
      */
     private static boolean isSymmetric(TreeNode root){
        if(root==null)
            return true;
         if(root.left!=null&&root.right!=null){
             if(root.left.val==root.right.val)
                 return true;
        }else if(root.left==null&&root.right==null)
            return false;
         else
             return true;
        return isSymmetric(root.left)&&isSymmetric(root.right);
     }

    public boolean isSymmetricCorrect(TreeNode root) {
        //总体上分为两种情况，一种是空树，一种不是空树
        //case1 树为空
        if(root==null)return true;
        //case2 树不为空时要利用递归的方法
        return isSymmetric(root.left,root.right);
    }
    public boolean isSymmetric(TreeNode left,TreeNode right){
        //case1 树的左右子树为空
        if(left==null&&right==null)return true;
        //case2 树的左右子树有一个为空
        if(left==null||right==null)return false;
        //case3 树的左右子树均不为空，判断节点的值是否相等
        return left.val==right.val&&isSymmetric(left.left,right.right)&&isSymmetric(left.right,right.left);
    }



    private static String preOrderReverseTraverseString(TreeNode root) {
        if(root==null)
            return "";
        String s1=String.valueOf(root.val);
        String s2=preOrderReverseTraverseString(root.right);
        String s3=preOrderReverseTraverseString(root.left);
        return s1+s2+s3;
    }

    private static String preOrderTraverseString(TreeNode root) {
         if(root==null)
             return "";
         String s1=String.valueOf(root.val);
         String s2=preOrderTraverseString(root.left);
         String s3=preOrderTraverseString(root.right);
         return s1+s2+s3;
    }


}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val=x;}
}
