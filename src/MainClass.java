class Solution {
    public int removeElement(int[] nums, int val) {

        if(nums.length==0){
            return 0;
        }
        //这部分在i=j后就不需要了
/*
        if(nums.length==1){
            if(nums[0]==val){
                return 0;
            }else{
                return 1;
            }
        }
*/

        int count = 0;
        //一开始 i<j,导致如果输入[4,5]的话，因为i<j所以 i不会等于1，导致错误
        for(int i=0,j=nums.length-1;i<=j;i++){
            if(nums[i] == val){
                int tmp = nums[i];
                //一开始缺少条件j<i导致越界错误
                while(j>i&&nums[j]==val){
                    count++;
                    j--;
                }
                nums[i] = nums[j];
                nums[j] = tmp;
                j--;
                count++;
            }
        }
        return nums.length-count;
    }
}
public class MainClass {
    public static void main(String[] args){
        int[] nums = {4,5};
        System.out.println(new Solution().removeElement(nums,5));
    }
}