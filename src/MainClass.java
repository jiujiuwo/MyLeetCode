class Solution {
    public int divide(int dividend, int divisor) {

        long  beichushu = dividend;
        long  chushu = divisor;
        int sign = 1;
        long result = 0;

        if(beichushu<0){
            //这里，如果输入是-2147483648，则取反以后，超过了int负数范围，造成dividend依然是负数
            beichushu = -beichushu;
            sign = sign * -1;
        }

        if(divisor<0){
            chushu = -chushu;
            sign = sign*-1;
        }

        int tmp = 0;

        if(chushu==1){
            if(sign*beichushu>=Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(sign*beichushu<=Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }else{
                return (int)beichushu*sign;
            }
        }

        //当输入   -2147483648，-1时，超时，用时太长。
        while (tmp + chushu<=beichushu){
            tmp += chushu;
            result++;
            //System.out.println(result);
            if(result >= Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
        }

        return (int)result*sign;
    }
}
public class MainClass {

    public static void main(String[] args) {
        System.out.println(new Solution().divide(-2147483648,-1));
        System.out.println(Integer.MAX_VALUE);
    }
}