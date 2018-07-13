package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import java.util.PriorityQueue;

/**
 * LeetCode problems 1--10
 * @author Watcher
 *
 */
public class Solution1 {

	private String str;

	public static void main(String[] args) {
		Solution1 so = new Solution1();

		System.out.println(-100 % 10);

		// System.out.println(so.convert("PAYPALISHIRING", 4));

		// System.out.println(so.longestPalindrome("aa"));
		// System.out.println(so.maxLcpsLength2("addsssa"));

		// System.out.println("11".substring(0, 0).length());

		/*
		 * ListNode l1 = new ListNode(1); ListNode l2 = new ListNode(9); l2.next
		 * = new ListNode(9); l2.next.next = new ListNode(9);
		 * so.addTwoNumbers(l1, l2);
		 */

		// int[] arr = { 2, 7, 11, 15 };
		// System.out.println(Arrays.toString(so.twoSum2(arr, 26)));

		// System.out.println(Double.parseDouble("-1."));

		// System.out.println(so.isNumber3("1"));

		// System.out.println(so.myAtoi("-91283472332"));

	}

	

	/**
	 * 9. Palindrome Number
	 * 
	 * Determine whether an integer is a palindrome. An integer is a palindrome
	 * when it reads the same backward as forward.
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int i = 0;
		char[] cs = (x + "").toCharArray();
		int len = cs.length;
		while (i != len / 2) {
			if (cs[i] != cs[len - i - 1])
				return false;
			i++;
		}
		return true;
	}

	/**
	 * 9. Palindrome Number
	 * 
	 * Determine whether an integer is a palindrome. An integer is a palindrome
	 * when it reads the same backward as forward.
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome2(int x) {
		return new StringBuilder(x + "").reverse().toString().equals(x + "");
	}

	/**
	 * 7. Reverse Integer
	 * 
	 * Given a 32-bit signed integer, reverse digits of an integer.
	 * 
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		long res = 0;
		while (x != 0) {
			res = (res << 1) + (res << 3) + x % 10;
			x /= 10;
		}
		if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
			return 0;
		else
			return (int) res;
	}

	/**
	 * 7. Reverse Integer
	 * 
	 * Given a 32-bit signed integer, reverse digits of an integer.
	 * 
	 * @param x
	 * @return
	 */
	public int reverse2(int x) {
		int s = x >= 0 ? 1 : -1;
		String str = s == 1 ? x + "" : (x + "").substring(1);
		try {
			return Integer.parseInt(new StringBuilder(str).reverse().toString()) * s;
		} catch (Exception e) {
			return 0;
		}
	}

	public String convert(String s, int nRows) {
		if (s == null || s.length() == 0 || nRows <= 0)
			return "";
		if (nRows == 1)
			return s;

		StringBuilder res = new StringBuilder();
		int size = 2 * nRows - 2;
		for (int i = 0; i < nRows; i++) {
			for (int j = i; j < s.length(); j += size) {
				res.append(s.charAt(j));
				if (i != 0 && i != nRows - 1) {// except the first row and the
												// last row
					int temp = j + size - 2 * i;
					if (temp < s.length())
						res.append(s.charAt(temp));
				}
			}
		}
		return res.toString();
	}

