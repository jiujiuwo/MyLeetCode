# 只出现一次的数字
```
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
```
## 常规做法，使用hashmap计数，有额外的空间
```java
class Solution {
    public int singleNumber(int[] nums) {

        Map<Integer,Integer> intMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!intMap.containsKey(nums[i])){
                intMap.put(nums[i],1);
            }else{
                intMap.put(nums[i],intMap.get(nums[i])+1);
            }
        }

        for(Integer item:intMap.keySet()){
            if(intMap.get(item)==1){
                return item;
            }
        }
        return 0;
    }
}
```
## 使用异或运算符做法，
```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i=0;i<nums.length;i++){
           result = result^nums[i];
        }
        return result;
    }
}
```

# 求众数
```
给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
```
## 常规做法，与上一题类似
```java
class Solution {
    public int majorityElement(int[] nums) {

        Map<Integer,Integer> intMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!intMap.containsKey(nums[i])){
                intMap.put(nums[i],1);
            }else{
                intMap.put(nums[i],intMap.get(nums[i])+1);
            }
        }

        for(Integer item:intMap.keySet()){
            if(intMap.get(item)>nums.length/2){
                return item;
            }
        }
        return 0;
    }
}
```
## 另一种方案
+ 从第一个数开始count=1，遇到相同的就加1，
遇到不同的就减1，减到0就重新换个数开始计数，
总能找到最多的那个
```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 1;
        int majority = nums[0];
        for(int i=1;i<nums.length;i++){
            if(majority==nums[i]){
                count++;
            }else{
                count--;
                if(count==0){
                    majority = nums[i+1];
                }
            }
        }
        return majority;
    }
}
```
# 搜索二维矩阵 II
```
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。
```
## 不优化的常规做法
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==target){
                    return true;
                }
            }
        }

        return false;
    }
}
```
## 优化方法
+ 左下角的元素是这一行中最小的元素，同时又是这一列中最大的元素。比较左下角元素和目标：
+ 若左下角元素等于目标，则找到
+ 若左下角元素大于目标，则目标不可能存在于当前矩阵的最后一行，问题规模可以减小为在去掉最后一行的子矩阵中寻找目标
+ 若左下角元素小于目标，则目标不可能存在于当前矩阵的第一列，问题规模可以减小为在去掉第一列的子矩阵中寻找目标
+ 若最后矩阵减小为空，则说明不存在
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        //这种方法注意边界输入
        if(matrix.length==0){
            return false;
        }

        int i= matrix.length-1;
        int j = 0;
        //最开始，i>0错误，i是下标，可以等于0的
        while (j<matrix[0].length&&i>=0){
            if(matrix[i][j]==target){
                return true;
            }else if(matrix[i][j]>target){
                i--;
            }else{
                j++;
            }
        }
        return false;
    }
}
```
# 合并两个有序数组
```
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:

输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
```
## 常规做法
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int length =0;
        for(int i=m;i<m+n;i++){
           nums1[i] = nums2[i-m];
        }
        Arrays.sort(nums1);
    }
}
```
## 逆向思维，从后往前插入
```java

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m>0&&n>0){
            if(nums1[m-1]>nums2[n-1]){
                nums1[m+n-1] = nums1[m-1];
                m--;
            }else{
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
        //nums2没有插入完，则将剩余元素插入到num1对应位置
        if(n!=0){
            for(int i=0;i<n;i++){
                nums1[i] = nums2[i];
            }
        }

    }
}
```

# 鸡蛋掉落
```
你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。

每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。

你的目标是确切地知道 F 的值是多少。

无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？

 

示例 1：

输入：K = 1, N = 2
输出：2
解释：
鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
如果它没碎，那么我们肯定知道 F = 2 。
因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
示例 2：

输入：K = 2, N = 6
输出：3
示例 3：

输入：K = 3, N = 14
输出：4
 

提示：

1 <= K <= 100
1 <= N <= 10000
```
