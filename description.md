## LeetCode刷题记录

#### 2.add two numbers  2018/11/13  
+ java解法，难点在 l1,l2 有没有下一个值的判断
```java
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

#### 3.Longest Substring Without Repeating Characters 2018/11/17  
+ java 超时解,时间复杂度太大，O(N<sup>3</sup>)
```java
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
+ Go语言正解,时间复杂度 O(N),12ms
```go
func lengthOfLongestSubstring(s string) int {
    //记录每个字符最后出现的位置
	start := 0
	maxLength := 0
	lastOccured :=make(map[byte]int)
	for i,ch := range []byte(s){

		lastIndex,ok := lastOccured[ch]

		if ok && lastIndex >= start{
			start = lastIndex + 1
		}
		if i - start + 1 > maxLength{
			maxLength = i - start + 1
		}
		lastOccured[ch] = i
	}

	return maxLength
}
```
+ java O(N)写法,35ms
```java
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int lengthOfLongestSubstring(String s) {

        int maxLen = 0;
        int startIndex =0;

        Map<Character,Integer> lastOccuredMap = new HashMap<>();

        for(int i =0;i < s.length();i++){
            char tmp = s.charAt(i);
          //  System.out.println(tmp);

            //一开始忘记 添加 lastOccuredMap.get(tmp)>=startIndex 这个条件，导致回退过多。
            // 还是对算法的过程不熟悉，不是自己想的
            if(lastOccuredMap.containsKey(tmp)&&lastOccuredMap.get(tmp)>=startIndex){
                startIndex = lastOccuredMap.get(tmp) + 1;
            }

            if((i - startIndex + 1) > maxLen){
                maxLen = i - startIndex + 1;
            }
         //   System.out.println(i);

            lastOccuredMap.put(tmp,i);
        }

        return maxLen;
    }
}
```

#### 4. Median of Two Sorted Arrays  2018/11/18 &ensp; &ensp; &ensp;
+ java 25 ms,感觉题目不难，但是用时较长，之后要多练
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        double median = 0;
        int index = (len1+len2)/2;

        int newNums[] = new int[index+1];

        int m = 0,n = 0;
        for(int i =0;i <= index;i++){
            if(m  <nums1.length && n < nums2.length){
                if(nums1[m] > nums2[n]){
                    newNums[i] = nums2[n];
                    n++;
                }else{
                    newNums[i] = nums1[m];
                    m++;
                }
                continue;
            }
            if(m < nums1.length){
                newNums[i] = nums1[m];
                m++;
            }
            if(n < nums2.length){
                newNums[i] = nums2[n];
                n++;
            }
        }

        if((nums1.length + nums2.length) % 2 == 0){
            return (newNums[newNums.length-1] + newNums[newNums.length-2]) * 1.0 / 2;
        }else {
            return newNums[newNums.length-1];
        }

    }
}
```

+ Go语言版本，24ms,
```go
func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {

	index := (len(nums1)+len(nums2)) / 2
	//fmt.Println(index)

	var median float64 = 0

	newArray := make([]int,index+1)

	m,n := 0,0

	for i := 0;i <=index;i++{
		if m < len(nums1) && n < len(nums2){
			if nums1[m] > nums2[n]{
				newArray[i] = nums2[n]
				n++
			}else{
				newArray[i] = nums1[m]
				m++
			}
			continue
		}

		if(m < len(nums1)){
			newArray[i] = nums1[m]
			m++
		}
		if(n < len(nums2)){
			newArray[i] = nums2[n]
			n++
		}
	}

	//fmt.Println(newArray)

	if (len(nums2) + len(nums1)) % 2 == 0{
		median =  (float64(newArray[index] + newArray[index -1]) / 2)
	}else{
		median =  float64(newArray[index])
	}
	return median
}
```

#### 5.Longest Palindromic Substring（最长回文子串） 2018/11/19 
+ java 超时解，暴力解法，难点在回文串的判断
```java
class Solution {
    public String longestPalindrome(String s) {

        String result = "";
        String tmp ="";

        for(int i=0;i< s.length();i++){
            for (int j = s.length();j >=i;j--){
                if(checkPalindrome(s.substring(i,j))){
                    tmp = s.substring(i,j);
                    if(tmp.length()>result.length()){
                        result = tmp;
                    }
                }
            }
        }

        return result;
    }

    private boolean checkPalindrome(String substring) {
      //  System.out.println(substring);

        boolean isPalindrome = true;

        for(int i= 0; i < substring.length()/2;i++){
            if(substring.charAt(i)==substring.charAt(substring.length()-1-i)){
                continue;
            }else{
                isPalindrome = false;
            }
        }

        return isPalindrome;
    }
}
```
+ Go语言，暴力解法，ac,3856ms,beat1.63%
<br>思路和java超时的一样
```go

func longestPalindrome(s string) string {
	result := ""
	tmp := ""
	for i:= 0;i < len(s);i++{
		for j:=len(s);j >i;j--{
			tmp = s[i:j]
			if isPalindrome(tmp){
				if len(tmp) > len(result){
					result = tmp
				}
			}
		}
	}
	return result
}

