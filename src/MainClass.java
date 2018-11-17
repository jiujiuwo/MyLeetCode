import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        int maxLen = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i+1; j<= chars.length; j++) {
                //System.out.println(s.substring(i,j));
                int tmp = checkDulplicateAndLen(s.substring(i,j));
                if(tmp > maxLen){
                    maxLen = tmp;
                }
            }
        }
        return maxLen;
    }

    //使用map计数的方法解此题，超时
    private int checkDulplicateAndLen(String substring) {
        int numChars = 0;
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i =0;i < substring.length();i++){
            if(charMap.containsKey(substring.charAt(i))){
                return 0;
            }else {
                charMap.put(substring.charAt(i),0);
                numChars++;
            }
        }
        return numChars;
    }
}

public class MainClass {
    public static void main(String[] args){
        String s = "pwwkew";
        new Solution().lengthOfLongestSubstring(s);
    }
}