## 21. Merge Two Sorted Lists
+ java解法
+ Runtime: 8 ms, faster than 74.95% of Java online submissions for Merge Two Sorted Lists.
```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        while (ptr1!=null||ptr2!=null){
            if(ptr1!=null&&ptr2!=null){
                if(ptr1.val>ptr2.val){
                    ptr.next = new ListNode(ptr2.val);
                    ptr2 = ptr2.next;
                    ptr = ptr.next;
                }else{
                    ptr.next = new ListNode(ptr1.val);
                    ptr1 = ptr1.next;
                    ptr = ptr.next;
                }
            }else if(ptr1!=null&&ptr2==null){
                ptr.next = new ListNode(ptr1.val);
                ptr1 = ptr1.next;
                ptr = ptr.next;
            }else if(ptr1==null&&ptr2!=null){
                ptr.next = new ListNode(ptr2.val);
                ptr2 = ptr2.next;
                ptr = ptr.next;
            }
        }
        head = head.next;

    return head;
    }
}
```

## 22. Generate Parentheses *****
+ 没有自己的思路，想到了暴力破解法，但是，怎么样列举出所有的情况还是没有想出来

+ java，暴力破解法
+ Runtime: 3 ms, faster than 32.23% of Java online submissions for Generate Parentheses.
+ 思路
    + 生成所有的 2<sup>2n</sup>个的'('和 ')'字符的序列，然后检查每一个是否有效
    + 为了产生所有的序列，我们使用递归来实现。所有的长度为 n 的序列即 '('加上所有的长度为n-1
    的子序列，以及')'加上所有长度为n-1的序列。
    + 判断是否有效，使用开括号的数量减去闭括号的数量，如果不为0，则无效
+ 时间复杂度 O( 2<sup>2n</sup>*n)
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        //递归函数
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    /*
        current用来保存生成的字符串，初始为空串，然后依次分别向后添加'('和')'
        pos表示当前生成的字符数量，
        combinations用来保存结果
     */
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {    //如果生成的字符串的长度已经和输入中的相等，那么判断现在生成的字符串是否有效，有效的话，添加到结果集中
            if (valid(current)){
                result.add(new String(current));
            }
        } else {
            //生成左括号的字符，追加到当前字符然后递归调用
            current[pos] = '(';
            generateAll(current, pos+1, result);
            //其实感觉下面这一步不必要，以 ')'开头的肯定不对
             //不对，下面这一步也很必要，只是在pos=0的时候不必要，pos=!0时这一步往后加）
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    //判断是否有效，感觉可以判断那个栈解决的问题啊，20题
    public boolean valid(char[] current) {
        System.out.println(current);
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;  //这里，如果balance <0的话会返回false.也就是说。）开头的都会返回false
        }
        return (balance == 0);
    }
}
```
+ java,回溯法
+ 思路
    + 和方法一种，我们每次都添加 '(' 或 ')' 不同，我们只在确认它有效的情况下才添加它，例如，")"在
    第一个位置时，肯定无效
+ Runtime: 3 ms, faster than 32.23% of Java online submissions for Generate Parentheses.
+ 时间复杂度
```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}
```

+ java,闭包数
+ 思路
    + 为了枚举某些东西，通常我们想把它表示为更容易计数的不相交子集的和。考虑一个有效的括号
    序列的闭包数，假设序列S是有效的，则最后一个元素的index>=0,s[0],s[1],...s[2*index+1]是有效的
    + 显然，每个括号序列都有惟一的闭包号。我们可以试着把它们一一列举出来。
    + 对于一个闭包数 c,我们知道，开始和结束的括号必须在0和2*c+1,中间的2c个元素必须是有效的
    序列，加上剩下的元素也肯定是一个有效的额序列
 ```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}
