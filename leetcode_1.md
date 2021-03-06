## LeetCode刷题记录
# 1. 两数之和
+ 题目描述
```
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:
给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```
#### 解法
+ 暴力解法

+ hashmap
```java
import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }else{
                map.put(nums[i],i);
            }
        }
        return nums;
    }
}
```

### 相关题目
```
15. 三数之和
18. 四数之和
167. 两数之和 II - 输入有序数组
两数之和 III - 数据结构设计
和为K的子数组
两数之和 IV - 输入 BST
小于 K 的两数之和
```


# 2.add two numbers  2018/11/13  
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
+ 第二次的写法
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(l1.val+l2.val);
        ListNode ptr = head;
        
        if(l1.next==null&&l2.next==null){
            if(ptr.val>=10){
                ptr.val = ptr.val -10;
                ptr.next = new ListNode(1);
            }
        }
        
        while(l1.next!=null||l2.next!=null){
            
            if(l1.next!=null){
                l1 = l1.next;
            }else{
                l1.val = 0;
            }
            
            if(l2.next!=null){
                l2 = l2.next;
            }else{
                l2.val = 0;
            }
            
            if(ptr.val>=10){
                ptr.val = ptr.val -10;
                ptr.next = new ListNode(l1.val+l2.val+1);
            }else{
                ptr.next = new ListNode(l1.val+l2.val);
            }
            ptr = ptr.next;
        }
        
        if(ptr.val>=10){
            ptr.val = ptr.val - 10;
            ptr.next = new ListNode(1);
        }
        
        return head;
    }
}
```
# 3.Longest Substring Without Repeating Characters 2018/11/17  
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
+ java解法，利用数组,双指针（滑动窗口）
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0,right = -1;
        int freq[] = new int[256];
        int result = 0;
        char[] charArray = s.toCharArray();
        while(left<s.length()){
            if(right+1<s.length()&&freq[charArray[right+1]]==0){
                right++;
                freq[charArray[right]]++;
            }else{
                freq[charArray[left]]--;
                left++;
            }
            result = Math.max(result,right-left+1);
        }
        return result;
    }
}
```

# 4. Median of Two Sorted Arrays  2018/11/18 &ensp; &ensp; &ensp;
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

