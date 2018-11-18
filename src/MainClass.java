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
public class MainClass {
    public static void main(String[] args){
       int nums1[] ={1,3};
       int nums2[] = {2,4};
        double x = new Solution().findMedianSortedArrays(nums1,nums2);
        System.out.println(x);
    }
}