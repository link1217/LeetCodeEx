package leetcode1;

/**
 * 136. Single Number
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums)
            res ^= num;
        return res;
    }
}
