class Solution {
    public ListNode swapPairs(ListNode head) {

        if(head==null){
            return head;
        }

        ListNode ptr=head;
        int index =0;
        while (ptr!=null){
            if(index % 2 == 0){
                if(ptr.next!=null){
                    int tmp = ptr.val;
                    ptr.val = ptr.next.val;
                    ptr.next.val = tmp;
                }
                ptr = ptr.next;
            }else{
                ptr = ptr.next;
            }
            index++;
        }

        return null;
    }
}
public class MainClass {
    public static void main(String[] args){

    }
}