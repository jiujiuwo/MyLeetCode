import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i < s.length();i++){
            char tmp = s.charAt(i);
            if(tmp == '('||tmp=='['||tmp=='{'){
                stack.push(tmp);
            }else if(tmp ==')'){
                if(stack.isEmpty()||stack.pop()!='('){
                    return false;
                }
            }else if(tmp =='}'){
                if(stack.isEmpty()||stack.pop()!='{'){
                    return false;
                }
            }else if(tmp ==']'){
                if(stack.isEmpty()||stack.pop()!='['){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

public class MainClass {

}