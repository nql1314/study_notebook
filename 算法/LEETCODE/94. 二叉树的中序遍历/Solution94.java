import java.util.ArrayList;
import java.util.List;

public class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        help(root, res);
        return res;
    }

    void help(TreeNode node, List res) {
        if (node == null) return;
        help(node.left, res);
        res.add(node.val);
        help(node.right, res);
    }
}