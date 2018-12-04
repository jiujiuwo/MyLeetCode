
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        //首先对整个数组进行排序
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        //第一重循环，从下标 0 到 num.length-2
        for (int i = 0; i < num.length - 2; i++) {
            //去重，如果 num[i] 和 num[i-1]相同则跳过
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                //num[i] + num[i+1]+num[hi] = 0,num[i+1]+num[hi] = 0 - num[i]
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        //这两行去重
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }
}

public class MainClass {
    public static void main(String[] args) {
        int[] tmp = {-5, 14, 1, -2, 11, 11, -10, 3, -6, 0, 3, -4, -9, -13, -8, -7, 9, 8, -7, 11, 12, -7, 4, -7, -1, -5, 13, 1, -2, 8, -13, 0, -1, 3, 13, -13, -1, 10, 5, 1, -13, -15, 12, -7, -13, -11, -7, 3, 13, 1, 0, 2, 1, 11, 10, 8, -8, 1, -14, -3, -6, -12, 12, 0, 6, 2, 2, -9, -3, 14, -1, -9, 14, -4, -1, 8, -8, 7, -4, 12, -14, 3, -9, 2, 0, -13, -13, -1, 3, -12, 11, 4, -9, 8, 11, 5, -5, -10, 3, -1, -11, -13, 5, -12, -10, 11, 11, -3, -5, 14, -13, -4, -5, -7, 6, 2, -13, 0, 8, -3, 4, 4, -14, 2};
        List<List<Integer>> x = new Solution().threeSum(tmp);
        System.out.println(x);
        System.out.println(x.size());
    }
}