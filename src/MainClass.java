class Solution {
    public int strStr(String haystack, String needle) {

        //注意这里是 <=
        for(int i=0;i<=haystack.length()-needle.length();i++){
            //System.out.println(haystack.substring(i,i+needle.length()));
            if(haystack.substring(i,i+needle.length()).equals(needle)){
                return i;
            }
        }
        return -1;
    }
}
public class MainClass {
    public static void main(String[] args){
        int[] nums = {3,2,2,3};
        System.out.println(new Solution().strStr("hello","ll"));
    }
}