# Leetcode刷题2
## 11. Container With Most Water
+ java解法，第一次出现内存超出限制的问题
+ 测试用的输入很长,一个屏幕装不下。本来想复习一下dp的。结果打脸
```java
class Solution {
    public int maxArea(int[] height) {

        int dp[][] = new int[height.length][height.length];

        for(int i=0;i<height.length-1;i++){
            for(int j = 1;j < height.length;j++){
                dp[i][j] = (height[i] > height[j] ? height[j] : height[i]) *(j-i);
               // System.out.println(i + " "+j+" "+(j-i));
            }
        }
        int result = 0;

        for(int i = 0;i < dp.length;i++){
            for(int j=0;j<dp.length;j++){
                if(dp[i][j]>result){
                    result = dp[i][j];
                }
            }
        }

        return result;
    }
}
```
+ 暴力做法
+ java,622ms,faster than 2.97%
```java
class Solution {
    public int maxArea(int[] height) {

    //    int dp[][] = new int[height.length][height.length];
        int tmp = 0,result = 0;

        for(int i=0;i<height.length-1;i++){
            for(int j = 1;j < height.length;j++){
                tmp = (height[i] > height[j] ? height[j] : height[i]) *(j-i);
               // System.out.println(i + " "+j+" "+(j-i));
                if(tmp>result){
                    result = tmp;
                }
            }
        }

        return result;
    }
}
```
+ java O(n)解法
+ beat 3.89%
```java
class Solution {
    //O(n)的解法，因为左右取两者小后是对称的，所以，没有必要二重循环，
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}

```
+ 奇怪的5ms解法，不知道和上面差在哪里
+ 可能差在 Math库的调用？？？
```java
class Solution {
    //O(n)的解法，因为左右取两者小后是对称的，所以，没有必要二重循环，
    public int maxArea(int[] height) {
        int max = 0,s = 0;

        for(int i = 0,j = height.length-1; i < j;){
            if(height[i] > height[j]) {
                s = height[j]*(j-i);
                j--;
            }
            else {
                s = height[i]*(j-i);
                i++;
            }
            if(s > max) max = s;
        }

        return max;
    }
}
```

+ Go 语言，用时竟然超过了java
+ 1180ms,faster than 6.13%
```go
package main

func maxArea(height []int) int {
	var tmp = 0
	var result = 0
	for i:=0;i<len(height);i++{
		for j:=1;j<len(height);j++{

			if height[i]>height[j]{
				tmp = height[j]
			}else{
				tmp = height[i]
			}
			tmp = tmp * (j-i)
			
			if tmp>result{
				result = tmp
			}
		}
	}
	return result
}

```

## 12. Integer to Roman

+ java 分类讨论思路，获取每一位上的数字做转换，代码量较大
+ Runtime: 75 ms, faster than 20.56% of Java online submissions for Integer to Roman.
```java
class Solution {

    private String add(String tmp,int count){
        if(count <= 3){
            for(int i = 0;i < count;i++){
                tmp +="I";
            }
        }else if(count == 4){
            tmp +="IV";
        }else if(count < 9 && count >4){
            tmp +="V";
            for(int i = 0;i < count-5;i++){
                tmp +="I";
            }
        }else {
            tmp += "IX";
        }
        return tmp;
    }

    private String add10(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="X";
            }
        }else if(count == 4){
            tmp += "XL";
        }else if(count < 9 && count >4){
            tmp +="L";
            for(int i = 0;i < count-5;i++){
                tmp +="X";
            }
        }else{
            tmp += "XC";
        }
        return tmp;
    }
    private String add100(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="C";
            }

        }else if(count == 4){
            tmp += "CD";
        }else if(count < 9 && count >4){
            tmp +="D";
            for(int i = 0;i < count-5;i++){
                tmp +="C";
            }
        }else{
            tmp += "CM";
        }
        return tmp;
    }

    private String add1000(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="M";
            }
        }else {
            return "error";
        }
        return tmp;
    }

    public String intToRoman(int num) {

        String result = "";
        String numS = (num+"");

        if(num >= 1000){
            result = add1000(result,numS.charAt(0)-'0');
          //  System.out.println(result);
            result = add100(result,numS.charAt(1)-'0');
          //  System.out.println(result);
            result = add10(result,numS.charAt(2)-'0');
          //  System.out.println(result);
            result = add(result,numS.charAt(3)-'0');
            //System.out.println(result);
        }else if(num <1000 && num>=100){
            result = add100(result,numS.charAt(0)-'0');
            result = add10(result,numS.charAt(1)-'0');
            result = add(result,numS.charAt(2)-'0');
        }else if(num<100 && num>=10){
            result = add10(result,numS.charAt(0)-'0');
            result = add(result,numS.charAt(1)-'0');
        }else{
            result = add(result,numS.charAt(0)-'0');
        }
        return result;
    }

}
```

