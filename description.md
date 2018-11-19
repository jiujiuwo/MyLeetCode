## LeetCode刷题记录

#### 2018 11 13, add two numbers
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

#### 2018 11 17 3,Longest Substring Without Repeating Characters
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

#### 2018/11/18 &ensp; &ensp; &ensp;4. Median of Two Sorted Arrays
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

#### 2018/11/19 5. Longest Palindromic Substring
+ java 超时解，
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