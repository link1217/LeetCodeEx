package leetcode;

/**
 * LeetCode problems 11--20
 * 
 * @author Watcher
 *
 */
public class Solution2 {

	public static void main(String[] args) {

	}

	/**
	 * 11. Container With Most Water
	 * 
	 * Given n non-negative integers a1, a2, ..., an, where each represents a
	 * point at coordinate (i, ai). n vertical lines are drawn such that the two
	 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
	 * together with x-axis forms a container, such that the container contains
	 * the most water.
	 * 
	 * Note: You may not slant the container and n is at least 2.
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int area = 0;
		int len = height.length;
		int left = 0, right = len - 1;
		while (left < right) {
			int lh = height[left];
			int rh = height[right];
			if (lh > rh) {
				area = Math.max(area, (right - left) * rh);
				right--;
			} else {
				area = Math.max(area, (right - left) * lh);
				left++;
			}
		}
		return area;
	}
}