## 13. Roman to Integer
+ java,O(n)解法，遍历所有字符求和
+ 一次性完成没有改动，Yeah!
+ Runtime: 35 ms, faster than 99.47% of Java online submissions for Roman to Integer.
```java
class Solution {
    public int romanToInt(String s) {
        int result = 0;

        for(int i =0;i < s.length();i++){
            char tmp = s.charAt(i);
            if(tmp == 'M'){
                result += 1000;
            }else if(tmp == 'C' && (i+1) < s.length() && s.charAt(i+1) == 'M'){
                result += 900;
                i++;
                continue;
            }else if(tmp == 'D'){
                result += 500;
            }else if(tmp == 'C' && (i+1) < s.length() && s.charAt(i+1) == 'D'){
                result += 400;
                i++;
                continue;
            }else if(tmp == 'C'){
                result += 100;
            }else if(tmp == 'X' && (i+1) < s.length() && s.charAt(i+1) == 'C'){
                result += 90;
                i++;
                continue;
            }else if(tmp == 'L'){
                result += 50;
            }else if(tmp == 'X' && (i+1) < s.length() && s.charAt(i+1) == 'L'){
                result += 40;
                i++;
                continue;
            }else if(tmp == 'X'){
                result += 10;
            }else if(tmp == 'I'  && (i+1) < s.length() && s.charAt(i+1) == 'X'){
                result += 9;
                i++;
                continue;
            }else if(tmp == 'V'){
                result += 5;
            }else if(tmp == 'I' && (i+1) < s.length() && s.charAt(i+1) == 'V'){
                result += 4;
                i++;
                continue;
            }else if(tmp == 'I'){
                result += 1;
            }
        }

        return result;
    }
}
```

## 14. Longest Common Prefix
+ java O(N<sup>2</sup>)解法，注意判断输入数组元素个数为0的情况
+ Runtime: 7 ms, faster than 61.14% of Java online submissions for Longest Common Prefix.
```java
class Solution {
    public String longestCommonPrefix(String[] strs) {

        if(strs.length<=0){
            return "";
        }

        int len = strs[0].length();

        for(int i = 0;i < strs.length;i++){
            if(len > strs[i].length()){
                len = strs[i].length();
            }
        }

        boolean continued = true;
        int index = 0;

        for(int i =0;i < len;i++){
            for(int j =0;j< strs.length-1;j++){
                if(strs[j].charAt(i) == strs[j+1].charAt(i)){
                    continue;
                }else{
                    continued = false;
                }
            }
            if(!continued){
                break;
            }
            index++;
        }

        return strs[0].substring(0,index);
    }
}
```

