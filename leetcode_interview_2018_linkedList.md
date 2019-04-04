# 链表相关问题
# 1. 翻转链表
```
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
```
#### 解法1，遍历两边链表，保存第一遍遍历的值(错误解法)
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
import java.util.ArrayList;
import java.util.List;


class Solution {
    public ListNode reverseList(ListNode head) {
        List<Integer> value = new ArrayList<>();

        ListNode ptr = head;

        while (ptr!=null){
            value.add(ptr.val);
            ptr = ptr.next;
        }
        ptr = head;
        while(ptr!=null){
            ptr.val = value.get(value.size()-1);
            value.remove(value.size()-1);
            ptr = ptr.next;
        }

        return head;
    }
}
```
####  解法2 头插法
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; //前指针节点
        ListNode curr = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode nextTemp = curr.next; //临时节点，暂存当前节点的下一节点，用于后移
            curr.next = prev; //将当前节点指向它前面的节点
            prev = curr; //前指针后移
            curr = nextTemp; //当前指针后移
        }
        return prev;
    }
}

class Solution {
    public ListNode reverseList(ListNode head) {

        if(head==null){
            return null;
        }

        ListNode pre = head;
        ListNode next = head.next;
        head.next = null;

        while(next!=null){
            ListNode tmp = next.next;
            next.next = pre;
            //头插法，每次插入玩移动头
            pre = next;
            next = tmp;
        }

        return pre;
    }
}

```
#### 解法3 递归
```java

```

# 2. 回文链表
```
请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
```
+ 其一，find mid node 使用快慢指针找到链表中点。 
+ 其二，reverse 逆序后半部分。 其三，check 从头、中点，开始比较是否相同。
#### 解法1，使用栈或者数组存储下来值，作比较,空间复杂度为O(n)
```java
class Solution {
    public boolean isPalindrome(ListNode head) {

        ListNode ptr = head;
        List<Integer> sequence = new ArrayList<>();

        while (ptr!=null){
            sequence.add(ptr.val);
            ptr = ptr.next;
        }

        for(int i=0;i<sequence.size()/2;i++){
            //注意，这里不能使用!=符号比较两个get后的对象，因为比较的是地址
            if(!sequence.get(i).equals(sequence.get(sequence.size()-i-1))){
                return false;
            }
        }

        return true;
    }
}

```
#### 解法2 ，快慢指针
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        // 要实现 O(n) 的时间复杂度和 O(1) 的空间复杂度，需要翻转后半部分
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 根据快慢指针，找到链表的中点
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow.next);
        while(slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head){
        // 递归到最后一个节点，返回新的新的头结点
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```
# 翻转链表II leetcode-92
+ 题目描述
```
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
```
+ 解法
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        
        for(int i=1;i<m;i++){
            pre = pre.next;
            cur = cur.next;
        }
        
        for(int i=0;i<n-m;i++){
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = pre.next;
            pre.next = tmp;
        }
        return dummy.next;
    }
}
```

# 3. k个一组翻转链表
+ 题目描述
```
给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

示例 :

给定这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

说明 :

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
```

### 解法1
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null||k<2) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode tail = dummy;
        ListNode cur;
        int n;
        while(true) {
            n = k;
            while(n>0 && tail != null) {
                n--;
                tail = tail.next;
            }
            if(tail == null)
                break;
            head = prev.next;
            while(prev.next != tail) {
                cur = prev.next;// 保存待处理的节点
                prev.next = cur.next;// 断开prev与待处理节点的连接
                cur.next = tail.next;//2步完成头插法 a. 将待处理节点尾部接上tail之后的节点
                tail.next = cur;//b.tail接上待处理的节点
            }
            tail = head;
            prev = head;
        }
        return dummy.next;
    }
}
```