package leetcode;

/**
 * 44. Wildcard Matching
 * 
 * @author Watcher
 *
 */
public class WildcardMatching {

	public static void main(String[] args) {
		WildcardMatching so = new WildcardMatching();
		System.out.println(so.isMatch("aa", "a"));
	}

	/**
	 * 指针回溯，耗时最少
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		int sIndex = 0, pIndex = 0, match = 0, starIndex = -1;
		while (sIndex < s.length()) {
			if (pIndex < p.length() && (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex))) {
				sIndex++;
				pIndex++;
			} else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
				starIndex = pIndex; // 记录最后一个出现的*
				match = sIndex; // 此时已匹配到的s中位置
				pIndex++;
			} else if (starIndex != -1) {
				pIndex = starIndex + 1; // 模式串的指针回到最后一个*的下一位置从新开始
				match++; // *抵消一个字符
				sIndex = match;
			} else {
				return false;
			}
		}
		while (pIndex < p.length() && p.charAt(pIndex) == '*')
			pIndex++;
		return pIndex == p.length();
	}

	/**
	 * 动态规划
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch2(String s, String p) {
		int sLen = s.length(), pLen = p.length();
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 0; i < pLen; i++) {
			if (p.charAt(i) == '*')
				for (int j = 0; j < sLen; j++)
					dp[j + 1] = dp[j] || dp[j + 1];
			else {
				for (int j = sLen - 1; j >= 0; j--)
					dp[j + 1] = dp[j] && (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j));
				dp[0] = false;
			}
		}
		return dp[s.length()];
	}

	/**
	 * 暴力匹配，超时
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch3(String s, String p) {
		if (p.isEmpty())
			return s.isEmpty() ? true : false;
		if (p.charAt(p.length() - 1) != '?' && p.charAt(p.length() - 1) != '*' && !s.isEmpty()
				&& p.charAt(p.length() - 1) != s.charAt(s.length() - 1))
			return false;
		// 处理p，去掉连续的*
		StringBuilder sb = new StringBuilder();
		sb.append(p.charAt(0));
		if (p.length() > 0)
			for (int i = 1; i < p.length(); i++) {
				if (p.charAt(i) == '*' && p.charAt(i - 1) == '*')
					continue;
				else
					sb.append(p.charAt(i));
			}
		return match(s, sb.toString());
	}

	private boolean match(String s, String p) {
		if (s.isEmpty() && p.replace("*", "").isEmpty())
			return true;
		if ((s.isEmpty() && !p.replace("*", "").isEmpty()) || (!s.isEmpty() && p.isEmpty()))
			return false;
		if (p.charAt(0) == '?' || s.charAt(0) == p.charAt(0))
			return match(s.substring(1), p.substring(1)); // ? 都往后走一个
		else if (p.charAt(0) == '*')
			return match(s, p.substring(1)) || match(s.substring(1), p); // * 匹配空或者s的第一个，递归匹配
		else
			return false;
	}

}