	/**
	 * 5. Longest Palindromic Substring
	 * 
	 * Given a string s, find the longest palindromic substring in s. You may
	 * assume that the maximum length of s is 1000.
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		int start = 0, end = 0;
		char[] cs = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(cs, i, i);
			int len2 = expandAroundCenter(cs, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(char[] cs, int left, int right) {
		while (left >= 0 && right <= cs.length - 1 && cs[left] == cs[right]) {
			left--;
			right++;
		}
		return right - left - 1;
	}

	/**
	 * 5. Longest Palindromic Substring
	 * 
	 * Given a string s, find the longest palindromic substring in s. You may
	 * assume that the maximum length of s is 1000.
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome3(String s) {
		return maxLcpsLength(s).replace("#", "");
	}

	public char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	public String maxLcpsLength(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() <= 1)
			return str;
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int left = 0, end = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			// max = Math.max(max, pArr[i]);
			if (max < pArr[i]) {
				max = pArr[i];
				end = i + max;
				left = i - max + 1;
			}
		}
		return new String(Arrays.copyOfRange(charArr, left, end));
	}

	public int maxLcpsLength2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			// max = Math.max(max, pArr[i]);
			if (max < pArr[i]) {
				max = pArr[i];
				System.out.println(max - 1 + "--" + i);
			}
		}
		return max - 1;
	}

	/**
	 * 5. Longest Palindromic Substring
	 * 
	 * Given a string s, find the longest palindromic substring in s. You may
	 * assume that the maximum length of s is 1000.
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if (s == null)
			return null;
		if (s.length() <= 1)
			return s;
		char[] cs = s.toCharArray();
		int start = 0, end = 0, len = cs.length;
		int[][] dp = new int[len][len];
		for (int i = 0; i < len - 1; i++) {
			dp[i][i] = 1;
			if (cs[i] == cs[i + 1]) {
				dp[i][i + 1] = 1;
				start = i;
				end = i + 1;
			} else
				dp[i][i + 1] = 0;
		}
		dp[len - 1][len - 1] = 1;
		for (int k = 3; k <= len; k++) {
			for (int i = 0; i < len - k + 1; i++) {
				int j = i + k - 1;
				if (cs[i] == cs[j] && dp[i + 1][j - 1] == 1) {
					dp[i][j] = 1;
					start = i;
					end = i + 1;
				} else
					dp[i][j] = 0;
			}
		}

		return s.substring(start, end + 1);
	}

	/**
	 * 10. Regular Expression Matching 动态规划，根据递归改编，记录递归的状态
	 * 
	 * Given an input string (s) and a pattern (p), implement regular expression
	 * matching with support for '.' and '*'.
	 * 
	 * '.' Matches any single character. '*' Matches zero or more of the
	 * preceding element. The matching should cover the entire input string (not
	 * partial).
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		if (s == null || p == null)
			return false;
		char[] ss = s.toCharArray();
		char[] ps = p.toCharArray();
		int m = ss.length, n = ps.length;
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[m][n] = true;
		for (int i = m; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				boolean first = i < m && (ss[i] == ps[j] || ps[j] == '.');
				if (j + 1 < n && ps[j + 1] == '*')
					dp[i][j] = dp[i][j + 2] || (first && dp[i + 1][j]);
				else
					dp[i][j] = first && dp[i + 1][j + 1];
			}
		}
		return dp[0][0];

		/*
		 * if (p.isEmpty()) return s.isEmpty(); boolean first = !s.isEmpty() &&
		 * (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'); if (p.length() >=
		 * 2 && p.charAt(1) == '*') { return isMatch2(s, p.substring(2)) ||
		 * (first && isMatch2(s.substring(1), p)); } else { return first &&
		 * isMatch2(s.substring(1), p.substring(1)); }
		 */
	}

	/**
	 * 10. Regular Expression Matching
	 * 
	 * Given an input string (s) and a pattern (p), implement regular expression
	 * matching with support for '.' and '*'.
	 * 
	 * '.' Matches any single character. '*' Matches zero or more of the
	 * preceding element. The matching should cover the entire input string (not
	 * partial).
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch2(String s, String p) {
		if (p.isEmpty())
			return s.isEmpty();
		boolean first = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
		if (p.length() >= 2 && p.charAt(1) == '*') {
			return isMatch2(s, p.substring(2)) || (first && isMatch2(s.substring(1), p));
		} else {
			return first && isMatch2(s.substring(1), p.substring(1));
		}
	}

	/**
	 * 10. Regular Expression Matching
	 * 
	 * Given an input string (s) and a pattern (p), implement regular expression
	 * matching with support for '.' and '*'.
	 * 
	 * '.' Matches any single character. '*' Matches zero or more of the
	 * preceding element. The matching should cover the entire input string (not
	 * partial).
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch3(String s, String p) {
		if (p == null || s == null)
			return false;
		return match(s, p, 0, 0);
	}

	private boolean match(String s, String p, int i, int j) {
		int m = s.length(), n = p.length();
		if (m == i && n == j)
			return true;
		if (m != i && j >= n)
			return false;
		if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
			// 模式串中当前字符的下一个字符为*
			// 分为四种情况，1：不匹配，i不变，j+=2 2:匹配，i+1,j不变 3：匹配 i+1，j+=2 4:匹配 i不变，j+=2;
			if (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'))
				return match(s, p, i + 1, j) || match(s, p, i + 1, j + 2) || match(s, p, i, j + 2);

			else
				return match(s, p, i, j + 2);

		} else {
			if (j < p.length() && i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)))
				return match(s, p, i + 1, j + 1);
			else
				return false;
		}
	}

	/**
	 * 4. Median of Two Sorted Arrays
	 * 
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * 
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		int left = (m + n + 1) / 2, right = (n + m + 2) / 2; // 下中位数和上中位数
		return (findKth(nums1, nums2, left) + findKth(nums1, nums2, right)) / 2.0;
	}

	private int findKth(int[] nums1, int[] nums2, int k) {
		int m = nums1.length, n = nums2.length;
		if (m > n)
			return findKth(nums2, nums1, k);
		if (m == 0)
			return nums2[k - 1];
		if (k == 1)
			return Math.min(nums1[0], nums2[0]);
		int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
		if (nums1[i - 1] > nums2[j - 1])
			return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
		else
			return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
	}

	/**
	 * 4. Median of Two Sorted Arrays
	 * 
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * 
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

		// 利用PriorityQueue建立大根堆和小根堆
		PriorityQueue<Integer> min = new PriorityQueue<Integer>();
		PriorityQueue<Integer> max = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer num1, Integer num2) {
				// TODO Auto-generated method stub
				return num2 - num1;
			}
		});
		boolean flag = true; // 第偶数个数进小根堆，第奇数个数进大根堆，true 表示第奇数个数

		for (int i = 0; i < nums1.length; i++) {
			int num = nums1[i];
			if (flag) {// 第奇数个数，进小根堆（先进大根堆，再进小根堆）
				max.offer(num);
				min.offer(max.poll());
				flag = !flag;
			} else {// 第偶数个数，进大根堆（先进小根堆，再进大根堆）
				min.offer(num);
				max.offer(min.poll());
				flag = !flag;
			}
		}
		for (int i = 0; i < nums2.length; i++) {
			int num = nums2[i];
			if (flag) {// 第奇数个数，进小根堆（先进大根堆，再进小根堆）
				max.offer(num);
				min.offer(max.poll());
				flag = !flag;
			} else {// 第偶数个数，进大根堆（先进小根堆，再进大根堆）
				min.offer(num);
				max.offer(min.poll());
				flag = !flag;
			}
		}
		if (!flag) { // 总共偶数个数
			return (min.peek() + max.peek()) / 2.0;
		} else { // 总共奇数个数
			return min.peek();
		}

	}

	/**
	 * 3. Longest Substring Without Repeating Characters
	 * 
	 * 自定义哈希表版本*
	 * 
	 * Given a string, find the length of the longest substring without
	 * repeating characters.
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring2(String s) {
		if (s == null || s.length() < 1)
			return 0;
		int max = 0;
		int[] chars = new int[128]; // 字符ASCII值作为下标，字符在s中的下标作为值
		for (int i = 0, j = 0; j < s.length(); j++) {
			i = Math.max(i, chars[s.charAt(j)]);
			max = Math.max(max, j - i);
			chars[s.charAt(j)] = j;
		}
		return max;
	}

	/**
	 * 3. Longest Substring Without Repeating Characters
	 * 
	 * Given a string, find the length of the longest substring without
	 * repeating characters.
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() < 1)
			return 0;
		if (s.length() == 1)
			return 1;
		int max = 0;
		int start = 0; // 不同字符的开始下标
		int end = 1;
		String subStr = "";
		while (end < s.length()) {
			subStr = s.substring(start, end);
			while (start < end) {
				if (subStr.contains(s.charAt(end) + "")) {
					// end下标对应的字符在子串中
					start++;
					subStr = s.substring(start, end);
				} else
					break;
			}
			max = Math.max(max, end - start + 1);
			end++;
		}

		return max;
	}

	/**
	 * 2. You are given two non-empty linked lists representing two non-negative
	 * integers. The digits are stored in reverse order and each of their nodes
	 * contain a single digit. Add the two numbers and return it as a linked
	 * list.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode preHead = new ListNode(-1); // new listnode for return
		ListNode res = preHead;
		int a = 0; // a=1 if sum of two number >=10
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + a;
			res.next = new ListNode(sum % 10);
			a = sum / 10;
			l1 = l1.next;
			l2 = l2.next;
			res = res.next;
		}
		if (a == 0) {
			res.next = l1 == null ? l2 : l1;
		} else {
			if (l1 == null && l2 == null) {
				res.next = new ListNode(a);
			} else {
				ListNode tmp = l1 == null ? l2 : l1;
				while (tmp != null) {
					int sum = tmp.val + a;
					res.next = new ListNode(sum % 10);
					a = sum / 10;
					tmp = tmp.next;
					res = res.next;
				}
				if (a == 1)
					res.next = new ListNode(1);
			}
		}

		return preHead.next;
	}

	/**
	 * 1. Two Sum
	 * 
	 * Given an array of integers, return indices of the two numbers such that
	 * they add up to a specific target. You may assume that each input would
	 * have exactly one solution, and you may not use the same element twice.
	 * 简化版，不需要排序
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int a = nums[i];
			int b = target - a;
			if (map.containsKey(b)) // 如果map里存放了b
				return new int[] { map.get(b), i };
			map.put(a, i);
		}
		return null;
	}

	/**
	 * 1. Two Sum
	 * 
	 * Given an array of integers, return indices of the two numbers such that
	 * they add up to a specific target. You may assume that each input would
	 * have exactly one solution, and you may not use the same element twice.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 2)
			return null;
		int[] res = new int[2];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			map.put(i, nums[i]);
		int start = 0, end = nums.length - 1;
		ArrayList<Entry<Integer, Integer>> list = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue() - o2.getValue();
			}
		});

		while (start < end) {
			if (list.get(start).getValue() + list.get(end).getValue() == target) {
				res[0] = list.get(start).getKey();
				res[1] = list.get(end).getKey();
				return res;
			} else if (list.get(start).getValue() + list.get(end).getValue() > target) {
				end--;
			} else {
				start++;
			}
		}

		return res;
	}

	/**
	 * 数字判断 状态标志位版本
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber3(String s) {
		if (s == null || s.trim().length() == 0)
			return false;
		s = s.trim();
		boolean hasE = false, hasDot = false, hasNum = false; // e.number标志位
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch >= '0' && ch <= '9')
				hasNum = true;
			else if (ch == '.') {
				if (hasDot || hasE) // 当前面有.或者e时直接返回false
					return false;
				hasDot = true;
			} else if (ch == 'e') {
				if (hasE || !hasNum) // 当前面有e或者前面没有数字时返回false
					return false;
				hasE = true;
				hasNum = false; // hasNum设置为false，后面必须有数字
			} else if (ch == '-' || ch == '+') {
				if (i != 0 && s.charAt(i - 1) != 'e') // -+号只能出现在首位或者e的下一位
					return false;
			} else {
				return false;
			}
		}
		return hasNum; // 如果数字合法，hasNum最终一定为true
	}

	/**
	 * 数字判断 正则表达式版本
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber2(String s) {
		if (s == null || s.trim().length() == 0)
			return false;
		s = s.trim(); // 去掉两侧空格
		String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
		return s.matches(regex);
	}

	/**
	 * 数字判断
	 * 
	 * @param s
	 * @return
	 */
	public boolean isNumber(String s) {
		s = s.trim();
		char[] str = s.toCharArray();
		if (str.length < 1 || str == null)
			return false;
		if ((str[0] < '0' || str[0] > '9') && str.length == 1)
			return false;
		int i = (str[0] == '-' || str[0] == '+') ? 1 : 0; // 第一个有效位
		if (str[0] == '-' || str[0] == '+') {
			if (!isNumber(s.substring(1)))
				return false;
		}
		int len = str.length;
		int state = 0;
		// 0表示只有数字，1表示先遇到. 2表示先遇到E
		/*
		 * if(str[i]<'0'||str[i]>'9') return false; i++; //跳过第一个有效数字
		 */ for (; i < len; i++) {
			char ch = str[i];
			if (ch == '.') {
				state = 1;
				break;
			}
			if (ch == 'e' || ch == 'E') {
				state = 2;
				break;
			}
			if (ch < '0' || ch > '9')
				return false;
		}

		// state=1，遇到了小数点
		if (state == 1) {
			i++; // 移动到小数点的下一位
			/*
			 * if(str[i]<'0'||str[i]>'9'||i==len) return false; i++;
			 */
			for (; i < len; i++) {
				char ch = str[i];
				if (ch == 'e' || ch == 'E') {
					state = 2;
					break;
				}
				if (ch < '0' || ch > '9')
					return false;
			}
		}

		// state=2,遇到了指数位
		if (state == 2) {
			// 指数位之前必须为合法数字
			if (!isNumber(s.substring(0, i)))
				return false;
			i++; // 指数位的下一位
			if (i == len)
				return false;
			if (str[i] == '-' || str[i] == '+')
				i++;
			if (i == len)
				return false;
			for (; i < len; i++) {
				char ch = str[i];
				if (ch < '0' || ch > '9')
					return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转换成int数
	 * 
	 * @param str
	 * @return
	 */
	public int myAtoi(String str) {
		if (str == null || str.trim().length() == 0)
			return 0;
		str = str.trim(); // 移除str两侧的空格
		int s = str.charAt(0) == '-' ? -1 : 1; // 是否需要乘以-1
		StringBuilder sb = new StringBuilder(); // 存放数字组成的字符串
		for (int i = (str.charAt(0) == '-' || str.charAt(0) == '+' ? 1 : 0); i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch < '0' || ch > '9')
				break; // 遇到非数字字符立即结束循环
			sb.append(ch);
		}
		if (sb.length() == 0) // 前面不是数字，直接返回0
			return 0;
		try {
			return s * Integer.parseInt(sb.toString());
		} catch (Exception e) {
			return s == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		}
	}

	/**
	 * Given a non-empty string s and a dictionary wordDict containing a list of
	 * non-empty words, add spaces in s to construct a sentence where each word
	 * is a valid dictionary word. Return all such possible sentences.
	 * 
	 * Note:
	 * 
	 * The same word in the dictionary may be reused multiple times in the
	 * segmentation. You may assume the dictionary does not contain duplicate
	 * words.
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public List<String> wordBreak(String s, List<String> wordDict) {
		return DFS(s, wordDict, new HashMap<String, ArrayList<String>>());
	}

	private List<String> DFS(String s, List<String> dict, HashMap<String, ArrayList<String>> map) {
		if (map.containsKey(s))
			return map.get(s);
		ArrayList<String> list = new ArrayList<String>();
		if (s.length() == 0) {
			list.add("");
			return list;
		}
		for (String subStr : dict) {
			if (s.startsWith(subStr)) {
				for (String str : DFS(s.substring(subStr.length()), dict, map)) {
					list.add(subStr + (str == "" ? "" : " ") + str);
				}
			}
		}
		map.put(s, list);
		return list;
	}

}
