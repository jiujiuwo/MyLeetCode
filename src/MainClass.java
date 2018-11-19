class Solution {
    public String longestPalindrome(String s) {

        String result = "";
        String tmp ="";

        for(int i=0;i< s.length();i++){
            for (int j = s.length();j >=i;j--){
                if(checkPalindrome(s.substring(i,j))){
                    tmp = s.substring(i,j);
                    if(tmp.length()>result.length()){
                        result = tmp;
                    }
                }
            }
        }

        return result;
    }

    private boolean checkPalindrome(String substring) {
      //  System.out.println(substring);

        boolean isPalindrome = true;

        for(int i= 0; i < substring.length()/2;i++){
            if(substring.charAt(i)==substring.charAt(substring.length()-1-i)){
                continue;
            }else{
                isPalindrome = false;
            }
        }

        return isPalindrome;
    }
}
public class MainClass {
    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
        String x = new Solution().longestPalindrome("\"vmqjjfnxtyciixhceqyvibhdmivndvxyzzamcrtpywczjmvlodtqbpjayfchpisbiycczpgjdzezzprfyfwiujqbcubohvvyakxfmsyqkysbigwcslofikvurcbjxrccasvyflhwkzlrqowyijfxacvirmyuhtobbpadxvngydlyzudvnyrgnipnpztdyqledweguchivlwfctafeavejkqyxvfqsigjwodxoqeabnhfhuwzgqarehgmhgisqetrhuszoklbywqrtauvsinumhnrmfkbxffkijrbeefjmipocoeddjuemvqqjpzktxecolwzgpdseshzztnvljbntrbkealeemgkapikyleontpwmoltfwfnrtnxcwmvshepsahffekaemmeklzrpmjxjpwqhihkgvnqhysptomfeqsikvnyhnujcgokfddwsqjmqgsqwsggwhxyinfspgukkfowoxaxosmmogxephzhhy\"");
        System.out.println(System.currentTimeMillis());
        System.out.println(x);
    }
}