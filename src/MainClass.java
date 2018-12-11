class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode headPtr = head;

        while (headPtr!=null){
            headPtr = reverseGroup(headPtr,k);
            headPtr = headPtr.next;
        }

        return null;
    }

    private ListNode reverseGroup(ListNode headPtr, int k) {
        ListNode tmpHead = headPtr;
        int index=0;
        while (tmpHead!=null){
            index++;
            tmpHead = tmpHead.next;
            if(index==k){
                break;
            }
        }
        return tmpHead;
    }


}
public class MainClass {
    public static void main(String[] args){
        int[] nums = {3,2,2,3};
       // System.out.println(new Solution().mergeKLists("hello","ll"));
    }
}