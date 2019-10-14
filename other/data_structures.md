# 快速排序
```java
import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();

        //如果k > 输入数组的长度。返回所有元素还是返回空？
        if(k>input.length){
            return result;
        }

        quickSort(input,0,input.length-1);

        for(int i=0;i<k;i++){
            result.add(input[i]);
        }

        return result;
    }

    //快速排序
    static void quickSort(int[] input,int left,int right){
        if(left>=right){
            return;
        }

        int p = input[left];

        int i = left;
        int j = right;

        while (i<j){
            while(input[j]>=p&&i<j){
                j--;
            }
            while(input[i]<=p&&i<j){
                i++;
            }

            if(i<j){
                int tmp = input[i];
                input[i] = input[j];
                input[j] = tmp;
            }
        }

        input[left] = input[i];
        input[i] = p;
        quickSort(input,left,i-1);
        quickSort(input,i+1,right);
    }
}
```
# 堆排序算法
```java
public class Sort {
    public static void main(String[] args) {
        int[] nums = {16,7,3,20,17,8};
        headSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    /**
     * 堆排序
     */
    public static void headSort(int[] list) {
        //构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = (list.length) / 2 - 1; i >= 0; i--) {
            headAdjust(list, list.length, i);
        }
        //排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = list.length - 1; i >= 1; i--) {
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;
            headAdjust(list, i, 0);
        }
    }
    
    private static void headAdjust(int[] list, int len, int i) {
        int k = i, temp = list[i], index = 2 * k + 1;
        while (index < len) {
            if (index + 1 < len) {
                if (list[index] < list[index + 1]) {
                    index = index + 1;
                }
            }
            if (list[index] > temp) {
                list[k] = list[index];
                k = index;
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        list[k] = temp;
    }
}
```

# 归并排序

# 
```java

package yudylaw.java.sorted;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 输出1,2,2,3,4,5的所有排列组合,4不能在第三位,3和5不能相邻
 * 
 * 采用字符跟踪
 * @author ****
 * 10:56:38 PM Apr 23, 2009
 */
public class Sorted {
	
	public static List<String> find(List<String> list) {
		List<String> rtn = new ArrayList<String>();
		String str;
		for (int i = 0; i < list.size(); i++) {
			str = list.get(i); 
			list.remove(i);
			if (list.size() == 0) { 
				rtn.add(str);
			} else {
				List<String> sList = find(list); 
				for (String s : sList) {
					rtn.add(str + s); 
					if (s.length() == 5) {
						addNumber(str + s);
					}
				}
			}
			list.add(i, str);
		}
		return rtn; 
	}

	/**
	 * 通过这个来排除
	 * @param str
	 */
	public static void addNumber(String str) {
		if (str.charAt(2) == '4' || str.contains("35") || str.contains("53")) {
			return;
		}
		set.add(str);
	}

	public static Set<String> set = new TreeSet<String>();

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		find(list);
		System.out.println(set.size());
		int cols = 10;
		for (String s : set) {
			System.out.print(s + " ");
			if (cols-- == 1) {
				System.out.println();
				cols = 10;
			}
		}
	}
}

```
# 递归反转字符串
```java
public class Main {
    //反转字符串
    public static String reverseString(String s){
        if(s.isEmpty()) return s;
        return reverseString(s.substring(1))+s.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(reverseString("123456789"));
    }
}

```