import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int[] sum = new int[n+1];
        int max = nums[0];

        for(int i=0;i<n;i++){
            if(i==0){
                sum[0] = nums[0];
            }else{
                sum[i] = sum[i-1]+nums[i];
            }
        }

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                max = Math.max(max,sum[j]-sum[i]);
            }
        }

        return max;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

           // boolean ret = new Solution().isPalindrome(head);

          //  String out = booleanToString(ret);

           // System.out.print(out);
        }
    }
}