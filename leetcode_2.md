# Leetcode刷题2
## 11. Container With Most Water
+ java解法，第一次出现内存超出限制的问题
+ 测试用的输入很长,一个屏幕装不下。本来想复习一下dp的。结果打脸
```java
class Solution {
    public int maxArea(int[] height) {

        int dp[][] = new int[height.length][height.length];

        for(int i=0;i<height.length-1;i++){
            for(int j = 1;j < height.length;j++){
                dp[i][j] = (height[i] > height[j] ? height[j] : height[i]) *(j-i);
               // System.out.println(i + " "+j+" "+(j-i));
            }
        }
        int result = 0;

        for(int i = 0;i < dp.length;i++){
            for(int j=0;j<dp.length;j++){
                if(dp[i][j]>result){
                    result = dp[i][j];
                }
            }
        }

        return result;
    }
}
```
+ 暴力做法
+ java,622ms,faster than 2.97%
```java
class Solution {
    public int maxArea(int[] height) {

    //    int dp[][] = new int[height.length][height.length];
        int tmp = 0,result = 0;

        for(int i=0;i<height.length-1;i++){
            for(int j = 1;j < height.length;j++){
                tmp = (height[i] > height[j] ? height[j] : height[i]) *(j-i);
               // System.out.println(i + " "+j+" "+(j-i));
                if(tmp>result){
                    result = tmp;
                }
            }
        }

        return result;
    }
}
```
+ java O(n)解法
+ beat 3.89%
```java
class Solution {
    //O(n)的解法，因为左右取两者小后是对称的，所以，没有必要二重循环，
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}

```
+ 奇怪的5ms解法，不知道和上面差在哪里
+ 可能差在 Math库的调用？？？
```java
class Solution {
    //O(n)的解法，因为左右取两者小后是对称的，所以，没有必要二重循环，
    public int maxArea(int[] height) {
        int max = 0,s = 0;

        for(int i = 0,j = height.length-1; i < j;){
            if(height[i] > height[j]) {
                s = height[j]*(j-i);
                j--;
            }
            else {
                s = height[i]*(j-i);
                i++;
            }
            if(s > max) max = s;
        }

        return max;
    }
}
```

+ Go 语言，用时竟然超过了java
+ 1180ms,faster than 6.13%
```go
package main

func maxArea(height []int) int {
	var tmp = 0
	var result = 0
	for i:=0;i<len(height);i++{
		for j:=1;j<len(height);j++{

			if height[i]>height[j]{
				tmp = height[j]
			}else{
				tmp = height[i]
			}
			tmp = tmp * (j-i)
			
			if tmp>result{
				result = tmp
			}
		}
	}
	return result
}

```

## 12. Integer to Roman

+ java 分类讨论思路，获取每一位上的数字做转换，代码量较大
+ Runtime: 75 ms, faster than 20.56% of Java online submissions for Integer to Roman.
```java
class Solution {

    private String add(String tmp,int count){
        if(count <= 3){
            for(int i = 0;i < count;i++){
                tmp +="I";
            }
        }else if(count == 4){
            tmp +="IV";
        }else if(count < 9 && count >4){
            tmp +="V";
            for(int i = 0;i < count-5;i++){
                tmp +="I";
            }
        }else {
            tmp += "IX";
        }
        return tmp;
    }

    private String add10(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="X";
            }
        }else if(count == 4){
            tmp += "XL";
        }else if(count < 9 && count >4){
            tmp +="L";
            for(int i = 0;i < count-5;i++){
                tmp +="X";
            }
        }else{
            tmp += "XC";
        }
        return tmp;
    }
    private String add100(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="C";
            }

        }else if(count == 4){
            tmp += "CD";
        }else if(count < 9 && count >4){
            tmp +="D";
            for(int i = 0;i < count-5;i++){
                tmp +="C";
            }
        }else{
            tmp += "CM";
        }
        return tmp;
    }

    private String add1000(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="M";
            }
        }else {
            return "error";
        }
        return tmp;
    }

    public String intToRoman(int num) {

        String result = "";
        String numS = (num+"");

        if(num >= 1000){
            result = add1000(result,numS.charAt(0)-'0');
          //  System.out.println(result);
            result = add100(result,numS.charAt(1)-'0');
          //  System.out.println(result);
            result = add10(result,numS.charAt(2)-'0');
          //  System.out.println(result);
            result = add(result,numS.charAt(3)-'0');
            //System.out.println(result);
        }else if(num <1000 && num>=100){
            result = add100(result,numS.charAt(0)-'0');
            result = add10(result,numS.charAt(1)-'0');
            result = add(result,numS.charAt(2)-'0');
        }else if(num<100 && num>=10){
            result = add10(result,numS.charAt(0)-'0');
            result = add(result,numS.charAt(1)-'0');
        }else{
            result = add(result,numS.charAt(0)-'0');
        }
        return result;
    }

}
```