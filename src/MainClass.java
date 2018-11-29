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
public class MainClass {
    public static void main(String[] args){
        int[] tmp = {1,8,6,2,5,4,8,3,7};
        int x = new Solution().maxArea(tmp);
        System.out.println(x);

    }
}