# 31. 

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