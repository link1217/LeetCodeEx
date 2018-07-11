package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Solution {

	private String str;

	public static void main(String[] args) {
		Solution so = new Solution();

		int[] arr = { 2, 7, 11, 15 };
		System.out.println(Arrays.toString(so.twoSum2(arr, 26)));

		// System.out.println(Double.parseDouble("-1."));

		// System.out.println(so.isNumber3("1"));

		// System.out.println(so.myAtoi("-91283472332"));

	}

	/**
	 * 1. Two Sum Given an array of integers, return indices of the two numbers
	 * such that they add up to a specific target. You may assume that each
	 * input would have exactly one solution, and you may not use the same
	 * element twice. 简化版，不需要排序
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
	 * 1. Two Sum Given an array of integers, return indices of the two numbers
	 * such that they add up to a specific target. You may assume that each
	 * input would have exactly one solution, and you may not use the same
	 * element twice.
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
