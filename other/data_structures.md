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