# 二维数组中的查找
```java
public class Solution {
    public boolean Find(int target, int [][] array) {
        if(array.length ==0){
            return false;
        }
        int row = array.length-1;
        int column = 0;
        
        while(column < array[0].length&&row>=0){
            if(array[row][column] == target){
                return true;
            }else if(array[row][column] > target){
                row--;
            }else{
                column++;
            }
        }
        return false;
    }
}

```
# 替换空格
```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ","%20");
    }
}
```
# 从尾到头遍历单向链表
```java
class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Stack<Integer> listStack = new Stack<>();
        ListNode ptr = listNode;

        while(ptr!=null){
            listStack.push(ptr.val);
            ptr = ptr.next;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!listStack.isEmpty()){
            result.add(listStack.pop());
        }
        return result;
    }
}
```