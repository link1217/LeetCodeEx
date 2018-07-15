package leetcode;

import java.util.Arrays;

/**
 * * 869. Reordered Power of 2
 * 
 * Starting with a positive integer N, we reorder the digits in any order
 * (including the original order) such that the leading digit is not zero.
 * 
 * Return true if and only if we can do this in a way such that the resulting
 * number is a power of 2.
 * 
 * @author Watcher
 *
 */
public class ReorderedPowerOf2 {

	public boolean reorderedPowerOf2(int N) {
		long n = N; // int逆序后有可能越界，用long接收
		n = change(n);
		long m = 1;
		while (n > change(m) || n > m) {
			m <<= 1;
			if (n == change(m))
				return true;
		}
		return n == change(m);
	}

	/**
	 * 输入一个整数，输出由该数字构成的最大整数 123=>321
	 * 
	 * @param N
	 * @return
	 */
	private long change(long N) {
		char[] cs = (N + "").toCharArray();
		Arrays.sort(cs); // 先排序
		String ns = new StringBuilder(new String(cs)).reverse().toString(); // 再反转
		return Long.parseLong(ns);
	}
}
