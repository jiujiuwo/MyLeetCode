# 31. 整数中1出现的次数（从1到n整数中1出现的次数）

# 32. 把数组排成最小的树
+ 题目描述
```
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
```
+ 解答
```java

```
# 33. 丑数

# 34. 第一次只出现一次的字符位置

# 35. 数组中的逆序对

# 36. 两个链表的第一个公共结点

# 37. 数字在排序数组中出现的次数

# 38. 二叉树的深度

# 39. 平衡二叉树
+ 题目描述
```
输入一棵二叉树，判断该二叉树是不是平衡二叉树
```
+ 解法1
```java

```

# 40.数组中只出现一次的数字
+ 题目描述
```
一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
```
+ 解法1，使用hashmap计数
```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        Map<Integer,Integer> countMap = new HashMap<>();
        for(int i=0;i<array.length;i++){
            if(countMap.containsKey(array[i])){
                countMap.put(array[i],countMap.get(array[i])+1);
            }else{
                countMap.put(array[i],1);
            }
        }
        int result[] = new int[2];
        int count = 0;
        for(int key : countMap.keySet()){
            if(countMap.get(key)==1){
                result[count]=key;
                count++;
            }
        }
        num1[0] = result[0];
        num2[0] = result[1];
    }
}
```