package leetcode;

/**
 * LeetCode problems 11--20
 * 
 * @author Watcher
 *
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(10 % 10);
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

	/**
	 * 12. Integer to Roman
	 * 
	 * Roman numerals are represented by seven different symbols: I, V, X, L, C,
	 * D and M.
	 * 
	 * @param num
	 * @return
	 */
	public String intToRoman2(int num) {
		String res = "";
		int m = 0, n = 1;
		while (num > 0) {
			m = (num % 10) * n;
			num /= 10; // 整数右移一位
			n *= 10; // n为补偿数
			if (m == 0)
				continue;
			String cur = toRoman(m);
			res = cur + res;
		}

		return res;
	}

	private String toRoman(int m) {
		String[] nums1 = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }; // 1--9
		String[] nums2 = { "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }; // 10--90
		String[] nums3 = { "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // 100--900
		String[] nums4 = { "M", "MM", "MMM" }; // 1000--3000
		if (m >= 1 && m <= 9) {
			return nums1[m - 1];
		}
		if (m >= 10 && m <= 90) {
			return nums2[m / 10 - 1];
		}
		if (m >= 100 && m <= 900) {
			return nums3[m / 100 - 1];
		}
		if (m >= 1000 && m <= 3000) {
			return nums4[m / 1000 - 1];
		}
		return null;
	}

	public String intToRoman(int num) {
		String[] nums1 = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }; // 1--9
		String[] nums2 = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }; // 10--90
		String[] nums3 = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // 100--900
		String[] nums4 = { "", "M", "MM", "MMM" }; // 1000--3000
		return nums4[num / 1000] + nums3[(num % 1000) / 100] + nums2[(num % 100) / 10] + nums1[num % 10];

	}

	/**
	 * 13. Roman to Integer
	 * 
	 * Roman numerals are represented by seven different symbols: I, V, X, L, C,
	 * D and M.
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt2(String s) {
		int sum = 0;
		if (s.indexOf("IV") != -1 || s.indexOf("IX") != -1)
			sum -= 2;
		if (s.indexOf("XL") != -1 || s.indexOf("XC") != -1)
			sum -= 20;
		if (s.indexOf("CD") != -1 || s.indexOf("CM") != -1)
			sum -= 200;
		char[] cs = s.toCharArray();
		for (char c : cs) {
			switch (c) {
			case 'M':
				sum += 1000;
				break;
			case 'D':
				sum += 500;
				break;
			case 'C':
				sum += 100;
				break;
			case 'L':
				sum += 50;
				break;
			case 'X':
				sum += 10;
				break;
			case 'V':
				sum += 5;
				break;
			case 'I':
				sum += 1;
				break;
			default:
				break;
			}
		}
		return sum;

	}

	public int romanToInt(String s) {
		int nums[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'M':
				nums[i] = 1000;
				break;
			case 'D':
				nums[i] = 500;
				break;
			case 'C':
				nums[i] = 100;
				break;
			case 'L':
				nums[i] = 50;
				break;
			case 'X':
				nums[i] = 10;
				break;
			case 'V':
				nums[i] = 5;
				break;
			case 'I':
				nums[i] = 1;
				break;
			}
		}
		int sum = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] < nums[i + 1])
				sum -= nums[i];
			else
				sum += nums[i];
		}
		return sum + nums[nums.length - 1];
	}
}
