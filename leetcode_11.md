# 102. 二叉树的层次遍历
+ 题目描述
```
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

```
+ 解法1
```java
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        int size = 1;
        while (!list.isEmpty()) {
            int newSize = 0;
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                TreeNode node = list.get(i);
                tmp.add(node.val);
            }
            result.add(new ArrayList<>(tmp));
            tmp.clear();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.get(i);
                if (node.left != null) {
                    newSize++;
                    list.add(node.left);
                }
                if (node.right != null) {
                    newSize++;
                    list.add(node.right);
                }
            }
            for (int i = 0; i < size; i++) {
                list.remove(0);
            }
            size = newSize;
        }
        return result;
    }
}
```

# 107. 二叉树的层次遍历 II
+ 题目描述
```
给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]
```
+ 解法1
```java
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
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
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return result;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        int size = 1;
        while (!list.isEmpty()) {
            int newSize = 0;
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                TreeNode node = list.get(i);
                tmp.add(node.val);
            }
            result.add(new ArrayList<>(tmp));
            tmp.clear();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.get(i);
                if (node.left != null) {
                    newSize++;
                    list.add(node.left);
                }
                if (node.right != null) {
                    newSize++;
                    list.add(node.right);
                }
            }
            for (int i = 0; i < size; i++) {
                list.remove(0);
            }
            size = newSize;
        }
        List<List<Integer>> newResult = new ArrayList<>();
        for(int i=result.size()-1;i>=0;i--){
            newResult.add(result.get(i));
        }
        return newResult;
    }
}
```