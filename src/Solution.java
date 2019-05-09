import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        List<TreeNode> tmp = new ArrayList<>();
        tmp.add(root);
        for(int i=0;i<tmp.size();i++){
            int deep = 0;

            if(root.left!=null){
                tmp.add(root.left);
            }
            if(root.right!=null){
                tmp.add(root.right);
            }
            deep++;
        }
        return true;
    }
}