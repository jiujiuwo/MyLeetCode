import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        //取和
        for(int i = 0;i < nums.length;i++){
            for(int j =0;j < nums.length;j++){
                for(int k=0;k < nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0&&i!=j&&i!=k&&j!=k){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        if(nums[i]==nums[j]&&nums[j]==nums[k]&&nums[i]==0){
                            result.size();
                        }
                        //判断是否有重复
                        boolean dup = false;
                        for(List<Integer> item:result){
                            //如果item中有一个0，而tmp中有三个0，则，item.containsAll(tmp)也为true
                            //所以此处应改为 tmp.containsAll(item)&&item.containsAll(tmp) 教合适
                            //若仅改为tmp.containsAll(item)也会出现类似的情况
                            if(tmp.containsAll(item)&&item.containsAll(tmp)){
                                dup = true;
                            }
                        }
                        if(!dup){
                            result.add(tmp);
                        }
                    }
                }
            }
        }

        return result;
    }
}
public class MainClass {
    public static void main(String[] args){
        int[] tmp = {-6,-8,-9,4,-14,6,-10,7,12,13,4,9,7,14,-12,7,0,14,-1,-3,2,2,-3,11,-6,-10,-13,-13,1,-9,2,2,-2,8,-9,0,-9,-12,14,10,8,3,4,0,-6,7,14,9,6,-2,13,-15,8,-5,3,-13,-8,5,-11,0,11,6,-13,-14,-9,-15,-7,-11,10,-7,14,4,3,3,11,13,-13,11,-1,0,-6,-10,0,9,0,10,11,0,0,-14,-15,-12,-1,10,12,-2,2,-10,2,-2,-10,2,-13,1,12,5,-1,-15,1,5,-8,3,10,8};
        List<List<Integer>>  x = new Solution().threeSum(tmp);
        System.out.println(x);
        System.out.println(x.size());
    }
}