# 21. 栈的压入弹出序列
+ 题目描述
```
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的
弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列
的弹出序列。（注意：这两个序列的长度是相等的）
```
+ 自己的解法
```java
import java.util.Stack;

public class Solution {

    Stack<Integer> stack1 = new Stack<>();

    public boolean IsPopOrder(int[] pushA,int[] popA){

        if(popA==null||pushA==null){
            return false;
        }

        if(popA.length!=pushA.length){
            return false;
        }

        int j=0;
        int i=0;
        while(i<popA.length){
            while(j<pushA.length){
                if(popA[i]!=pushA[j]){
                    stack1.push(pushA[j]);
                    j++;
                }else{
                    //如果有两个相等的元素，则两个数组都向前移动一位
                   i++;
                   j++;
                }
            }
            //比较栈1中剩余的元素与出栈顺序中剩余的元素是否相等
            while (!stack1.isEmpty()){
                if(stack1.pop()!=popA[i]){
                    return false;
                }
                i++;
            }
        }

        return true;
    }
}

```

# 22. 层次遍历二叉树
+ 题目描述
```
从上往下打印出二叉树的每个节点，同层节点从左至右打印。
```
```java
import java.util.ArrayList;
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        //注意在root为null的时候，返回值不能返回null,要返回空的链表，否则通过率为80%
        if(root==null){
            return result;
         }
        ArrayList<TreeNode> nodes = new ArrayList<>();

        nodes.add(root);
        //当节点的大小不等于节点值的大小时，循环
        while (result.size()!=nodes.size()){
            //每一次循环
            for(int i=0;i<nodes.size();i++){
                TreeNode tmp = nodes.get(i);
                result.add(tmp.val);
                if(tmp.left!=null){
                    nodes.add(tmp.left);
                }
                if(tmp.right!=null){
                    nodes.add(tmp.right);
                }
            }
        }
        return result;
    }
}
```
+ 优化上面的方法
```java
import java.util.ArrayList;
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();

        nodes.add(root);
            //之前外面的while循环没有必要
            for(int i=0;i<nodes.size();i++){
                TreeNode tmp = nodes.get(i);
                result.add(tmp.val);
                if(tmp.left!=null){
                    nodes.add(tmp.left);
                }
                if(tmp.right!=null){
                    nodes.add(tmp.right);
                }
            }
        
        return result;
    }
}
```

# 23. 二叉搜索树的后序遍历序列
+ 题目描述
```
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
注意： 二叉搜索树：有序
后序遍历序列，根节点为最后一个元素
```
+ 解法1，递归
+ Arrays.copyOfRange的用法
```java
import java.util.Arrays;
/*
    比我做的好
*/
public class Solution {
    public static boolean VerifySquenceOfBST(int[] sequence) {
        if(sequence==null||sequence.length==0)
            return false;
        
        //二叉搜索树中，最后一个元素为根节点
        int root=sequence[sequence.length-1];
        //在二叉搜索树中，左子树的节点的值小于根节点
        int i=0;
        for(;i<sequence.length-1;i++){
            if(sequence[i]>root){
                break;
            }
        }
        //在二叉搜索树中，右子树中的节点大于根节点
        int j=i;
        for(;j<sequence.length-1;j++){
            if(sequence[j]<root)
                return false;
        }
        boolean left=true;
        boolean right=true;
        //判断左子树是不是二叉搜索树
        if(i>0){
            left=VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));
        }
        //判断右子树是不是二叉搜索树
        if(i<sequence.length-1)
            right=VerifySquenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length-1));
        return (left&&right);

    }
}
```

