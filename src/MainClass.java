class Solution {
    public int myAtoi(String str) {

        double result = 0;
        int figureCount = 0;    //处理 +0 123 这样的输入情况

        //一开始没有判空
        if(str.length()<=0){
            return 0;
        }

        boolean isNegative = false;

        for(int i= 0;i < str.length();i++){
            //这里也要判断  figureCount==0  防止出现  0-1的情况
            if(str.charAt(i)=='-' && result==0&&figureCount==0){
                isNegative = true;
                //防止出现正负号同时有的情况
                if(i < str.length()-1 && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'){
                    continue;               //这里要加continue,否则，如果只有一个"-"号，会越界
                }else {
                    break;
                }
            }
            if(str.charAt(i)=='+'&&result==0&&figureCount==0){
                if(i < str.length()-1 && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'){
                    continue;               //这里要加continue,否则，如果只有一个"-"号，会越界
                }else {
                    break;
                }
            }
            //System.out.println(i);
            if(str.charAt(i)>='0' && str.charAt(i)<='9'){
                result = result * 10 + str.charAt(i) - '0';
                figureCount++;
            }else if(str.charAt(i)==' '&&result==0&&figureCount==0){
                continue;
            }else{
                break;
            }
        }

        //判断正负要在下面的代码之前
       // System.out.println(result);

        if(isNegative){
            result = -result;
        }

       // System.out.println(result);
        //System.out.println(Long.MAX_VALUE);

        if(result >= Integer.MAX_VALUE||result>=Long.MAX_VALUE){
            return Integer.MAX_VALUE;
        }else if(result <= Integer.MIN_VALUE||result<=Long.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        return (int)result;
    }
}
public class MainClass {
    public static void main(String[] args){
      //  System.out.println(System.currentTimeMillis());
        int x = new Solution().myAtoi("9223372036854775808");
     //   System.out.println(System.currentTimeMillis());
        System.out.println(x);
        //java 中 int 的最大值
       // System.out.println(Integer.MAX_VALUE);
      //  System.out.println(Long.MAX_VALUE);
        //System.out.println(Math.pow(2,31)-1);
    }
}