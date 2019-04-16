import java.util.ArrayList;
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if(root==null){
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<TreeNode> nodes = new ArrayList<>();

        nodes.add(root);
        while (result.size()!=nodes.size()){
            for(int i=0;i<nodes.size();i++){
                TreeNode tmp = nodes.get(i);
                result.add(tmp.val);
                if(tmp.left!=null){
                    nodes.add(tmp.left);
                }
                if(tmp.right!=null){
                    nodes.add(tmp.right);
                }
            }
        }
        return result;
    }
}