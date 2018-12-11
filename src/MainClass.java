class Solution {
    public int removeElement(int[] nums, int val) {
        int i =0;
        //只取前几个，不关心后面的值，所以可行
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }
}
public class MainClass {
    public static void main(String[] args){
        int[] nums = {3,2,2,3};
        System.out.println(new Solution().removeElement(nums,3));
    }
}