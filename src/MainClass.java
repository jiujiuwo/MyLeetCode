class Solution {
    public int searchInsert(int[] nums, int target) {

        int result = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=target){
                return i;
            }
        }

        return nums.length;
    }
}
public class MainClass {

    public static void main(String[] args) {
        System.out.println(new Solution().divide(-2147483648,-1));
        System.out.println(Integer.MAX_VALUE);
    }
}