import java.util.ArrayList;
import java.util.Collections;
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

                        //判断是否有重复
                        boolean dup = false;
/*                        for(List<Integer> item:result){
                            //如果item中有一个0，而tmp中有三个0，则，item.containsAll(tmp)也为true
                            //所以此处应改为 tmp.containsAll(item)&&item.containsAll(tmp) 教合适
                            //若仅改为tmp.containsAll(item)也会出现类似的情况
                            if(tmp.containsAll(item)&&item.containsAll(tmp)){
                                dup = true;
                            }
                        }*/
                        //通过排序来判断是否有重复
                        Collections.sort(tmp);
                        for(List<Integer> item:result){
                            Collections.sort(item);
                            if(item.get(0)==tmp.get(0)&&item.get(1)==tmp.get(1)&&item.get(2)==tmp.get(2)){
                                dup = true;
                                break;
                            }else{
                                continue;
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
        int[] tmp = {-5,14,1,-2,11,11,-10,3,-6,0,3,-4,-9,-13,-8,-7,9,8,-7,11,12,-7,4,-7,-1,-5,13,1,-2,8,-13,0,-1,3,13,-13,-1,10,5,1,-13,-15,12,-7,-13,-11,-7,3,13,1,0,2,1,11,10,8,-8,1,-14,-3,-6,-12,12,0,6,2,2,-9,-3,14,-1,-9,14,-4,-1,8,-8,7,-4,12,-14,3,-9,2,0,-13,-13,-1,3,-12,11,4,-9,8,11,5,-5,-10,3,-1,-11,-13,5,-12,-10,11,11,-3,-5,14,-13,-4,-5,-7,6,2,-13,0,8,-3,4,4,-14,2};
        List<List<Integer>>  x = new Solution().threeSum(tmp);
        System.out.println(x);
        System.out.println(x.size());
    }
}