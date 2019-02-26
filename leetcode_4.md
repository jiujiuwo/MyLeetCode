
## 35. Search Insert Position
+ java做法，复杂度O(N),
+ Runtime: 3 ms, faster than 74.98% of Java online submissions for Search Insert Position.
```java
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
```

## 38.Count and Say
+ java做法，遍历字符串，时间复杂度O(N<sup>2</sup>)
+ 9 ms, faster than 29.08% of Java online submissions for Count and Say.
  Memory Usage: 37.5 MB, less than 6.90% of Java online submissions for Count and Say.
 ```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    public String countAndSay(int n) {

        String pre = "1";
        String result = "";

        if(1 == n){
            return pre;
        }else{
            for(int j=1;j<n;j++){
                int count = 0;
                for(int i=0;i<pre.length();i++){
                    count++;
                    if(i == pre.length()-1 || pre.charAt(i) != pre.charAt(i+1) ){
                        String tmp = count +""+pre.charAt(i);
                        result+=tmp;
                        count = 0;
                    }
                }
                pre = result;
                //System.out.println(pre);
                result = "";
            }
        }
        return pre;
    }
}
```