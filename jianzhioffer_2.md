# 11. 二进制中1的个数
```
输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
```
+ 解法1，使用位运算法
+ 思路
+ 如果一个整数不为0，那么这个整数至少有一位是1。如果我们把这个整数减1，那么原来处在整数最右边的1就会变为0，原来在1后面
的所有的0都会变成1(如果最右边的1后面还有0的话)。其余所有位将不会受到影响。
+ 这个时候如果我们再把原来的整数和减去1之后的结果做与运算，从原来整数最右边
一个1那一位开始所有位都会变成0。如1100&1011=1000.也就是说，把一个整数减去1，
再和原整数做与运算，会把该整数最右边一个1变成0.那么一个整数的二进制有多少个1，
就可以进行多少次这样的操作。
```java
public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        while(n!= 0){
            count++;
            n = n & (n - 1);
         }
        return count;
    }
}

//下面的这种做法通过了44%的case,猜测int不是无符号的类型
public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while(flag!= 0){
            if((n&flag)>0){        //找到了原因，n有可能为负数，因此大于0不成立，改外！=0通过
                count++;
            }
            flag = flag << 1;
         }
        return count;
    }
}
```

+ 解法2,使用Integer的方法
```java
public class Solution {
    public int NumberOf1(int n) {
        int count = 0;
        char[] tmp = Integer.toBinaryString(n).toCharArray();
        for(int i=0;i<tmp.length;i++){
            if(tmp[i]=='1'){
                count++;
            }
        }
        return count;
    }
}
```

# 12. 数值的整数次方
```
给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
```
+ 注意exponent为负数的情况
```java
public class Solution {
    //注意边界条件，exponent为负数的情况
    public double Power(double base, int exponent) {
        
        double result = 1.0;
        
        if(exponent<0){
            int tmp = - exponent;
            //这里应该使用修改后的值
            for(int i=0;i<tmp;i++){
                result *= base;
            }
            result = 1/result;
        }else if(exponent>0){
             for(int i=0;i<exponent;i++){
                result *= base;
            }
        }else{
            return 1;
        }
        
        return result;
  }
}
```