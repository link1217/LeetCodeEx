package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Watcher
 *
 */
public class PermutationSequence {

	public static void main(String[] args) {
		PermutationSequence so = new PermutationSequence();
		System.out.println(so.getPermutation2(9, 101134));
	}

	public String getPermutation(int n, int k) {
		
		
		return null;
	}

	/**
	 * 回溯，超时
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation2(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		return backtrack(n, k, res, new ArrayList<Integer>());
	}

	private String backtrack(int n, int k, List<List<Integer>> res, ArrayList<Integer> list) {
		if (list.size() == n) {
			res.add(new ArrayList<>(list));
			return null;
		}
		for (int i = 1; i <= n; i++) {
			if (list.contains(i))
				continue;
			list.add(i);
			backtrack(n, k, res, list);
			if (res.size() == k) {
				StringBuilder sb = new StringBuilder();
				for (Integer cur : res.get(k - 1))
					sb.append(cur);
				return sb.toString();
			}
			list.remove(list.size() - 1);
		}
		return null;
	}
}
