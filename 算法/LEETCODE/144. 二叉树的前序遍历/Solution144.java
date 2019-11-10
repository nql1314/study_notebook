import java.util.ArrayList;
import java.util.List;

public class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root,res);
        return  res;
    }

    void helper(TreeNode node, List res) {
        if (node == null) return;
        res.add(node.val);
        helper(node.left, res);
        helper(node.right, res);
    }
}
