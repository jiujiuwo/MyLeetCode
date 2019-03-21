import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution {
    public void moveZeroes(int[] nums) {
        int tmp =0;
        int sum =0;

        if(nums.length==1){
            return;
        }

        //没有考虑nums长度为1的情况
        for(int i=0;i<nums.length;i++){
            //如果全为0，不加sum<num.length跳不出循环
            if(nums[i]==0&&sum<nums.length){
                //如果nums[i]为0，则计数，并且i后的元素前移
                sum++;
                for(int j=i;j<nums.length-1;j++){
                    nums[j] = nums[j+1];
                }
                //为了防止出现移动一位以后，i位置上的数还是0
                i--;
            }
        }

        //将后面的空位补0,若元素全是0.则
        if(sum<nums.length){
            for(int i=nums.length-sum;i<nums.length;i++){
                nums[i] = 0;
            }
        }else {
            return;
        }
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            new Solution().moveZeroes(nums);
            String out = integerArrayToString(nums);

            System.out.print(out);
        }
    }
}