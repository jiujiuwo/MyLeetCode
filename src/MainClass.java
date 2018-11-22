class Solution {
    public int reverse(int x) {

        long result = 0;

        //一开始没有看清题目的范围
        if(x >= Math.pow(2,31)-1 || x <= Math.pow(-2,31)){
            return 0;
        }

        //计算过程中可能溢出，溢出返回0
        if(x > 0){
            int len = (x+"").length();
            for(int i = 0;i < len;i++){
                //中间过程，一开始   x % (int)Math.pow(10,i+1) ,已经造成了整数溢出
                long tmp = x % (long)Math.pow(10,i+1) ;
               // System.out.println(tmp);
                tmp = tmp / (long)Math.pow(10,i);
                //System.out.println(tmp);
               // System.out.println(result);
                result += tmp * (long)Math.pow(10,len-i-1);
            }
        }else if(x < 0){
            x = -x;
            int len = (x+"").length();
            for(int i = 0;i < len;i++){
                long tmp = x % (long)Math.pow(10,i+1) ;
                // System.out.println(tmp);
                tmp = tmp / (long)Math.pow(10,i);
                // System.out.println(tmp);
                // System.out.println(result);
                result += tmp * (long)Math.pow(10,len-i-1);
            }
            result = -result;
        }else{
            result = x;
        }
        //利用long来存储中间过程
        if(result >= Integer.MAX_VALUE ||result <= Integer.MIN_VALUE ){
            return 0;
        }

        return (int)result;
    }
}
public class MainClass {
    public static void main(String[] args){
      //  System.out.println(System.currentTimeMillis());
        int x = new Solution().reverse(1534236469);
     //   System.out.println(System.currentTimeMillis());
        System.out.println(x);
        //java 中 int 的最大值
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        //System.out.println(Math.pow(2,31)-1);
    }
}