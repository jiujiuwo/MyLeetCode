class Solution {
    //递归解决模式串匹配问题
    //自上向下
    boolean[][] dp;

    public boolean isMatch(String s, String p) {
        dp = new boolean[s.length()+1][p.length()+1];

        //dp[i][j]表示s[i:]p[j:]是否匹配，因此如果两个都是空串，则肯定都匹配
        dp[s.length()][p.length()] = true;
        for(int i = s.length();i >=0;i--){
            for(int j = p.length()-1;j >=0;j--){
                //这里实现的步骤感觉和递归确实差不多
                boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if(j + 1 < p.length() && p.charAt(j+1) == '*'){
                    //这里的dp和递归中的isMatch方法本质相同，如果p的j+1位置上为*，则查看dp[i][j+2]是否匹配，或首字母和dp[i+1][j]是否匹配
                    dp[i][j] = dp[i][j+2]||firstMatch && dp[i+1][j];
                }else {
                    dp[i][j] = firstMatch &&dp[i+1][j+1];
                }
            }
        }

        dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i = 0;i < s.length();i++){
            for(int j = 0;j < p.length();j++){
                boolean firstMatch = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
            }
        }
        return dp[0][0];
    }

}

public class MainClass {
    public static void main(String[] args){
        boolean x = new Solution().isMatch("aab","c*a*b*");

        System.out.println(x);

    }
}