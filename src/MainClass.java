class Solution {
    /*
        简单的操作，而且是一重循环，但是耗时较高，154ms.
     */
    public boolean isPalindrome(int x) {

        String tmpX= x +"";
        for(int i = 0;i <tmpX.length();i++){
            if(tmpX.charAt(i)==tmpX.charAt(tmpX.length()-i-1)){
                continue;
            }else {
                return false;
            }
        }

    return true;
    }
}


public class MainClass {
    public static void main(String[] args){
        boolean x = new Solution().isPalindrome(808);

        System.out.println(x);

    }
}