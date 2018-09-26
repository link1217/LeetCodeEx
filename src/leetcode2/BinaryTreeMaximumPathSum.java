package leetcode2;

import others.TreeNode;

/**
 * 124. Binary Tree Maximum Path Sum
 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        help(root);
        return res;
    }

    private int res = Integer.MIN_VALUE;

    private int help(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int rootVal = root.val;
        int leftVal = Math.max(help(root.left), 0);
        int rightVal = Math.max(0, help(root.right));
        res = Math.max(res, rootVal + leftVal + rightVal);
        return rootVal + Math.max(leftVal, rightVal);
    }
}
