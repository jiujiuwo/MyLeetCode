# 11. 二进制中1的个数
```
输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
```
+ 解法1，使用位运算法
+ 思路
+ 如果一个整数不为0，那么这个整数至少有一位是1。如果我们把这个整数减1，那么原来处在整数最右边的1就会变为0，原来在1后面
的所有的0都会变成1(如果最右边的1后面还有0的话)。其余所有位将不会受到影响。
+ 这个时候如果我们再把原来的整数和减去1之后的结果做与运算，从原来整数最右边
一个1那一位开始所有位都会变成0。如1100&1011=1000.也就是说，把一个整数减去1，
再和原整数做与运算，会把该整数最右边一个1变成0.那么一个整数的二进制有多少个1，
就可以进行多少次这样的操作。
```java
public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        while(n!= 0){
            count++;
            n = n & (n - 1);
         }
        return count;
    }
}

//下面的这种做法通过了44%的case,猜测int不是无符号的类型
public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while(flag!= 0){
            if((n&flag)>0){        //找到了原因，n有可能为负数，因此大于0不成立，改外！=0通过
                count++;
            }
            flag = flag << 1;
         }
        return count;
    }
}
```

+ 解法2,使用Integer的方法
```java
public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        char[] tmp = Integer.toBinaryString(n).toCharArray();
        for(int i=0;i<tmp.length;i++){
            if(tmp[i]=='1'){
                count++;
            }
        }
        return count;
    }
}
```

# 12. 数值的整数次方
```
给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
```
+ 注意exponent为负数的情况
```java
public class Solution {
    //注意边界条件，exponent为负数的情况
    public double Power(double base, int exponent) {
        
        double result = 1.0;
        
        if(exponent<0){
            int tmp = - exponent;
            //这里应该使用修改后的值
            for(int i=0;i<tmp;i++){
                result *= base;
            }
            result = 1/result;
        }else if(exponent>0){
             for(int i=0;i<exponent;i++){
                result *= base;
            }
        }else{
            return 1;
        }
        
        return result;
  }
}
```

# 13. 调整数组顺序使奇数位于偶数前面
+ 题目描述
```
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
```

+ 解法1
```java
public class Solution {
    public void reOrderArray(int [] array) {
        
        int count =0;
        //统计数组中偶数元素的个数，为下面的循环终止提供条件
        for(int i=0;i<array.length;i++){
            if(array[i] % 2 == 0){
                count++;
            }
        }
        
        int count2 = 0;
        for(int i=0;i<array.length;i++){
            if(array[i] % 2 == 0&&count2<count){
                int tmp = array[i];
                for(int j=i;j<array.length-1;j++){
                    array[j] = array[j+1];
                }
                array[array.length-1] = tmp;
                count2++;
                //将i后的元素前移，并且从当前元素开始重新统计
                i--;
            }
        }
    }
}
```
+ 解法2，书上的解法，不能满足偶数间的相对位置不变
```java
public class Solution {
    public void reOrderArray(int [] array) {
        int start = 0;
        int end = array.length -1;
        
        while(start < end){
            while(start<end&&array[start]%2==1){
                start++;
            }
            while(start<end&&array[end]%2==0){
                end--;
            }
            if(start<end){
                int tmp = array[start];
                array[start] = array[end];
                array[end] = tmp;
            }
        }
    }
}
```

# 14. 链表中倒数第k个结点
+ 题目描述
```
输入一个链表，输出该链表中倒数第k个结点。
```
+ 解法1，注意边界条件
```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        //注意head是否为空
        if(head==null){
            return null;
        }
        ListNode ptr = head;
        int count = 0;
        while(ptr != null){
            count++;
            ptr = ptr.next;
        }
        
        //注意k是否小于结点总数
        if(k>count){
            return null;
        }else{
            ptr = head;
            for(int i=0;i<count-k;i++){
                ptr = ptr.next;
            }
        }
        return ptr;
    }
}
```

# 15. 合并两个排序的链表
+ 题目描述
```
输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
```
+ 解法1
````java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode ptr = head;
        while(list1!=null||list2!=null){
            if(list1!=null&&list2!=null){
                if(list1.val>list2.val){
                    //取了某一个链表的值，将该链表的指针后移
                    ptr.next = new ListNode(list2.val);
                    ptr = ptr.next;
                    list2 = list2.next;
                }else{
                    ptr.next = new ListNode(list1.val);
                    ptr = ptr.next;
                    list1 = list1.next;
                }
            }else if(list1!=null&&list2==null){
                ptr.next = new ListNode(list1.val);
                ptr = ptr.next;
                list1 = list1.next;
            }else if(list1==null&&list2!=null){
                ptr.next = new ListNode(list2.val);
                ptr = ptr.next;
                list2 = list2.next;
            }
        }
        return head.next;
    }
}
````