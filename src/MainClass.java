import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //首先对原输入数组进行排序
        Arrays.sort(nums);

        for(int tmp:nums){
            System.out.printf(tmp+" ");
        }
        System.out.println();
        //用来存储最后的结果
        List<List<Integer>>  result = new ArrayList<>();

        for(int i = 0;i < nums.length-3;i++){
            //如果出现了重复的i,跳过
            if(i>0&&nums[i]==nums[i-1]){
                i++;
            }

            for(int j = i+1;j < nums.length-2;j++){     //二重循环，遍历i,j
                //while循环，遍历low,high
                //如果出现了重复的j,跳过去一个
                while(j<nums.length-2&&j>1&&nums[j]==nums[j+1]){
                    j++;
                }
                int low = j+1,high = nums.length-1;

                // System.out.println(i+" "+j);
               while(low<high){
                  // System.out.println(low+" "+high);
                    int tmp = nums[i]+nums[j]+nums[low]+nums[high];
                    if(tmp - target==0){
                        result.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
                        System.out.println(i+" "+j+" "+low+" "+high);
                        low++;
                        high--;
                    }else if(tmp - target >0){
                        high--;
                    }else{
                        low++;
                    }

                   //这个题目不需要对后面的元素进行过滤，因为他们就是不同的
                }
            }
        }
        return result;
    }
}

public class MainClass {
    public static void main(String[] args) {
        int tmp[] = {-1,0,-5,-2,-2,-4,0,1,-2};
        List<List<Integer>> x = new Solution().fourSum(tmp, -9);
        System.out.println(x);
    }
}