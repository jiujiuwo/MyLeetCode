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