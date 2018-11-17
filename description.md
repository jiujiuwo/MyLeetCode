## LeetCode刷题记录

#### 2018 11 13, add two numbers
```
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(l1.val + l2.val);
        ListNode ptr = head;
        //考虑 l1与l2不等长
        while (l1.next != null || l2.next != null) {
            if (l1.next != null) {
                l1 = l1.next;
            } else {
                l1.val = 0;
            }
            if (l2.next != null) {
                l2 = l2.next;
            } else {
                l2.val = 0;
            }
            ptr.next = new ListNode(l1.val + l2.val);
            ptr = ptr.next;
        }
        //指向结果链表，最后一个位置的指针
        ptr = head;
        while (ptr != null) {   //遍历结果链表，满10进1
            if (ptr.val >= 10) {
                ptr.val -= 10;
                if (ptr.next != null) {
                    ptr.next.val += 1;
                } else {
                    ptr.next = new ListNode(1);
                }
            }
            ptr = ptr.next;
        }
        return head;
    }
}
```

#### 2018 11 17 3,Longest Substring Without Repeating Characters
```
class Solution {
      public int lengthOfLongestSubstring(String s) {

        int maxLen = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i+1; j<= chars.length; j++) {
                //System.out.println(s.substring(i,j));
                int tmp = checkDulplicateAndLen(s.substring(i,j));
                if(tmp > maxLen){
                    maxLen = tmp;
                }
            }
        }
        return maxLen;
    }
    private int checkDulplicateAndLen(String substring) {
        int numChars = 0;
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i =0;i < substring.length();i++){
            if(charMap.containsKey(substring.charAt(i))){
                return 0;
            }else {
                charMap.put(substring.charAt(i),0);
                numChars++;
            }
        }
        return numChars;
    }
}


//time limit exceeded
```
