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

## 22. Generate Parentheses
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