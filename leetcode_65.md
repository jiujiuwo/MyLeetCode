## 647. 回文子串
###题目描述
```
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:
输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
示例 2:
输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
注意:

输入的字符串长度不会超过1000。
```
+ 扩展中心解法
```java
class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        for(int i=0;i<s.length();i++){
            result += expandCenter(s,i,i);
            result += expandCenter(s,i,i+1);
        }
        return result;
    }
    
    public int expandCenter(String tmp,int start,int end){
        int count = 0;
        while(start>=0&&end<tmp.length()&&tmp.charAt(start)==tmp.charAt(end)){
            count++;
            start--;
            end++;
        }
        return count;
    }
}
```