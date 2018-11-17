import java.util.HashMap;
import java.util.Map;

class Solution {

    public int lengthOfLongestSubstring(String s) {

        int maxLen = 0;
        int startIndex =0;

        Map<Character,Integer> lastOccuredMap = new HashMap<>();

        for(int i =0;i < s.length();i++){
            char tmp = s.charAt(i);
          //  System.out.println(tmp);

            //一开始忘记 添加 lastOccuredMap.get(tmp)>=startIndex 这个条件，导致回退过多。
            // 还是对算法的过程不熟悉，不是自己想的
            if(lastOccuredMap.containsKey(tmp)&&lastOccuredMap.get(tmp)>=startIndex){
                startIndex = lastOccuredMap.get(tmp) + 1;
            }

            if((i - startIndex + 1) > maxLen){
                maxLen = i - startIndex + 1;
            }
         //   System.out.println(i);

            lastOccuredMap.put(tmp,i);
        }

        return maxLen;
    }
}

public class MainClass {
    public static void main(String[] args){
        String s = "abba";
        int x = new Solution().lengthOfLongestSubstring(s);
        System.out.println(x);
    }
}