## 15. 3Sum
+ java O(N<sup>4</sup>)解法,预料之中的超时，但是之前的问题都解决了，应该是正解
+ 248 / 313 test cases passed.
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        //取和
        for(int i = 0;i < nums.length;i++){
            for(int j =0;j < nums.length;j++){
                for(int k=0;k < nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0&&i!=j&&i!=k&&j!=k){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        //判断是否有重复
                        boolean dup = false;
                        for(List<Integer> item:result){
                            //如果item中有一个0，而tmp中有三个0，则，item.containsAll(tmp)也为true
                            //所以此处应改为 tmp.containsAll(item)&&item.containsAll(tmp) 教合适
                            //若仅改为tmp.containsAll(item)也会出现类似的情况
                            if(tmp.containsAll(item)&&item.containsAll(tmp)){
                                dup = true;
                            }
                        }
                        if(!dup){
                            result.add(tmp);
                        }
                    }
                }
            }
        }
        return result;
    }
}
```
+ java，利用排序后的数组来判断是否有重复，还是超时
+ 265 / 313 test cases passed.
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        //取和
        for(int i = 0;i < nums.length;i++){
            for(int j =0;j < nums.length;j++){
                for(int k=0;k < nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==0&&i!=j&&i!=k&&j!=k){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);

                        //判断是否有重复
                        boolean dup = false;
/*                        for(List<Integer> item:result){
                            //如果item中有一个0，而tmp中有三个0，则，item.containsAll(tmp)也为true
                            //所以此处应改为 tmp.containsAll(item)&&item.containsAll(tmp) 教合适
                            //若仅改为tmp.containsAll(item)也会出现类似的情况
                            if(tmp.containsAll(item)&&item.containsAll(tmp)){
                                dup = true;
                            }
                        }*/
                        //通过排序来判断是否有重复
                        Collections.sort(tmp);
                        for(List<Integer> item:result){
                            Collections.sort(item);
                            if(item.get(0)==tmp.get(0)&&item.get(1)==tmp.get(1)&&item.get(2)==tmp.get(2)){
                                dup = true;
                                break;
                            }else{
                                continue;
                            }
                        }
                        if(!dup){
                            result.add(tmp);
                        }
                    }
                }
            }
        }

        return result;
    }
}
```

+ Go语言，这次试用Go语言，相同的思路，结果还是超时
+ 253 / 313 test cases passed.
```go
package main

import (
	"fmt"
	"sort"
)

func threeSum(nums []int) [][]int {

	result := make([][]int,len(nums)*len(nums))
	length := len(nums)
	index :=0

	if(length<=0){
		return result
	}

	for i:=0;i<length;i++{
		for j:=0;j<length;j++{
			for k:=0;k<length;k++{
				if nums[i] + nums[j] + nums[k] == 0 && i !=j && j!=k && i!=k{
					result[index] = make([]int,3)
					result[index][0] = nums[i]
					result[index][1] = nums[j]
					result[index][2] = nums[k]
					if index>0{
						sort.Ints(result[index])
						dup := false
						//为什么是从0到 index+1呢
						for m:=0;m<index;m++{
							sort.Ints(result[m])
							fmt.Println(m,index)
							if result[m][0]==result[index][0]&&result[m][1]==result[index][1]&&result[m][2]==result[index][2]{
								dup = true
								break
							}else{
								continue
							}
						}
						if !dup{
							//fmt.Println(i,j,k)
							index++
						}
					}else{
						index++
					}
				}
			}
		}
	}

	return result[:index]
}

```
+ Go语言，尝试先排序然后再去操作，果然速度变快了
+ 但是还是超时
+ 311 / 313 test cases passed.
```go
import (
	"sort"
)

func threeSum(nums []int) [][]int {
	length := len(nums)

	if length<=0{
		return nil
	}
	result := make([][]int,len(nums)*len(nums))
	index :=0

	sort.Ints(nums)

	for i:=0;i<length;i++{
		for j:=i+1;j<length;j++{
			for k:=j+1;k<length;k++{
				if nums[i] + nums[j] + nums[k] == 0{
					result[index] = make([]int,3)
					result[index][0] = nums[i]
					result[index][1] = nums[j]
					result[index][2] = nums[k]
					if index>0{
						//sort.Ints(result[index])
						dup := false
						//为什么是从0到 index+1呢
						for m:=0;m<index;m++{
							//sort.Ints(result[m])
							//fmt.Println(m,index)
							if result[m][0]==result[index][0]&&result[m][1]==result[index][1]&&result[m][2]==result[index][2]{
								dup = true
								break
							}
						}
						if !dup{
							//fmt.Println(i,j,k)
							index++
						}
					}else{
						index++
					}
				}
			}
		}
	}

	return result[:index]
}
```

