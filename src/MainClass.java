import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists==null||lists.length==0){
            return null;
        }

        List<Integer> nums = new LinkedList<>();

        for(int i=0;i <lists.length;i++){
            ListNode ptr = lists[i];
            while (ptr!=null){
                nums.add(ptr.val);
                ptr = ptr.next;
            }
        }
        Collections.sort(nums);
        if(nums.size()>0){
            ListNode head = new ListNode(nums.get(0));
            ListNode ptr = head;

            for(int i=1;i<nums.size();i++){
                ptr.next = new ListNode(nums.get(i));
                ptr = ptr.next;
            }

            return head;
        }else{
            return null;
        }
    }
}
public class MainClass {
    public static void main(String[] args){

    }
}