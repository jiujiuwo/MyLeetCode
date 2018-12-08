import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

//这道题目一开始就理解错了题意，看错了，题目是要删除倒数第n个元素
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(n<=0||head==null){
            return head;
        }else{
            List<ListNode> nodes = new LinkedList<>();
            ListNode ptr;
            ptr = head;
            while(ptr!=null){
                nodes.add(ptr);
                ptr = ptr.next;
            }

            //如果要删除的 倒数第n个元素比整个链表的大小要小，则取到链表的前一个，然后node.next = node.next.next
            if(nodes.size()>n){
                ListNode nodeN = nodes.get(nodes.size()-n-1);
                if(nodeN.next!=null){
                    nodeN.next = nodeN.next.next;
                }
                //否则，就是n与元素个数相同，则，没有前一个元素，因此是直接将头部去掉
            }else if(nodes.size()==n){
                head = head.next;
            }
        }
        return head;
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

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            ListNode ret = new Solution().removeNthFromEnd(head, n);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}