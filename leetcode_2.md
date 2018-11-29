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