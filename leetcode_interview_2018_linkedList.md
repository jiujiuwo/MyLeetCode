# 链表相关问题
## 1. 翻转链表
```
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
```
#### 解法1，遍历两边链表，保存第一遍遍历的值
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
```
#### 解法3 递归
```java

```

## 回文链表
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