func isPalindrome(s string) bool{

	var isPalindrome bool = true

	for i := 0;i < len(s)/2;i++{
		if s[i] == s[len(s)-i-1]{
			//fmt.Println(s[i],s[len(s)-i-1])
			continue
		}else{
			isPalindrome = false
		}
	}
	return isPalindrome
}
```

+ java语言，动态规划解法，148 ms,beat 16% ，
难点在dp[i][j]的理解和推导
```java
class Solution {
    public String longestPalindrome(String s) {

        if (s == null || "".equals(s)) {
            return s;
        }

        int len = s.length();

        String ans = "";
        int max = 0;

        boolean[][] dp = new boolean[len][len];

        for (int j = 0; j < len; j++) {

            for (int i = 0; i <= j; i++) {
                
                boolean judge = s.charAt(i) == s.charAt(j);

                dp[i][j] = j - i > 2 ? dp[i + 1][j - 1] && judge : judge;

                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
```
+ Go 语言，dp,94ms,beat 38%
```go
func longestPalindromeDp(s string) string {

	if len(s) == 0{
		return s
	}

	sLen := len(s)
	result := ""
	max := 0

	dp := make([][]bool,sLen)
	for i:=0; i< sLen;i++{
		dp[i] = make([]bool,sLen)
	}

	//动态规划，i,j分别代表什么？？？
	for i :=0 ;i < sLen; i++{
		for j := 0; j <= i; j++{	//遍历所有子串
			//fmt.Println(result,dp,max,i,j)
			//判断首尾两个字符是否相等
			judge := s[i] == s[j]
			fmt.Println(j,i)
			//动态规划递推式
			//dp[i][j] = (j - i > 2) ? (dp[i+1][j-1] && judge) :judge
			if i - j > 2{
				dp[j][i] = dp[j+1][i-1] && judge
			}else{
				dp[j][i] = judge
			}

			if dp[j][i] && ((i - j + 1) > max){
			//	fmt.Println(j,i,max)
				max = i - j + 1
				result = s[j:i+1]
			}
		}
	}
	fmt.Println(dp)
	return result
}
```
#### 6.ZigZag Conversion（Z型字母排列转换）  2018/11/21 
+ java语言，72ms,O(N<sup>2</sup>)，难点在精确的下标控制
```java
class Solution {
    public String convert(String s, int numRows) {

        String result = "";
        char[][] chars = new char[numRows][s.length()];
        boolean down = true;

        //一开始没有想到，行数为1的情况，导致编译错误。
        if(numRows <=1){
            return s;
        }

        //简单的题目做了一个多小时。。。惨
        for(int i=0,j =0,index =0;index < s.length();index++){
            chars[i][j] = s.charAt(index);
           // System.out.println(i+" "+j);
            if(down){
                i++;
            }else{
                i--;
                j++;
            }
           if(i==numRows){
                i -= 2;     //边界条件搞不清楚，这里，一开始写的 i--,结果发现不对
                j++;
                down = false;
           }
           if(i==0){
              // i++;           在向上走的时候，一直是i--,当i减到0时，
               down = true;
           }
        }

        for(int i = 0;i < numRows;i++){
            for(int j = 0; j < s.length();j++){
                if(chars[i][j]!='\0')
                result += chars[i][j];
            }
        }

        return result;
    }
}
```
#### 7.7. Reverse Integer 2018/11/22
+ java解法，24ms, beat 58%,难点在int最大值越界，溢出判断
```java
class Solution {
    public int reverse(int x) {

        long result = 0;

        //一开始没有看清题目的范围
        if(x >= Math.pow(2,31)-1 || x <= Math.pow(-2,31)){
            return 0;
        }

        //计算过程中可能溢出，溢出返回0
        if(x > 0){
            int len = (x+"").length();
            for(int i = 0;i < len;i++){
                //中间过程，一开始   x % (int)Math.pow(10,i+1) ,已经造成了整数溢出
                long tmp = x % (long)Math.pow(10,i+1) ;
               // System.out.println(tmp);
                tmp = tmp / (long)Math.pow(10,i);
                //System.out.println(tmp);
               // System.out.println(result);
                result += tmp * (long)Math.pow(10,len-i-1);
            }
        }else if(x < 0){
            x = -x;
            int len = (x+"").length();
            for(int i = 0;i < len;i++){
                long tmp = x % (long)Math.pow(10,i+1) ;
                // System.out.println(tmp);
                tmp = tmp / (long)Math.pow(10,i);
                // System.out.println(tmp);
                // System.out.println(result);
                result += tmp * (long)Math.pow(10,len-i-1);
            }
            result = -result;
        }else{
            result = x;
        }
        //利用long来存储中间过程
        if(result >= Integer.MAX_VALUE ||result <= Integer.MIN_VALUE ){
            return 0;
        }

        return (int)result;
    }
}
```