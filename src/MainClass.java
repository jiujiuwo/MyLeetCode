/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode headPtr = head;
        ListNode result = null;
        ListNode resultPtr = null;

        //检查特殊的输入，例如，整个list的长度不足k,或者k<2则直接返回原list
        int length = 0;
        while (headPtr!=null){
            length++;
            headPtr = headPtr.next;
        }
        if(length<k||k<2){
            return head;
        }

        headPtr = head;

        while (headPtr != null) {
            //每k个节点转换一次，每次转化完的新数组，返回下一组的首地址
            ListNode[] results = reverseGroup(result,headPtr,k);
            result = results[0];
            headPtr = results[1];
        }

        return result;
    }
    /*
        result 为结果数组
        headPtr 为当前位置的指针
        k 为原输入中的k
     */
    private ListNode[] reverseGroup(ListNode result,ListNode headPtr,int k) {
        //nums用来存放带逆转的节点值
        ListNode[] newNodes = new ListNode[k];

        //将新节点进行串联
        for (int i = 0; i < k; i++) {
            newNodes[i] = new ListNode(0);
        }
        for (int i = 0; i < k - 1; i++) {
            newNodes[i].next = newNodes[i + 1];
        }
        //一开始，它指向原来的数组，后来，它指向新的数组
        ListNode tmpHead;
        ListNode pre = null;
        if(result==null){
             tmpHead = headPtr;
        }else{
            //要改变result后面的元素，因此要定位到最后的位置
            pre = headPtr;
            tmpHead = headPtr.next;
        }
        int index = k - 1;

        while (tmpHead != null) {
            //倒序开始给新的list赋值
            newNodes[index].val = tmpHead.val;
            index--;
            //指针向后移1
            tmpHead = tmpHead.next;
            //如果index减小到了0以下
            if (index < 0) {    //如果index减小到了0，说明这组可以转换，返回的头信心指向下一个
                if(result==null){
                    result = newNodes[0];
                }else{
                   pre.next = newNodes[0];
                }
                newNodes[k - 1].next = tmpHead;
                headPtr =  newNodes[k - 1];
                break;
            }
        }

        if(index>=0){
            headPtr = tmpHead;
        }

        newNodes[0] = result;
        newNodes[1] = headPtr;
        return newNodes;
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
        for (int index = 0; index < parts.length; index++) {
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
        for (int item : nodeValues) {
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
            int k = Integer.parseInt(line);

            ListNode ret = new Solution().reverseKGroup(head, k);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}