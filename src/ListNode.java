import javax.swing.*;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x){val = x;}
}
/*
class Solution{
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){

        ListNode head = new ListNode(l1.val+l2.val);

        if(l1.next==null){
            return head;
        }else {
            while(l1.next!=null){
                l1 = l1.next;
                l2 = l2.next;
                head.next = new ListNode(l1.val+l2.val);
            }
        }

        return head;
    }
}*/