```
## 23. Merge k Sorted Lists ****
+ java,最简单的做法，先收集数字，然后排序，然后构建结果
+ Your runtime beats 24.69 % of java submissions
```java
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists==null||lists.length==0){
            return null;
        }

        List<Integer> nums = new LinkedList<>();

        for(int i=0;i <lists.length;i++){
            ListNode ptr = lists[i];
            while (ptr!=null){
                nums.add(ptr.val);
                ptr = ptr.next;
            }
        }
        Collections.sort(nums);
        if(nums.size()>0){
            ListNode head = new ListNode(nums.get(0));
            ListNode ptr = head;

            for(int i=1;i<nums.size();i++){
                ptr.next = new ListNode(nums.get(i));
                ptr = ptr.next;
            }

            return head;
        }else{
            return null;
        }
    }
}
```
+ java,0(N<sup>2</sup>)做法，多次调用mergeTwoList
+ 超时,130 / 131 test cases passed.
```java
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode newList = null;

        if(lists.length==1){
            return lists[0];
        }

        for(int i=0;i < lists.length-1;i++){
            newList = mergeTwoLists(lists[i],lists[i+1]);
            if(i+1 < lists.length){
                lists[i+1] = newList;
            }
        }
        return newList;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        while (ptr1!=null||ptr2!=null){
            if(ptr1!=null&&ptr2!=null){
                if(ptr1.val>ptr2.val){
                    ptr.next = new ListNode(ptr2.val);
                    ptr2 = ptr2.next;
                    ptr = ptr.next;
                }else{
                    ptr.next = new ListNode(ptr1.val);
                    ptr1 = ptr1.next;
                    ptr = ptr.next;
                }
            }else if(ptr1!=null&&ptr2==null){
                ptr.next = new ListNode(ptr1.val);
                ptr1 = ptr1.next;
                ptr = ptr.next;
            }else if(ptr1==null&&ptr2!=null){
                ptr.next = new ListNode(ptr2.val);
                ptr2 = ptr2.next;
                ptr = ptr.next;
            }
        }
        head = head.next;

        return head;
    }
}
```
#### 后续尝试更多的做法

## 24. Swap Nodes in Pairs
+ java,按照下标的奇偶来交换元素
+ Runtime: 2 ms, faster than 99.98% of Java online submissions for Swap Nodes in Pairs.
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
    public ListNode swapPairs(ListNode head) {

        if(head==null){
            return head;
        }

        ListNode ptr=head;
        int index =0;
        while (ptr!=null){
            if(index % 2 == 0){
                if(ptr.next!=null){
                    int tmp = ptr.val;
                    ptr.val = ptr.next.val;
                    ptr.next.val = tmp;
                }
                ptr = ptr.next;
            }else{
                ptr = ptr.next;
            }
            index++;
        }

        return head;
    }
}
```
## 25. Reverse Nodes in k-Group
+ java，自己熬了两天写出来的破方法，也不知道叫啥方法
+ Runtime: 7 ms, faster than 15.88% of Java online submissions for Reverse Nodes in k-Group.
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode headPtr = head;
        ListNode result = null;
        ListNode resultPtr = null;

        //检查特殊的输入，例如，整个list的长度不足k,或者k<2则直接返回原list
        int length = 0;
        while (headPtr!=null){
            length++;
            headPtr = headPtr.next;
        }
        if(length<k||k<2){
            return head;
        }

        headPtr = head;

        while (headPtr != null) {
            //每k个节点转换一次，每次转化完的新数组，返回下一组的首地址
            ListNode[] results = reverseGroup(result,headPtr,k);
            result = results[0];
            headPtr = results[1];
        }

        return result;
    }
    /*
        result 为结果数组
        headPtr 为当前位置的指针
        k 为原输入中的k
     */
    private ListNode[] reverseGroup(ListNode result,ListNode headPtr,int k) {
        //nums用来存放带逆转的节点值
        ListNode[] newNodes = new ListNode[k];

        //将新节点进行串联
        for (int i = 0; i < k; i++) {
            newNodes[i] = new ListNode(0);
        }
        for (int i = 0; i < k - 1; i++) {
            newNodes[i].next = newNodes[i + 1];
        }
        //一开始，它指向原来的数组，后来，它指向新的数组
        ListNode tmpHead;
        ListNode pre = null;
        if(result==null){
             tmpHead = headPtr;
        }else{
            //要改变result后面的元素，因此要定位到最后的位置
            pre = headPtr;
            tmpHead = headPtr.next;
        }
        int index = k - 1;

        while (tmpHead != null) {
            //倒序开始给新的list赋值
            newNodes[index].val = tmpHead.val;
            index--;
            //指针向后移1
            tmpHead = tmpHead.next;
            //如果index减小到了0以下
            if (index < 0) {    //如果index减小到了0，说明这组可以转换，返回的头信心指向下一个
                if(result==null){
                    result = newNodes[0];
                }else{
                   pre.next = newNodes[0];
                }
                newNodes[k - 1].next = tmpHead;
                headPtr =  newNodes[k - 1];
                break;
            }
        }

        if(index>=0){
            headPtr = tmpHead;
        }

        newNodes[0] = result;
        newNodes[1] = headPtr;
        return newNodes;
    }
}

