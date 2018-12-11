class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode newList = null;

        if(lists.length==1){
            return lists[0];
        }

        for(int i=0;i < lists.length-1;i++){
            newList = mergeTwoLists(lists[i],lists[i+1]);
            if(i+1 < lists.length){
                lists[i+1] = newList;
            }
        }
        return newList;
    }

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
    public static void main(String[] args){
        int[] nums = {3,2,2,3};
       // System.out.println(new Solution().mergeKLists("hello","ll"));
    }
}