# 字符串相关问题
# 1. 验证回文串
```
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false
```
## 有下标越界错误的做法
```java
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        //System.out.println(s);
        int m =0,n = s.length()-1;

        //处理没有有效字符的情况
        //什么时候该s,length-1,什么时候不－1
        int tmp =0;
        for(int i=0;i<s.length();i++){
            if(!(s.charAt(i)>='0'&&s.charAt(i)<='9'||s.charAt(i)>='a'&&s.charAt(i)<='z')){
                s = s.replace(""+s.charAt(i),"*");
            }
        }
        s = s.replace("*","");


        if(s.isEmpty()||s.equals("")){
            return true;
        }

        if(s.equals("")||s.isEmpty()){
            return true;
        }

        System.out.println(s);

        while(m<n){
            //大于等于少写了等于，小于等于少写了等于
            while (!(s.charAt(m)>='0'&&s.charAt(m)<='9'||s.charAt(m)>='a'&&s.charAt(m)<='z')){
                m++;
                if(m>n){    //输入为a.的情况
                    return false;
                }
            }
            while(!(s.charAt(n)>='0'&&s.charAt(n)<='9'||s.charAt(n)>='a'&&s.charAt(n)<='z')){
                n--;
                if(n<m){
                    return false;
                }
            }
            //System.out.println(m +" "+ s.charAt(m)+","+n+" "+s.charAt(n));
            if(s.charAt(m)!=s.charAt(n)){
                return false;
            }else{
                m++;
                //一开始这里写成了n++ 也没有发现
                n--;
            }
        }
        return true;
    }
}
```
## 第二种思路，先替换非法字符，然后做判断
+ leetcode上超时，475/476个通过测试用例
```java
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        //处理没有有效字符的情况
        //什么时候该s,length-1,什么时候不－1
        for(int i=0;i<s.length();i++){
            if(!(s.charAt(i)>='0'&&s.charAt(i)<='9'||s.charAt(i)>='a'&&s.charAt(i)<='z')){
                s = s.replace(""+s.charAt(i),"*");
            }
        }
        s = s.replace("*","");
        if(s.isEmpty()||s.equals("")){
            return true;
        }
       // System.out.println(s);

        for(int i=0;i< s.length()/2;i++){
            if(s.charAt(i)!=s.charAt(s.length()-1-i)){
                return false;
            }else{
                continue;
            }
        }

        return true;
    }
}
```
## 上面的方法，超时的解决方案，使用StringBuilder来存储去掉非法字符的字符串
```java
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder newString = new StringBuilder();
        //处理没有有效字符的情况
        //什么时候该s,length-1,什么时候不－1
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'||s.charAt(i)>='a'&&s.charAt(i)<='z'){
                newString.append(s.charAt(i));
            }
        }

        if(newString.toString().isEmpty()||newString.toString().equals("")){
            return true;
        }
       // System.out.println(s);

        for(int i=0;i< newString.length();i++){
            if(newString.charAt(i)!=newString.charAt(newString.length()-1-i)){
                return false;
            }else{
                continue;
            }
        }

        return true;
    }
}
```