```

## 26. Remove Duplicates from Sorted Array
+ java,O(n<sup>2</sup>)做法,很慢，不能有其他的空间申请，想不出其他的办法了
+ Runtime: 99 ms, faster than 2.80% of Java online submissions for Remove Duplicates from Sorted Array.
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        for(int i=0,index=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1]){
                //int tmp = nums[nums.length-1];
                for(int j=i;j<nums.length-1;j++){
                    nums[j] = nums[j+1];
                }
                i--;
                len--;
                //nums[nums.length-1] = tmp;
            }
            index++;
            if(index==nums.length-1){
                break;
            }
        }

        return len;
    }
}
```
+ 双指针做法,唉能想出这种方法的人，我真的是自愧不如啊
+ Runtime: 9 ms, faster than 49.13% of Java online submissions for 
Remove Duplicates from Sorted Array.
+ 思路
    + 因为数组已经是有序的了，我们使用两个指针 i和j,i是走的慢的指针，j是走的快的指针
    只要nums[i]==nums[j],我们就增加j,跳过重复的元素
    + 当遇到nums[i]!=nums[j]时，重复运行已经结束，我们需要把nums[j]的值赋给nums[i+1],
    然后增加i,进行相同的处理，直到j走到最后
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
```

## 27. Remove Element
+ java,O(n) 普通做法，题目虽然简单，但是需要注意的地方很多。详细见注释
+ Runtime: 8 ms, faster than 19.18% of Java online submissions for Remove Element.
```java
class Solution {
    public int removeElement(int[] nums, int val) {

        if(nums.length==0){
            return 0;
        }

        int count = 0;
        //一开始 i<j,导致如果输入[4,5]的话，因为i<j所以 i不会等于1，导致错误
        for(int i=0,j=nums.length-1;i<=j;i++){
            if(nums[i] == val){
                int tmp = nums[i];
                //一开始缺少条件j<i导致越界错误
                while(j>i&&nums[j]==val){
                    count++;
                    j--;
                }
                nums[i] = nums[j];
                nums[j] = tmp;
                j--;
                count++;
            }
        }
        return nums.length-count;
    }
}
```
+ java,O(N),双指针做法
+ Runtime: 6 ms, faster than 46.80% of Java online submissions for Remove Element.
```java
class Solution {
    public int removeElement(int[] nums, int val) {

        int i =0;
        //只取前几个，不关心后面的值，所以可行
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }
}
```

## 28. Implement strStr()
+ java,直接调用String的IndexOf方法
+ 2ms,Your runtime beats 100.00 % of java submissions.
```java
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```
+ java，使用String 的subString和equals方法
+ 3ms,Your runtime beats 99.64 % of java submissions.
```java
class Solution {
    public int strStr(String haystack, String needle) {

        //注意这里是 <=
        for(int i=0;i<=haystack.length()-needle.length();i++){
            //System.out.println(haystack.substring(i,i+needle.length()));
            if(haystack.substring(i,i+needle.length()).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}
```

## 29. Divide Two Integers
+ java,题目不难，但是超时，使用加法求解的话，除数小时太耗时
+ 正确的做法好像是使用 位操作，位运算
```java
class Solution {
    public int divide(int dividend, int divisor) {

        long  beichushu = dividend;
        long  chushu = divisor;
        int sign = 1;
        long result = 0;

        if(beichushu<0){
            //这里，如果输入是-2147483648，则取反以后，超过了int负数范围，造成dividend依然是负数
            beichushu = -beichushu;
            sign = sign * -1;
        }

        if(divisor<0){
            chushu = -chushu;
            sign = sign*-1;
        }

        int tmp = 0;

        if(chushu==1){
            if(sign*beichushu>=Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(sign*beichushu<=Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }else{
                return (int)beichushu*sign;
            }
        }

        //当输入   -2147483648，-1时，超时，用时太长。
        while (tmp + chushu<=beichushu){
            tmp += chushu;
            result++;
            //System.out.println(result);
            if(result >= Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
        }

        return (int)result*sign;
    }
}
```

## 35. Search Insert Position
+ java做法，复杂度O(N),
+ Runtime: 3 ms, faster than 74.98% of Java online submissions for Search Insert Position.
```java
class Solution {
    public int searchInsert(int[] nums, int target) {

        int result = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=target){
                return i;
            }
        }

        return nums.length;
    }
}
```