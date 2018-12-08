
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        while (ptr1!=null||ptr2!=null){
            if(ptr1!=null&&ptr2!=null){
                if(ptr1.val>ptr2.val){
                    ptr.next = new ListNode(ptr2.val);
                    ptr2 = ptr2.next;
                    ptr = ptr.next;
                }else{
                    ptr.next = new ListNode(ptr1.val);
                    ptr1 = ptr1.next;
                    ptr = ptr.next;
                }
            }else if(ptr1!=null&&ptr2==null){
                ptr.next = new ListNode(ptr1.val);
                ptr1 = ptr1.next;
                ptr = ptr.next;
            }else if(ptr1==null&&ptr2!=null){
                ptr.next = new ListNode(ptr2.val);
                ptr2 = ptr2.next;
                ptr = ptr.next;
            }
        }
        head = head.next;

    return head;
    }
}

public class MainClass {

}