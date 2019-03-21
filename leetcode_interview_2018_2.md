# 数组相关问题
## 乘积最大子序列
```
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```
### 最简单，时间复杂度最高的做法
+ leetcode 提交超时
```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int tmp = 1;
                for(int k=i;k<=j;k++){
                    tmp *= nums[k];
                }
                if(tmp > max){
                    max = tmp;
                }
            }
        }
        return max;
    }
}
```
### 上面的做法,第三重循环其实多余了，改进如下
```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        //第一个循环控制子列的首部
        for (int i = 0; i < nums.length; i++) {
            int tmp =1;
            //第二个循环，既控制子列的尾部，也表示该段子列的积，叠加一次更新一次最大值
            for (int j = i; j < nums.length; j++) {
                //这里nums[]
                tmp *= nums[j];
                if(tmp > max){
                    max = tmp;
                }
            }
        }
        return max;
    }
}
```

## 旋转数组
```
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的原地算法。
```
### 为什么做题用最简单的办法，还要调试很久，最后超时？？？？？？？？？？？
+ 边界条件？大于还是大于等于，小于还是小于等于没有搞清楚
+ 变量的使用有问题
+ 不细心
### O(N^2)做法
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int tmp =0;
        //判断如果k比数组的长度要大
        if(k>nums.length){
            k = k - nums.length*(k/nums.length);
        }
        if(k<=0){
            return;
        }

        int length = nums.length;
        for(int i=0;i<k;i++){
            tmp = nums[length-1];
            for(int j=length-1;j>=0;j--){
                if(j-1>=0){
                    nums[j] = nums[j-1];
                }else{
                    nums[0] = tmp;
                }
            }
        }
    }
}
```
### 正确做法，翻转法
```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }

    private void reverse(int[] nums, int left, int right) {
        while(left<right){
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            right--;
            left++;
        }
    }
}
```

## 存在重复元素
```
给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
```
### 使用HashSet做法
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(!numSet.contains(nums[i])){
                numSet.add(nums[i]);
            }else{
                return true;
            }
        }

        return false;
    }
}
```
## 移动零元素
+ 各种边界条件和特殊情况的考虑
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int tmp =0;
        int sum =0;

        if(nums.length==1){
            return;
        }

        //没有考虑nums长度为1的情况
        for(int i=0;i<nums.length;i++){
            //如果全为0，不加sum<num.length跳不出循环
            if(nums[i]==0&&sum<nums.length){
                //如果nums[i]为0，则计数，并且i后的元素前移
                sum++;
                for(int j=i;j<nums.length-1;j++){
                    nums[j] = nums[j+1];
                }
                //为了防止出现移动一位以后，i位置上的数还是0
                i--;
            }
        }

        //将后面的空位补0,若元素全是0.则
        if(sum<nums.length){
            for(int i=nums.length-sum;i<nums.length;i++){
                nums[i] = 0;
            }
        }else {
            return;
        }
    }
}
```