## 5.Longest Palindromic Substring（最长回文子串） 2018/11/19 *****
### 解法
+ 暴力解法
+ 动态规划法
+ 扩展中心法
```java
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        int start = 0, end = 0;
        if (length <= 1) {
            return s;
        }
        for (int i = 0; i < length; i++) {
            int len1 = 0;
            int len2 = 0;
            len1 = expandCenter(s, i, i + 1);
            len2 = expandCenter(s, i, i);

            len1 = Math.max(len1, len2);

            if (len1 > end - start) {
                start = i - (len1 - 1) / 2;
                end = i + len1 / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandCenter(String s, int left, int right) {
        int i = left, j = right;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}
```
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
```
1. dp[l][r]为true表示从l到r的子串为回文串
2. 初始化二位数组，单个字符为回文串，所以dp[i][i]=true
3. 找到状态转移方程，dp[l][r] = (s[r]==s[l]&&(r-l==1||dp[l+1][r-1]))?true:false
```
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
+ 动态规划
```java
class Solution {
    public String longestPalindrome(String s) {

        int sLength = s.length();
        int maxLength = 0;
        String result = "";
        if (sLength <= 1) {
            return s;
        } else {
            boolean dp[][] = new boolean[sLength][sLength];
            for (int i = sLength-1; i >= 0; i--) {
                for (int j = sLength-1; j >= i; j--) {
                    if(s.charAt(i)==s.charAt(j)&&((j-i<=1)||(dp[i+1][j-1]))){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = false;
                    }
                    if(dp[i][j]&&(j-i>=maxLength)){//注意这里是>=
                        maxLength = j-i+1;
                        result = s.substring(i,j+1);
                    }
                }
            }

        }

        return result;
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
### 相似题目
+ 214.最短回文串
+ 336.回文对
+ 516.最长回文子序列
+ 647.回文子串

## 6.ZigZag Conversion（Z型字母排列转换）  2018/11/21 
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
#### 7. Reverse Integer 2018/11/22
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
+ Go语言解法

#### 8. String to Integer (atoi) 2018/11/23，
+ 一道简单的题目，结果acc只有14.2%，远低于其他题目，原因就是，要考虑的情况太多了。
我足足提交了10次才ac
<br> beat 97.57%,17ms

```java
class Solution {
    public int myAtoi(String str) {

        double result = 0;
        int figureCount = 0;    //处理 +0 123 这样的输入情况

        //一开始没有判空
        if(str.length()<=0){
            return 0;
        }

        boolean isNegative = false;

        for(int i= 0;i < str.length();i++){
            //这里也要判断  figureCount==0  防止出现  0-1的情况
            if(str.charAt(i)=='-' && result==0&&figureCount==0){
                isNegative = true;
                //防止出现正负号同时有的情况
                if(i < str.length()-1 && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'){
                    continue;               //这里要加continue,否则，如果只有一个"-"号，会越界
                }else {
                    break;
                }
            }
            if(str.charAt(i)=='+'&&result==0&&figureCount==0){
                if(i < str.length()-1 && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'){
                    continue;               //这里要加continue,否则，如果只有一个"-"号，会越界
                }else {
                    break;
                }
            }
            //System.out.println(i);
            if(str.charAt(i)>='0' && str.charAt(i)<='9'){
                result = result * 10 + str.charAt(i) - '0';
                figureCount++;
            }else if(str.charAt(i)==' '&&result==0&&figureCount==0){
                continue;
            }else{
                break;
            }
        }

        //判断正负要在下面的代码之前
       // System.out.println(result);

        if(isNegative){
            result = -result;
        }
        //添加了两条语句，运行时间增加30ms
       // System.out.println(result);
        //System.out.println(Long.MAX_VALUE);

        if(result >= Integer.MAX_VALUE||result>=Long.MAX_VALUE){
            return Integer.MAX_VALUE;
        }else if(result <= Integer.MIN_VALUE||result<=Long.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        return (int)result;
    }
}
```
#### 9. Palindrome Number,回文数字的判断
+ Java 解法
+ 154ms,faster than 24.48% java
```java
class Solution {
    /*
        简单的操作，而且是一重循环，但是耗时较高，154ms.
     */
    public boolean isPalindrome(int x) {

        String tmpX= x +"";
        for(int i = 0;i <tmpX.length();i++){
            if(tmpX.charAt(i)==tmpX.charAt(tmpX.length()-i-1)){
                continue;
            }else {
                return false;
            }
        }

    return true;
    }
}
```

+ Go语言解法
+ Runtime: 56 ms, faster than 100.00% of Go online submissions for Palindrome Number.
```go
import (
	"strconv"
)
//哇，go语言同样的解法，beat 100%
func isPalindrome(x int) bool {
	tmpX := strconv.Itoa(x)
	len := len(tmpX)
	for index,_ := range []byte(tmpX){
		if(tmpX[index]==tmpX[len - index -1]){
			continue
		}else{
			return false
		}
	}
	return true
}

```

#### 10. Regular Expression Matching (正则表达式匹配) *****
+ hard类型的一道题目，但是可以直接使用java的正则表达式匹配求解

+ java,使用String类的match函数
+ 138 ms, faster than 7.84% of Java online submissions for Regular Expression Matching.
```java
class Solution {
    public boolean isMatch(String s, String p) {

        boolean isMatch = s.matches(p);

        return isMatch;
    }
}
```

+ Java,使用递归方式求解
    + 134ms,beat 8.46%
```java
class Solution {
    //递归解决模式串匹配问题

    public boolean isMatch(String s, String p) {
        if(p.isEmpty()){
            return s.isEmpty();
        }
        System.out.println(s+","+p);

        //判断首字母是否匹配
        boolean firstMatch = (!s.isEmpty()&&(p.charAt(0) == s.charAt(0)||p.charAt(0) == '.'));

        //如果 p 的长度大于2，且 p[1] == '*' ，
        if(p.length() >= 2 && p.charAt(1) =='*'){
            //如果出现了 *,那么*之前的字符是可以不出现的，因此可以考虑，匹配s和剩下的p[2:]
            return(isMatch(s,p.substring(2))||(firstMatch && isMatch(s.substring(1),p)));
        }else{  //p[1]不是
            return firstMatch && isMatch(s.substring(1),p.substring(1));
        }
    }
}

```
+ java使用动态规划求解，
    + 22ms,beat 50.7%
```java
enum Result {
    TRUE, FALSE
}

class Solution {
    Result[][] memo;

    public boolean isMatch(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                                   (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                       first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
}
```
+ 动态规划，自底向上
    + 19ms,beat 63.4%
```java
class Solution {
    //递归解决模式串匹配问题
    //自底向上做法

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;
        for(int i = s.length();i >=0;i--){
            for(int j = p.length()-1;j >=0;j--){
                //这里实现的步骤感觉和递归确实差不多
                boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if(j + 1 < p.length() && p.charAt(j+1) == '*'){
                    //这里的dp和递归中的isMatch方法本质相同
                    dp[i][j] = dp[i][j+2]||firstMatch && dp[i+1][j];
                }else {
                    dp[i][j] = firstMatch &&dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

}

```

+ Go语言，使用正则库，结果错误,下一步查找原因
```go
import (
	"regexp"
)

/*
	同样是内建的正则库，go语言的结果与java不同，不知道为什么。
	目前该方法是错误的，下一步寻找问题
 */
func isMatch(s string, p string) bool {
	isMatch,_ := regexp.MatchString(p,s)
	//isMatch,_ := regexp.Match(p,[]byte(s))	两个方法都是错的
	return isMatch
}
```