# 24. 二叉树中和为某一值的路径
+ 题目描述
```
输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的
和为输入整数的所有路径。路径定义为从树的根结点开始往下一
直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，
数组长度大的数组靠前)
注意：
这里定义的路径是 从根节点开始，往下一直到叶节点所经过的节点，
所以必须到叶节点
```
+ 解法1,错误的自己的解法
+ 误以为是先根遍历？？？
```java
import java.util.ArrayList;
import java.util.Collections;

/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        findPath(root,pathList,path,target);
        return pathList;
    }

    public void findPath(TreeNode root,ArrayList<ArrayList<Integer>> pathList,ArrayList<Integer> path,int target){
        if(root==null){
            return;
        }
        path.add(root.val);

        int sum = 0;
        for(int i=0;i<path.size();i++){
            sum += path.get(i);
            if(sum==target){
                pathList.add(path);
                path = new ArrayList<>();
                return;
            }
        }

        findPath(root.left,pathList,path,target);
        findPath(root.right,pathList,path,target);

    }
}
```
+ 正确解法
```java
import java.util.*;
public class Solution {
    // 全局变量，用于存储得到的每一个路径
    ArrayList<ArrayList<Integer>> resultsList = new ArrayList<ArrayList<Integer>>();

    /**
     * 建立额外一个函数，用来实现递归求解
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<Integer> pathList = new ArrayList<Integer>();
        if (root == null) {
            return resultsList;
        }

        int curSum = 0;
        int index = 0;
        int []path = new int[1000];
        this.isTargetPath(root, target, curSum, path, index);

        return this.resultsList;
    }

    /**
     * 递归求解函数
     * 思路很明白，把根节点到叶节点的路径上的值都加起来，
     * 所以在递归的过程中，需要逐个累加，并且累加的同时，还要将沿途经过的节点值记录下来，
     * 如何在递归函数中实现这一切功能呢？参数！！可用传参的方式解决
     *
     * @param eleNode   当前节点
     * @param target    目标和
     * @param curSum    当前已经累积到的和
     * @param path  记录到当前的节点位置，经过的路径
     * @param index 从根节点到当前节点为止，存的节点的数目
     */
    public void isTargetPath(TreeNode eleNode, int target, int curSum, int []path, int index) {
        if (eleNode == null) {
            return;
        }

        curSum += eleNode.val;
        // 把该节点包含进去
        path[index] = eleNode.val;
        index ++;

        // 当前已经是处于叶子节点，并且累计的和与target相等
        if (curSum == target && eleNode.left == null && eleNode.right == null) {
            // 将得到的结果放在外层结构中
            ArrayList<Integer> pathList = new ArrayList<Integer>();
            for (int i = 0; i < index; i++) {
                pathList.add(path[i]);
            }
            resultsList.add(pathList);
            return;
        }

        // 该节点有左子节点，前提还是要curSum 小于 target，否则递归就没有意义了
        if (curSum < target && eleNode.left != null) {
            this.isTargetPath(eleNode.left, target, curSum, path, index);
        }

        // 右子节点
        if (curSum < target && eleNode.right != null) {
            this.isTargetPath(eleNode.right, target, curSum, path, index);
        }
    }
}
```

# 25. 复杂链表的复制
+ 题目描述
```
输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
```
+ 解法1
+ 解法1与题意不符，题目中任意一个节点是指链表中的任意一个节点，
不是new一个任意节点
```java
public class Solution {
    public RandomListNode Clone(RandomListNode pHead){
        if(pHead==null){
            return null;
        }
        //增加一个虚拟的头节点
        RandomListNode newHead = new RandomListNode(pHead.label);
        //遍历原链表的指针
        RandomListNode ptr = pHead;
        //新链表的指针
        RandomListNode newPtr = newHead;
        while (ptr!=null){
            //首先复制next节点
            RandomListNode tmp = new RandomListNode(ptr.label);
            newPtr.next = tmp;
            //注意random是next节点的random
            if(ptr.random!=null){
                RandomListNode tmp2 = new RandomListNode(ptr.random.label);
                newPtr.next.random = tmp2;
            }
            //两个指针后移
            ptr = ptr.next;
            newPtr = newPtr.next;
        }
        return newHead.next;
    }
}
```
+ 解法2
```java

import java.util.*;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Solution {
    public RandomListNode Clone(RandomListNode pHead){
        if(pHead==null){
            return null;
        }
        RandomListNode newHead = new RandomListNode(pHead.label);
        RandomListNode ptr = pHead;
        RandomListNode newPtr = newHead;
        List<RandomListNode> list = new ArrayList<>();
        List<RandomListNode> list2 = new ArrayList<>();

        //首先复制
        Map<Integer,Integer> map = new HashMap<>();
        while (ptr!=null){
            RandomListNode tmp = new RandomListNode(ptr.label);
            list.add(ptr);
            newPtr.next = tmp;
            ptr = ptr.next;
            newPtr = newPtr.next;
            list2.add(newPtr);
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).random!=null){
                map.put(i,list.indexOf(list.get(i).random));
            }
        }

        for(int i=0;i<list.size();i++){
            if(map.containsKey(i)){
                list2.get(i).random = list2.get(map.get(i));
            }
        }
        return newHead.next;
    }
}

class MainClass {
    public static void main(String[] args){
        RandomListNode randomListNode = new RandomListNode(1);
        List<RandomListNode> test = new ArrayList<>();
        test.add(randomListNode);
        System.out.println(randomListNode==test.get(0));//true
        System.out.println(randomListNode.equals(test.get(0)));//true
    }
}
```

