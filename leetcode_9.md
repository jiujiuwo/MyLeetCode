# leetcode 88.合并两个有序数组
### 题目描述
```
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，
使得 num1 成为一个有序数组。
说明:
初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
输出: [1,2,2,3,5,6]
```
+ 解答1
+ 5ms
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
+ 解答2
+ 0ms
```java

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int newIndex = m+n-1;
        while(m>0&&n>0){
            if(nums1[m-1]>nums2[n-1]){
                nums1[newIndex] = nums1[m-1];
                m--;
            }else{
                nums1[newIndex] = nums2[n-1];
                n--;
            }
            newIndex--;
        }
        //注意要考虑m=0的情况
        if(m==0&&n>0){
            for(int i=0;i<n;i++){
                nums1[i]=nums2[i];
            }
        }
    }
}
```