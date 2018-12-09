import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        //递归函数
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    /*
        current用来保存生成的字符串，初始为空串，然后依次分别向后添加'('和')'
        pos表示当前生成的字符数量，
        combinations用来保存结果
     */
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {    //如果生成的字符串的长度已经和输入中的相等，那么判断现在生成的字符串是否有效，有效的话，添加到结果集中
            if (valid(current)){
                result.add(new String(current));
            }
        } else {
            //生成左括号的字符，追加到当前字符然后递归调用
            current[pos] = '(';
            generateAll(current, pos+1, result);
            //其实感觉下面这一步不必要，以 ')'开头的肯定不对
            //不对，下面这一步也很必要，只是在pos=0的时候不必要
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    //判断是否有效，感觉可以判断那个栈解决的问题啊，20题
    public boolean valid(char[] current) {
        System.out.println(current);
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;  //这里，如果balance <0的话会返回false.也就是说。）开头的都会返回false
        }
        return (balance == 0);
    }
}
public class MainClass {
    public static void main(String[] args){
        System.out.println(new Solution().generateParenthesis(3));
    }
}