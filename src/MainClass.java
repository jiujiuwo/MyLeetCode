class Solution {
    public String longestCommonPrefix(String[] strs) {

        //一开始没有判断输入数组的长度是否为0
        if(strs.length<=0){
            return "";
        }

        int len = strs[0].length();

        for(int i = 0;i < strs.length;i++){
            if(len > strs[i].length()){
                len = strs[i].length();
            }
        }

        boolean continued = true;
        int index = 0;

        for(int i =0;i < len;i++){
            for(int j =0;j< strs.length-1;j++){
                if(strs[j].charAt(i) == strs[j+1].charAt(i)){
                    continue;
                }else{
                    continued = false;
                }
            }
            if(!continued){
                break;
            }
            index++;
        }

        return strs[0].substring(0,index);
    }
}
public class MainClass {
    public static void main(String[] args){
        String[] tmp = {"flower","flow","flaw"};
        String x = new Solution().longestCommonPrefix(tmp);
        System.out.println(x);
    }
}