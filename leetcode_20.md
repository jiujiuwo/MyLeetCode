# 199. 二叉树的右视图
+ 题目描述
```
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

```
+ 解法1
```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return  result;
        }
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        int size=1;
        while(!nodes.isEmpty()){
            result.add(nodes.get(nodes.size()-1).val);
            int newSize = 0;
            for(int i=0;i<size;i++){
                TreeNode node = nodes.get(i);
                if(node.left!=null){
                    nodes.add(node.left);
                    newSize++;
                }
                if(node.right!=null){
                    nodes.add(node.right);
                    newSize++;
                }
            }
            for(int i=0;i<size;i++){
                nodes.remove(0);
            }
            size = newSize;
        }
        return result;
    }
}
```