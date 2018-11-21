class Solution {
    public String convert(String s, int numRows) {

        String result = "";
        char[][] chars = new char[numRows][s.length()];
        boolean down = true;

        //一开始没有想到，行数为1的情况，导致编译错误。
        if(numRows <=1){
            return s;
        }

        //简单的题目做了一个多小时。。。惨
        for(int i=0,j =0,index =0;index < s.length();index++){
            chars[i][j] = s.charAt(index);
           // System.out.println(i+" "+j);
            if(down){
                i++;
            }else{
                i--;
                j++;
            }
           if(i==numRows){
                i -= 2;     //边界条件搞不清楚，这里，一开始写的 i--,结果发现不对
                j++;
                down = false;
           }
           if(i==0){
              // i++;           在向上走的时候，一直是i--,当i减到0时，
               down = true;
           }
        }

        for(int i = 0;i < numRows;i++){
            for(int j = 0; j < s.length();j++){
                if(chars[i][j]!='\0')
                result += chars[i][j];
            }
        }

        return result;
    }
}
public class MainClass {
    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
        String x = new Solution().convert("PAYPALISHIRING",3);
        System.out.println(System.currentTimeMillis());
        System.out.println(x);
    }
}