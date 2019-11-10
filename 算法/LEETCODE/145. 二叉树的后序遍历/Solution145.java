import java.util.ArrayList;
import java.util.List;

public class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root,res);
        return  res;
    }

    void helper(TreeNode node, List res) {
        if (node == null) return;
        helper(node.left, res);
        helper(node.right, res);
        res.add(node.val);
    }
}
