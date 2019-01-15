<<<<<<< HEAD
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> resut = new LinkedList<>();
        List<Integer> firstIndex = new LinkedList<>();

        for (int i = 0; i < words.length; i++) {
            int index = s.indexOf(words[i]);
            if (index > -1) {
                if (!firstIndex.contains(index)) {
                    firstIndex.add(index);
                } else {
                    String tmp = s.substring(index, index + words[i].length());
                    index = tmp.indexOf(words[i]);
                    if (index > -1) {
                        index += words[i].length() + index;
                        firstIndex.add(index);
                    } else {
                        return resut;
                    }
                }
            }
        }

        String tmp = s;

        generateAllString(words, 0, words.length);

        return resut;
    }

    private void generateAllString(String[] words, int start, int end) {

=======
class Solution {
    public int searchInsert(int[] nums, int target) {

        int result = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=target){
                return i;
            }
        }

        return nums.length;
>>>>>>> c7b04e2e7585a94091f504028ddf3642e80d3ad8
    }
}

public class MainClass {

    public static void main(String[] args) {
        String a = "acb";
        String b = a;
        b = b.substring(1);
        System.out.println(a);
        System.out.println(b);

        ListNode nodea = new ListNode(1);
        ListNode nodeb = nodea;
        nodeb.val = 2;
        System.out.println(nodea.val);
    }
}