package leetcode2;

import others.TreeNode;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private int inIndex = 0, posIndex = 0;

    /**
     * 递归版，耗时1ms
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, Integer.MAX_VALUE);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int val) {
        if (inIndex >= inorder.length || inorder[inorder.length - inIndex - 1] == val)
            return null;
        TreeNode root = new TreeNode(postorder[postorder.length - ++posIndex]);
        root.right = buildTree(inorder, postorder, root.val);
        inIndex++;
        root.left = buildTree(inorder, postorder, val);
        return root;
    }
}
