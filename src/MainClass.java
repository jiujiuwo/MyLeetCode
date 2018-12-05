import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {

        //用来表示结果，即三个数的和
        int result = 0;
        //用来表示距离
        int distance = Integer.MAX_VALUE;
        //先从小打到大排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                //求出三个数的和与target的差
                //target >0
                int tmp;
                tmp = nums[i] + nums[low] + nums[high] - target;
                System.out.println(i + " "+low + " "+high+" "+tmp+" "+result+" "+distance);

                //如果差<0
                if (tmp < 0) {
                    tmp = -tmp;
                    if (tmp < distance) {
                        result = -tmp + target;
                        distance = tmp;
                    }
                    low++;
                } else {  //如果差>0
                    if (tmp < distance) {
                        result = tmp + target;
                        distance = tmp;
                    }
                    high--;
                }
            }
        }
        return result;
    }
}

public class MainClass {
    public static void main(String[] args) {
        int tmp[] = {1, 1, 1, 0};
        int x = new Solution().threeSumClosest(tmp, -100);
        System.out.println(x);
    }
}