# 26. 二叉搜索树与双向链表
+ 题目描述
```
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
要求不能创建任何新的节点，只能调整树中结点指针的指向
```
+ 解法1,使用非递归中序遍历
```java
import java.util.Stack;

public class Solution{
    /*
     解法1，非递归的中序遍历
     修改当前结点与前一遍历结点的指针指向
     */
    public TreeNode Convert(TreeNode pRootOfTree){
        if(pRootOfTree==null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = pRootOfTree;
        TreeNode pre = null;//保存中序遍历序列的上一结点
        boolean isFirst = true;

        while(ptr!=null||!stack.isEmpty()){
            while (ptr!=null){
                stack.push(ptr);
                ptr = ptr.left;
            }
            ptr = stack.pop();
            if(isFirst){
                pRootOfTree = ptr;//将中序遍历序列中的第一个节点记为root
                pre = pRootOfTree;
                isFirst = false;
            }else{
                pre.right = ptr;    //上一个节点的右子树链接到当前结点
                ptr.left = pre;     //当前结点的左链连接到上一个节点
                pre = ptr;          //pre当前结点
            }
            ptr = ptr.right;
        }
        return pRootOfTree;
    }
}
```
+ 解法2，递归方法
    + 1.将左子树构造成双链表，并返回链表头节点。
    + 2.定位至左子树双链表最后一个节点。
    + 3.如果左子树链表不为空的话，将当前root追加到左子树链表。
    + 4.将右子树构造成双链表，并返回链表头节点。
    + 5.如果右子树链表不为空的话，将该链表追加到root节点之后。
    + 6.根据左子树链表是否为空确定返回的节点。
```java
public class Solution{
    public TreeNode Convert(TreeNode root) {
        if(root==null)
            return null;
        if(root.left==null&&root.right==null)
            return root;
        // 1.将左子树构造成双链表，并返回链表头节点
        TreeNode left = Convert(root.left);
        TreeNode p = left;
        // 2.定位至左子树双链表最后一个节点
        while(p!=null&&p.right!=null){
            p = p.right;
        }
        // 3.如果左子树链表不为空的话，将当前root追加到左子树链表
        if(left!=null){
            p.right = root;
            root.left = p;
        }
        // 4.将右子树构造成双链表，并返回链表头节点
        TreeNode right = Convert(root.right);
        // 5.如果右子树链表不为空的话，将该链表追加到root节点之后
        if(right!=null){
            right.left = root;
            root.right = right;
        }
        return left!=null?left:root;       
    }
}
```
# 27. 字符串的排列
#### 遗留问题，见？？？处
+ 题目描述
```
输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
输入描述:
输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
```
+ 解法1，有重复的结果，不通过(通过率30%)
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Solution {
    public ArrayList<String> Permutation(String str) {

        ArrayList<String> result = new ArrayList<>();
        Set<String> resultSet = new LinkedHashSet<>();

        if(str==null||str.isEmpty()|str.length()==0){
            return result;
        }

        if(str.length()==1){
            result.add(str);
            return result;
        }

        char[] charArray = str.toCharArray();

        Arrays.sort(charArray);

        buildResult(resultSet,"",charArray,0,charArray.length);

        result.addAll(resultSet);

        return result;
    }

    private void buildResult(Set<String> result,String tmp,char[] charArray, int index, int length) {

        if(tmp.length()==length){
            result.add(tmp);
        }else{
            for(int i=0;i<length;i++){
                if(index!=0){
                    if(i!=index){
                        buildResult(result,tmp+charArray[i],charArray,index+1,length);
                    }
                }else{
                    buildResult(result,tmp+charArray[i],charArray,index+1,length);
                }
            }
        }
    }
}
```
+ 解法2,正解
```java
import java.util.ArrayList;
import java.util.Collections;
public class Solution
{
    public ArrayList<String> Permutation(String str)
    {
        ArrayList<String> res=new ArrayList<String>();
        if(str.length()==0||str==null)return res;
        int n= str.length();
        helper(res,0,str.toCharArray());
        Collections.sort(res);
        return res;
         
    }
    public void helper( ArrayList<String> res,int index,char []s)
    {
        if(index==s.length-1)res.add(new String(s));
        for(int i=index;i<s.length;i++)
        {
            if(i==index||s[index]!=s[i])
            {
                //为什么这里要有连个swap???
                swap(s,index,i);
                helper(res,index+1,s);
                swap(s,index,i);
            }
        }
         
    }
     
