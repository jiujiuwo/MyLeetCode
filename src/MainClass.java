class Solution {
    public boolean isMatch(String s, String p) {

        boolean isMatch = s.matches(p);

        return isMatch;
    }
}

public class MainClass {
    public static void main(String[] args){
        boolean x = new Solution().isMatch("aa","a");

        System.out.println(x);

    }
}