+ 别人的解法，java,据说是O(N<sup>2</sup>)
+ Runtime: 71 ms, faster than 40.69% of Java online submissions for 3Sum.
```java

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        //首先对整个数组进行排序
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        //第一重循环，从下标 0 到 num.length-2
        for (int i = 0; i < num.length - 2; i++) {
            //去重，如果 num[i] 和 num[i-1]相同则跳过
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                //num[i] + num[i+1]+num[hi] = 0,num[i+1]+num[hi] = 0 - num[i]
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        //这两行去重
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }
}
```
+ Go语言版本，思路和上面的java一样，但是还是超时
+ 311 / 313 test cases passed.
```go
import (
	"sort"
)

func threeSum(nums []int) [][]int {
	length := len(nums)

	if length<=0{
		return nil
	}
	result := make([][]int,len(nums)*len(nums))
	index :=0

	sort.Ints(nums)
//	fmt.Println(nums)

	for i:=0;i<length;i++{
		//如果没有 nums[i] !=nums[i-1] ,则会有重复 [-4 -1 -1 0 1 2]
		if i==0 || (i >0 && nums[i] !=nums[i-1]){
			low := i+1
			high := length -1
			sum := 0 - nums[i]
			for ;low<high;{
				if nums[low]+nums[high] == sum{
					result[index] = make([]int,3)
					result[index][0] = nums[i]
					result[index][1] = nums[low]
					result[index][2] = nums[high]
					index++
					for;low<high&&nums[low] == nums[low+1];{
						low++
					}
					for;low<high&&nums[high] == nums[high-1];{
						high--
					}
					low++
					high--
				}else if nums[low] + nums[high] < sum{
					low++
				}else{
					high--
				}
			}
		}
	}

	return result[:index]
}
```
+ Go语言，之前看错了，不是超时，是之前的数组申请方式不对，超内存了
+ 但是时间和java解法还是差很多
+ Runtime: 1020 ms, faster than 37.15% of Go online submissions for 3Sum.
```go
import (
	"fmt"
	"sort"
)

func threeSum(nums []int) [][]int {
	length := len(nums)

	if length<=0{
		return nil
	}
	//这里可以先申请一个空的slice
	result := make([][]int,0)
	index :=0

	sort.Ints(nums)
	//fmt.Println(nums)

	for i:=0;i<length;i++{
		//如果没有 nums[i] !=nums[i-1] ,则会有重复 [-4 -1 -1 0 1 2]
		if i==0 || (i >0 && nums[i] !=nums[i-1]){
			low := i+1
			high := length -1
			sum := 0 - nums[i]
			for ;low<high;{
				if nums[low]+nums[high] == sum{
					//不断的向slice追加元素
					tmp := make([]int,3)
					result = append(result,tmp)
					result[index][0] = nums[i]
					result[index][1] = nums[low]
					result[index][2] = nums[high]
					index++
					for;low<high&&nums[low] == nums[low+1];{
						low++
					}
					for;low<high&&nums[high] == nums[high-1];{
						high--
					}
					low++
					high--
				}else if nums[low] + nums[high] < sum{
					low++
				}else{
					high--
				}
			}
		}
	}

	return result[:index]
}
```

## 16. 3Sum Closest
+ 挺简单的一道题目，结果做了一个多小时，思路不清晰
+ 一个调节思路的方法是编写程序边写注释
+ Runtime: 12 ms, faster than 73.82% of Java online submissions for 3Sum Closest.
```java
import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {

        int result = 0;
        int distance = Integer.MAX_VALUE;
        //先从小打到大排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                //求出三个数的和与target的差,即距离
                //target >0
                int tmp;
                tmp = nums[i] + nums[low] + nums[high] - target;
               // System.out.println(i + " "+low + " "+high+" "+tmp+" "+result+" "+distance);

                //如果差<0
                if (tmp < 0) {
                    tmp = -tmp;
                    if (tmp < distance) {
                        result = -tmp + target;
                        distance = tmp;
                    }
                    low++;
                } else {  //如果差>0
                    if (tmp < distance) {
                        result = tmp + target;
                        distance = tmp;
                    }
                    high--;
                }
            }
        }


        return result;
    }
}

```

