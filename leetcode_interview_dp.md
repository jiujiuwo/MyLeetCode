# 动态规划问题
## 1. 最大子序和
```
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

#### 解法1. 使用前缀和，时间复杂度O（n^2）
```java
class Solution {
    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int[] sum = new int[n+1];
        int max = nums[0];

        //sum数组存储前n项和
        for(int i=1;i<n+1;i++){
            sum[i] = sum[i-1]+nums[i-1];
        }

        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                max = Math.max(max,sum[j]-sum[i]);
            }
        }

        return max;
    }
}


```
####第二种O(N^2)的解法
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        //第一个循环控制子列的首部
        for (int i = 0; i < nums.length; i++) {
            int tmp =0;
            //第二个循环，既控制子列的尾部，也表示该段子列的积，叠加一次更新一次最大值
            for (int j = i; j < nums.length; j++) {
                //这里nums[]
                tmp += nums[j];
                if(tmp > max){
                    max = tmp;
                }
            }
        }
        return max;
    }
}
```
#### 解法2. 采用动态规划法
+ 我们用dp[n]表示以第n个数结尾的最大连续子序列的和，于是存在以下递推公式：
  dp[n] = max(0, dp[n-1]) + num[n]
  仔细思考后不难发现这个递推公式是正确的，则整个问题的答案是max(dp[m])
 ```java
class Solution {
    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        int max = nums[0];
        for(int i=1;i<n;i++){

            if(dp[i-1]>=0){
                dp[i] = dp[i-1]+nums[i];
            }else{
                dp[i] = nums[i];
            }
            if(dp[i]>max){
                max = dp[i];
            }
        }
        return max;
    }
}
```