# 1. 二维数组中的查找
```
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序
，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
```
+ 解法思路
```java
public class Solution {
    public boolean Find(int target, int [][] array) {
        if(array.length ==0){
            return false;
        }
        int row = array.length-1;
        int column = 0;
        
        while(column < array[0].length&&row>=0){
            if(array[row][column] == target){
                return true;
            }else if(array[row][column] > target){
                row--;
            }else{
                column++;
            }
        }
        return false;
    }
}

```
# 2. 替换空格
```
请实现一个函数，将一个字符串中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
```
+ 解法
```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ","%20");
    }
}
```
# 3. 从尾到头遍历单向链表
```
输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
```
+ 解法
```java
class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Stack<Integer> listStack = new Stack<>();
        ListNode ptr = listNode;

        while(ptr!=null){
            listStack.push(ptr.val);
            ptr = ptr.next;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!listStack.isEmpty()){
            result.add(listStack.pop());
        }
        return result;
    }
}
```

# 4. 重建二叉树
```
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的
结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列
{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
```
+ 解法
```java
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre==null||in==null||pre.length!=in.length||pre.length==0){
            return null;
        }
        return construct(pre,0,pre.length-1,in,0,in.length-1);
    }
    
    public TreeNode construct(int[] pre,int ps,int pe,int[] in,int is,int ie){
        //开始位置大于结束位置，说明已经处理到叶节点，递归的结束条件
        if(ps>pe){
            return null;
        }
        //前序遍历的第一个结点为当前的根节点
        int value = pre[ps];
        //index为根节点在中序遍历中的索引
        int index = is;
        while(index<ie&&in[index]!=value){
            index++;
        }
        //构建当前结点
        TreeNode node = new TreeNode(value);
        //当前结点的左子树个数为index-is
        //左子树对应的前序遍历的位置在prep[ps+1,ps+index-is]
        //左子树对应的中序遍历的位置在in[is,index-1]
        node.left = construct(pre,ps+1,ps+index-is,in,is,index-1);
        //当前结点的右子树个数为ie-index
        //右子树的前序遍历位置在pre[ps+index-is+1,pe]
        //右子树对应的中序遍历位置为in[index+1,ie]
        node.right = construct(pre,ps+index-is+1,pe,in,index+1,ie);
        return node;
    }
}
```
# 5. 用两个栈实现队列
```
用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型
```
+ 解法1
```java
import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
``` 

# 6. 旋转数组的最小数字
```
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}
为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，
若数组大小为0，请返回0。
```
+ 解法1
```java
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if(array==null||array.length==0){
            return 0;
        }
        
        int indexF=0,indexE = array.length-1;
        
        while(indexF<indexE){
            if(array[indexF]<=array[indexF+1]){
                indexF++;
            }
            if(array[indexE]>=array[indexE-1]){
                indexE--;
            }
            //注意循环终止的条件
            if(indexF+1==indexE){
                break;
            }
        }
        //最后返回
        return array[indexE];
    }
}
```