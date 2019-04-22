import java.util.Stack;

public class Solution{
    /*
     解法1，非递归的中序遍历
     修改当前结点与前一遍历结点的指针指向
     */
    public TreeNode Convert(TreeNode pRootOfTree){
        if(pRootOfTree==null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = pRootOfTree;
        TreeNode pre = null;//保存中序遍历序列的上一结点
        boolean isFirst = true;

        while(ptr!=null||!stack.isEmpty()){
            while (ptr!=null){
                stack.push(ptr);
                ptr = ptr.left;
            }
            ptr = stack.pop();
            if(isFirst){
                pRootOfTree = ptr;
                pre = pRootOfTree;
                isFirst = false;
            }else{
                pre.right = ptr;
                ptr.left = pre;
                pre = ptr;
            }
            ptr = ptr.right;
        }
        return pRootOfTree;
    }
}