## 17. Letter Combinations of a Phone Number
+ Go语言解法，使用切片和map
+ 25 / 25 test cases passed.
+ runtime beats 100.00 % of golang submissions
```go

import "fmt"

func combine(result []string,runes []string) []string{
	//fmt.Println(result)
	//fmt.Println(runes)
	//对一开始，第一个字符进行特殊处理
	if len(result)==0{
		for j:=0;j<len(runes);j++{
			result = append(result,runes[j])
		}
	}else{//从第二个字符开始，依次为之前的字符，加上当前字符数组的每个元素，append到result中
		lenResult := len(result)
		for i:=0; i<lenResult; i++{
			for j:=0;j<len(runes);j++{
				result = append(result,result[i]+runes[j])
			}
		}
	}
	//fmt.Println(result)
	return result
}

func letterCombinations(digits string) []string {
	//用来存储结果
	result := []string{}

	if(len(digits)==0){
		return result
	}

	//首先，将数字和字母的映射关系写好
	maps := map[rune][]string{
		'0':{},
		'2':{"a","b","c"},
		'3':{"d","e","f"},
		'4':{"g","h","i"},
		'5':{"j","k","l"},
		'6':{"m","n","o"},
		'7':{"p","q","r","s"},
		'8':{"t","u","v"},
		'9':{"w","x","y","z"},
	}

	//计算真正的结果中包含的元素个数，即，字符串长度为len(digits)的个数
	index := 1
	for _,digit := range []rune(digits){
		//fmt.Println(index,maps[ditit])
		result = combine(result,maps[digit])
		index = index * len(maps[digit])
	}
	//fmt.Println("over")
	//fmt.Println(maps)
	//fmt.Println(result)
	//从结果数组去，除去前面长度不足的元素
	return result[len(result)-index:]
}

func main() {
	fmt.Println(letterCombinations("234"))
}
```

## 18. 4Sum
+ 4 Sum好难，没有做出来，看别人的代码也似懂非懂
+ java,未完成，我自己代码
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //首先对原输入数组进行排序
        Arrays.sort(nums);

        for(int tmp:nums){
            System.out.printf(tmp+" ");
        }
        System.out.println();
        //用来存储最后的结果
        List<List<Integer>>  result = new ArrayList<>();

        for(int i = 0;i < nums.length-3;i++){
            //如果出现了重复的i,跳过
            if(i>0&&nums[i]==nums[i-1]){
                i++;
            }

            for(int j = i+1;j < nums.length-2;j++){     //二重循环，遍历i,j
                //while循环，遍历low,high
                //如果出现了重复的j,跳过去一个
                while(j<nums.length-2&&j>1&&nums[j]==nums[j+1]){
                    j++;
                }
                int low = j+1,high = nums.length-1;

                // System.out.println(i+" "+j);
               while(low<high){
                  // System.out.println(low+" "+high);
                    int tmp = nums[i]+nums[j]+nums[low]+nums[high];
                    if(tmp - target==0){
                        result.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
                        System.out.println(i+" "+j+" "+low+" "+high);
                        low++;
                        high--;
                    }else if(tmp - target >0){
                        high--;
                    }else{
                        low++;
                    }

                   //这个题目不需要对后面的元素进行过滤，因为他们就是不同的
                }
            }
        }
        return result;
    }
}
```
+ 别人的思路和解法
+ 如果你已经通过使用排序的方法实现了3sum和4sum：最后将它们减少到2sum,你可能已经感到
所有的ksumd都可以分为两个问题
    +  2sum问题
    + 将k sum 和问题减少到k-1 sum和问题
    
+ 使用递归的方法解题
```java
    public class Solution {
        int len = 0;
        public List<List<Integer>> fourSum(int[] nums, int target) {
            len = nums.length;
            Arrays.sort(nums);
            return kSum(nums, target, 4, 0);
        }
       private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
            ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
            if(index >= len) {
                return res;
            }
            if(k == 2) {
            	int i = index, j = len - 1;
            	while(i < j) {
                    //find a pair
            	    if(target - nums[i] == nums[j]) {
            	    	List<Integer> temp = new ArrayList<>();
                    	temp.add(nums[i]);
                    	temp.add(target-nums[i]);
                        res.add(temp);
                        //skip duplication
                        while(i<j && nums[i]==nums[i+1]) i++;
                        while(i<j && nums[j-1]==nums[j]) j--;
                        i++;
                        j--;
                    //move left bound
            	    } else if (target - nums[i] > nums[j]) {
            	        i++;
                    //move right bound
            	    } else {
            	        j--;
            	    }
            	}
            } else{
                for (int i = index; i < len - k + 1; i++) {
                    //use current number to reduce ksum into k-1sum
                    ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1);
                    if(temp != null){
                        //add previous results
                        for (List<Integer> t : temp) {
                            t.add(0, nums[i]);
                        }
                        res.addAll(temp);
                    }
                    while (i < len-1 && nums[i] == nums[i+1]) {
                        //skip duplicated numbers
                        i++;
                    }
                }
            }
            return res;
        }
    }
    
```