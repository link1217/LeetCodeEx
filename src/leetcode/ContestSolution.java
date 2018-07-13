package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * 每周评测
 * 
 * @author Watcher
 *
 */
public class ContestSolution {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);

		root.right = new TreeNode(1);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);

		System.out.println(new ContestSolution().subtreeWithAllDeepest(root).val);
	}

	/**
	 * 867. Transpose Matrix
	 * 
	 * Given a matrix A, return the transpose of A.
	 * 
	 * The transpose of a matrix is the matrix flipped over it's main diagonal,
	 * switching the row and column indices of the matrix.
	 * 
	 * @param A
	 * @return
	 */
	public int[][] transpose(int[][] A) {
		int m = A.length, n = A[0].length; // m*n 转换为n*m
		int[][] res = new int[n][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				res[j][i] = A[i][j];
			}
		}
		return res;

	}

	/**
	 * 865. Smallest Subtree with all the Deepest Nodes
	 * 
	 * Given a binary tree rooted at root, the depth of each node is the
	 * shortest distance to the root.
	 * 
	 * A node is deepest if it has the largest depth possible among any node in
	 * the entire tree.
	 * 
	 * The subtree of a node is that node, plus the set of all descendants of
	 * that node.
	 * 
	 * Return the node with the largest depth such that it contains all the
	 * deepest nodes in its subtree.
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return root;
		HashMap<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		HashSet<TreeNode> set = new HashSet<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			list.clear();
			while (size-- > 0) {
				root = queue.poll();
				list.add(root);
				if (root.left != null) {
					queue.offer(root.left);
					map.put(root.left, root);
				}
				if (root.right != null) {
					queue.offer(root.right);
					map.put(root.right, root);
				}
			}

		}
		if (list.size() == 1)
			return list.get(0);
		for (TreeNode n : list) {
			set.add(map.get(n));
		}
		while (set.size() > 1) {
			list = new ArrayList<TreeNode>(set);
			set.clear();
			for (TreeNode n : list) {
				set.add(map.get(n));
			}
		}

		return new ArrayList<TreeNode>(set).get(0);
	}

	public TreeNode subtreeWithAllDeepest2(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return root;
		while (root != null) {
			int left = depthOfNode(root.left, 1);
			int right = depthOfNode(root.right, 1);
			if (left == right)
				return root;
			root = left > right ? root.left : root.right;
		}
		return root;
	}

	private int depthOfNode(TreeNode n, int dep) {
		if (n == null)
			return dep - 1;
		return Math.max(depthOfNode(n.left, dep + 1), depthOfNode(n.right, dep + 1));
	}
}