    public void swap(char[]t,int i,int j)
     {
        char c=t[i];
        t[i]=t[j];
        t[j]=c;
    }
}
```
+ 解法3
```java
import java.util.*;
 
public class Solution {
    public ArrayList<String> Permutation(String str) {
 
        ArrayList<String> result = new ArrayList<>();
 
        if(str==null||str.isEmpty()|str.length()==0){
            return result;
        }
 
        char[] charArray = str.toCharArray();
        TreeSet<String> temp = new TreeSet();
 
        Permutation(charArray,0,temp);
        result.addAll(temp);
        return result;
    }
 
    private void Permutation(char[] charArray,int begin,TreeSet result) {
        if(charArray==null||charArray.length==0||begin<0||begin>charArray.length-1){
            return;
        }
 
        if(begin==charArray.length-1){
            result.add(String.valueOf(charArray));
        }else{
            for(int i=begin;i<=charArray.length-1;i++){
                swap(charArray,begin,i);
                Permutation(charArray,begin+1,result);
                swap(charArray,begin,i);
            }
        }
 
    }
 
    private void swap(char[] charArray, int a, int b) {
        char t = charArray[a];
        charArray[a] = charArray[b];
        charArray[b] = t;
    }
}
```
![1](https://note.youdao.com/yws/api/personal/file/D9EDE7094E1848F3A92FB23BCDC6AFBB?method=download&shareKey=f08c61375d4ca969fcca03ad93611e62)

# 28. 数组中出现次数超过一半的数字
+ 题目描述
```
数组中有一个数字出现的次数超过数组长度的一半，
请找出这个数字。例如输入一个长度为9的数组
{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
超过数组长度的一半，因此输出2。如果不存在则输出0。
```
+ 解法1，map法，19ms
```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int result = 0;

        if(array==null||array.length==0){
            return result;
        }

        Map<Integer,Integer> countMap = new HashMap<>();

        for(int i=0;i<array.length;i++){
            if(countMap.containsKey(array[i])){
                countMap.put(array[i],countMap.get(array[i])+1);
            }else{
                countMap.put(array[i],1);
            }
        }

        for(int item : countMap.keySet()){
            if(countMap.get(item)>array.length/2){
                result = item;
            }
        }

        return result;
    }
}
```

+ 解法2,19ms,另一种计数方法，书中推荐做法
```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int result = 0;

        if(array==null||array.length==0){
            return result;
        }

        result = array[0];
        int count = 0;

        for(int i=0;i<array.length;i++){
            if(array[i]==result){
                count++;
            }else{
                count--;
                if(count<=0){
                    result = array[i];
                    count=1;
                }
            }
        }
        count=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==result){
                count++;
            }
        }

        if(count<=array.length/2){
            result = 0;
        }

        return result;
    }
}
```
# 29. 最小的K个数
+ 题目描述
```
输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
```
+ 解法1 ，使用快速排序
```java
import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();

        //如果k > 输入数组的长度。返回所有元素还是返回空？
        if(k>input.length){
            return result;
        }

        quickSort(input,0,input.length-1);

        for(int i=0;i<k;i++){
            result.add(input[i]);
        }

        return result;
    }

    //快速排序
    static void quickSort(int[] input,int left,int right){
        if(left>=right){
            return;
        }

        int p = input[left];

        int i = left;
        int j = right;

        while (i<j){
            while(input[j]>=p&&i<j){
                j--;
            }
            while(input[i]<=p&&i<j){
                i++;
            }

            if(i<j){
                int tmp = input[i];
                input[i] = input[j];
                input[j] = tmp;
            }
        }

        input[left] = input[i];
        input[i] = p;
        quickSort(input,left,i-1);
        quickSort(input,i+1,right);
    }
}
```
# 30. 连续子数组的最大和
+ 题目描述
```
HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,
他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,
是否应该包含某个负数,并期望旁边的正数会弥补它呢？
例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,
到第3个为止)。给一个数组，返回它的最大连续子序列的和，
你会不会被他忽悠住？(子向量的长度至少是1)
```
+ 解法1
```java

```