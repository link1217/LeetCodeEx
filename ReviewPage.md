# Review Page

This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. 
New problems will be automatically updated once added.
--------------------------------------------
### **1. [Two Sum](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/TwoSum.java)**      Level: Easy      Tags: Array, Hash Table
      

#### 哈希表
- 可以使用hashmap，或者自定义一个数组当做伪哈希表

---------------
### **2. [Add Two Numbers](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/AddTwoNumbers.java)**    Level: Medium     Tags:  Linked List, Math
- 注意链表的开始和结尾，需要考虑是否进位
- ListNode preHead = new ListNode(-1); // new listnode for return
> 有时候新建一个头结点，会有助于返回结果。这道题新建preHead，最后返回preHead.next
- 最后一位相加后如果产生进位，则需要新建一个值为1的节点

--------------------
### **3. [Longest Substring Without Repeating Characters](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestSubstringWithoutRepeatingCharacters.java)** 	Level: Medium		Tags: Hash Table, Two Pointers, String
-使用长度为128的数组，可以模拟哈希表:int[] chars = new int[128];  chars[i] = index;//i为字符的ASCII值，index为该字符在原字符串中的下标值。
> for (int i = 0, j = 0; j < s.length(); j++) {
			i = Math.max(i, chars[s.charAt(j)]);	
			max = Math.max(max, j - i);
			chars[s.charAt(j)] = j;
		}
		
-----------------------
### **4. [Median of Two Sorted Arrays](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/MedianOfTwoSortedArrays.java)** 	Level: Hard		Tags: Array, Binary Search, Divide and Conquer 
- 借用二分查找的思维解决两个数组中中位数的问题，等价于求第k个数：kth(当k分别等于 left = (m + n + 1) / 2, right = (n + m + 2) / 2; // 下中位数和上中位数，相加除以2即为中位数)
> M[m]和N[n]是长度为m和n的有序数组，假设始终有m <= n,求;kM = M[i]和kN = N[j]是其第k/2个数，若kM > kN,则kth必不在N[0-j]，否则kth必不在M[0-i]，去掉必不存在的部分，
求递归求第k-i(k-j)个数即可
> 		int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
		if (nums1[i - 1] > nums2[j - 1])
			return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
		else
			return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
> 注意递归结束条件：
>  		if (m > n)
			return findKth(nums2, nums1, k);
		if (m == 0)
			return nums2[k - 1];
		if (k == 1)
			return Math.min(nums1[0], nums2[0]);
			
---------------------



