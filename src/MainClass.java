class Solution {
    //递归解决模式串匹配问题

    public boolean isMatch(String s, String p) {

        System.out.println(s+","+p);
        if(p.isEmpty()){
            return s.isEmpty();
        }

        //判断首字母是否匹配
        boolean firstMatch = (!s.isEmpty()&&(p.charAt(0) == s.charAt(0)||p.charAt(0) == '.'));

        //如果 p 的长度大于2，且 p[1] == '*' ，
        if(p.length() >= 2 && p.charAt(1) =='*'){
            //如果出现了 *,那么*之前的字符是可以不出现的，因此可以考虑，匹配s和剩下的p[2:]
            //或的第二个条件也是必须的，如果输入是 “aa”,"a*"的话，适用
            return(isMatch(s,p.substring(2))||(firstMatch && isMatch(s.substring(1),p)));
        }else{  //p[1]不是
            return firstMatch && isMatch(s.substring(1),p.substring(1));
        }
    }
}

public class MainClass {
    public static void main(String[] args){
        boolean x = new Solution().isMatch("aab","c*a*b*");

        System.out.println(x);

    }
}