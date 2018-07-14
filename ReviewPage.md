<<<<<<< HEAD
# Review Page

This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. 
New problems will be automatically updated once added.
--------------------------------------------
### **1. [Two Sum](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/TwoSum.java)**      
#### Level: Easy      
#### Tags: Array, Hash Table
      

#### 哈希表
- 可以使用hashmap，或者自定义一个数组当做伪哈希表

---------------
### **2. [Add Two Numbers](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/AddTwoNumbers.java)**    
#### Level: Medium     
#### Tags:  Linked List, Math
- 注意链表的开始和结尾，需要考虑是否进位
- `ListNode preHead = new ListNode(-1); // new listnode for return`
> 有时候新建一个头结点，会有助于返回结果。这道题新建preHead，最后返回preHead.next
- 最后一位相加后如果产生进位，则需要新建一个值为1的节点

--------------------
### **3. [Longest Substring Without Repeating Characters](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestSubstringWithoutRepeatingCharacters.java)** 	
#### Level: Medium		
#### Tags: Hash Table, Two Pointers, String
- 使用长度为128的数组，可以模拟哈希表:int[] chars = new int[128];  chars[i] = index;//i为字符的ASCII值，index为该字符在原字符串中的下标值。
```
		for (int i = 0, j = 0; j < s.length(); j++) {
 			i = Math.max(i, chars[s.charAt(j)]);	
 			max = Math.max(max, j - i);
 			chars[s.charAt(j)] = j;
		}
```		
-----------------------
### **4. [Median of Two Sorted Arrays](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/MedianOfTwoSortedArrays.java)** 	
#### Level: Hard		
#### Tags: Array, Binary Search, Divide and Conquer 
- 借用二分查找的思维解决两个数组中中位数的问题，等价于求第k个数：kth(当k分别等于  `left = (m + n + 1) / 2, right = (n + m + 2) / 2; // 下中位数
和上中位数，相加除以2即为中位数)`
>  M[m]和N[n]是长度为m和n的有序数组，假设始终有m <= n,求;kM = M[i]和kN = N[j]是其第k/2个数，若kM > kN,则kth必不在N[0-j]，否则kth必不在M[0-i]，去掉必不存在的部分，求递归求第k-i(k-j)个数即可
```
 		int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
		if (nums1[i - 1] > nums2[j - 1])
			return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
		else
			return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
```			
- 注意递归结束条件：
 
 		if (m > n)
			return findKth(nums2, nums1, k);
		if (m == 0)
			return nums2[k - 1];
		if (k == 1)
			return Math.min(nums1[0], nums2[0]);
			
---------------------
### **5. [Longest Palindromic Substring](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestPalindromicSubstring.java)** 
#### Levle: Medium	
#### Tags: String, Dynamic Programming
- 暴力拓展，遍历每一个字符，以此字符为中心向两边拓展，求最大回文长度
- 马拉车算法：求字符串中的最大回文子串长度

------------------------------
### **6. [ZigZag Conversion](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ZigZagConversion.java)** 
#### Level: Medium		
#### Tags: String 
- 找数学规律，把字符串换成012345，便于发现规律

-------------------------------
### 7. [Reverse Integer](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ReverseInteger.java)	
#### Level: Easy	
#### Tags: Math 
- 按位翻转，或者直接转换成字符串，调用StringBuilder的reverse方法

--------------------------------------
### **8. [String to Integer (atoi)](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/StringToInteger.java)** 
#### Level: Medium		
#### Tags: Math, String
- String.trim() 方法可以移除字符串两侧的空格
- 调用Integer转换数字的时候，用try catch 来处理非法字符：报异常则返回非法输入 

-----------------------------------
### **9. [Palindrome Number](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/PalindromeNumber.java)** 
#### Level: Easy 	
#### Tags:  Math
* while循环按位计算，如果只是判断回文数字，可以只转换一半
```
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
		return (x == rev || x == rev / 10);
```
---------------------------------
### **10. [Regular Expression Matching](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RegularExpressionMatching.java)** 
#### Level: Hard	
#### Tags: String, Dynamic Programming, BackTracking 
- 递归改动态规划：用二维数组记录递归的状态，可以省掉重复计算
> 详情参考代码

---------------------------
### **11. [Container With Most Water](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ContainerWithMostWater.java)** 
#### Level： Medium   
#### Tags： Array, Two Pointers 
- 限制容量大小的一定是短板，所以短板指针向长板移动，相乘的结果有可能比原来更大，如果长板向短板移动，乘积一定比原来更小
- 状态转移，数学关系判断

---------------------------
### **12. [Integer to Roman](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/IntegerToRoman.java)** 
#### Level: Medium	
#### Tags: Math, String 
- 整数的每一位对应一个罗马数字，123 = 100 + 20 + 3，用3个罗马数字即可表示
- 记录每一个罗马数字对应的整数
> `return nums4[num / 1000] + nums3[(num % 1000) / 100] + nums2[(num % 100) / 10] + nums1[num % 10];`

------------------------------
### **13. [Roman to Integer](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RomanToInteger.java)** 
#### Level: Easy		
#### Tags: Math, String
-  把罗马数字转换成整数放入数组，累加求和，当出现当前数小于后面的数，说明是特殊情况
- 从整体考虑，不要沦陷进细节中

----------------------
### **14. [Longest Common Prefix](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestCommonPrefix.java)**	
#### Level: Easy		
#### Tags: String 
* 很无聊的题，虽然官方Solution中给出了多种方法，这道题依旧很烂
* 从左到右依次判断最长前缀，借助一个获取两个字符串共同前缀的函数，一旦遇到字符串为空，直接返回""
* 讨论区有人给出二分法查找共同前缀长度，运行速度比直接匹配快2ms（二分法5ms，直接匹配7ms），其实差距不大，没必要用二分法

-------------------------------



---------------------
### **65. [Valid Number](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ValidNumber.java)** 	
#### Level: Hard		
#### Tags: Math, String 
- 设置状态标志位，每一个位置都对应一个状态。注意hasE = true后将hasNum设为false
>	`boolean hasE = false, hasDot = false, hasNum = false; // e.number标志位`
- 正则表达式：
>	`String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";`

-----------------------




----------------------
### **140. [Word Break II](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/WordBreakII.java)**	
#### Level: Hard		
#### Tags:Dynamic Programming, BackTracking 
- 深度优先搜索：
>	参考代码理解

---------------------------
### **865. [Smallest Subtree with all the Deepest Nodes](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/SmallestSubtreeWithAllTheDeepestNodes.java)**	
#### Level: Medium	
#### Tags: Tree 
- 利用平衡二叉树的性质，若最深节点只有一个，直接返回此节点，否则返回所有最深节点共同的父节点，即返回左右子树深度一样的节点
- 求二叉树某一节点的深度：
```
	private int depthOfNode(TreeNode n) {
		if (n == null)
			return 0;
		return Math.max(depthOfNode(n.left), depthOfNode(n.right)) + 1;
	}
```
	
---------------------
### **866. [Prime Palindrome](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/PrimePalindrome.java)**	
#### Level: Medium	
#### Tags: Math 
- 一开始实现一种很复杂的方法，构建回文时考虑数字的左半边，累加左半边，需要注意进位时构建回文的方法转换。具体参考代码
- 作弊版：将1-Integer.MaxValue范围内的回文素数放进数组，然后找到比当前数字大的下一个回文素数。这方法，真的是，作弊啊啊啊啊
>	假如数据量有限且可查询得到，必要时候直接拿来用，有奇效

-----------------------------
### **867. [Transpose Matrix](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/TransposeMatrix.java)**	
#### Level:Easy	
#### Tags: Array
- 二维数组的转置，ij互换即可

----------------------------------












=======
# Review Page

This page summarize the solutions of all problems. For thoughts,ideas written in English, refer to deach individual solution. 
New problems will be automatically updated once added.
--------------------------------------------
### **1. [Two Sum](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/TwoSum.java)**      
#### Level: Easy      
#### Tags: Array, Hash Table
      

#### 哈希表
- 可以使用hashmap，或者自定义一个数组当做伪哈希表

---------------
### **2. [Add Two Numbers](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/AddTwoNumbers.java)**    
#### Level: Medium     
#### Tags:  Linked List, Math
- 注意链表的开始和结尾，需要考虑是否进位
- `ListNode preHead = new ListNode(-1); // new listnode for return`
> 有时候新建一个头结点，会有助于返回结果。这道题新建preHead，最后返回preHead.next
- 最后一位相加后如果产生进位，则需要新建一个值为1的节点

--------------------
### **3. [Longest Substring Without Repeating Characters](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestSubstringWithoutRepeatingCharacters.java)** 	
#### Level: Medium		
#### Tags: Hash Table, Two Pointers, String
- 使用长度为128的数组，可以模拟哈希表:int[] chars = new int[128];  chars[i] = index;//i为字符的ASCII值，index为该字符在原字符串中的下标值。
```
		for (int i = 0, j = 0; j < s.length(); j++) {
 			i = Math.max(i, chars[s.charAt(j)]);	
 			max = Math.max(max, j - i);
 			chars[s.charAt(j)] = j;
		}
```		
-----------------------
### **4. [Median of Two Sorted Arrays](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/MedianOfTwoSortedArrays.java)** 	
#### Level: Hard		
#### Tags: Array, Binary Search, Divide and Conquer 
- 借用二分查找的思维解决两个数组中中位数的问题，等价于求第k个数：kth(当k分别等于  `left = (m + n + 1) / 2, right = (n + m + 2) / 2; // 下中位数
和上中位数，相加除以2即为中位数)`
>  M[m]和N[n]是长度为m和n的有序数组，假设始终有m <= n,求;kM = M[i]和kN = N[j]是其第k/2个数，若kM > kN,则kth必不在N[0-j]，否则kth必不在M[0-i]，去掉必不存在的部分，求递归求第k-i(k-j)个数即可
```
 		int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
		if (nums1[i - 1] > nums2[j - 1])
			return findKth(nums1, Arrays.copyOfRange(nums2, j, n), k - j);
		else
			return findKth(Arrays.copyOfRange(nums1, i, m), nums2, k - i);
```			
- 注意递归结束条件：
 
 		if (m > n)
			return findKth(nums2, nums1, k);
		if (m == 0)
			return nums2[k - 1];
		if (k == 1)
			return Math.min(nums1[0], nums2[0]);
			
---------------------
### **5. [Longest Palindromic Substring](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestPalindromicSubstring.java)** 
#### Levle: Medium	
#### Tags: String, Dynamic Programming
- 暴力拓展，遍历每一个字符，以此字符为中心向两边拓展，求最大回文长度
- 马拉车算法：求字符串中的最大回文子串长度

------------------------------
### **6. [ZigZag Conversion](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ZigZagConversion.java)** 
#### Level: Medium		
#### Tags: String 
- 找数学规律，把字符串换成012345，便于发现规律

-------------------------------
### 7. [Reverse Integer](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ReverseInteger.java)	
#### Level: Easy	
#### Tags: Math 
- 按位翻转，或者直接转换成字符串，调用StringBuilder的reverse方法

--------------------------------------
### **8. [String to Integer (atoi)](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/StringToInteger.java)** 
#### Level: Medium		
#### Tags: Math, String
- String.trim() 方法可以移除字符串两侧的空格
- 调用Integer转换数字的时候，用try catch 来处理非法字符：报异常则返回非法输入 

-----------------------------------
### **9. [Palindrome Number](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/PalindromeNumber.java)** 
#### Level: Easy 	
#### Tags:  Math
* while循环按位计算，如果只是判断回文数字，可以只转换一半
```
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
		return (x == rev || x == rev / 10);
```
---------------------------------
### **10. [Regular Expression Matching](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RegularExpressionMatching.java)** 
#### Level: Hard	
#### Tags: String, Dynamic Programming, BackTracking 
- 递归改动态规划：用二维数组记录递归的状态，可以省掉重复计算
> 详情参考代码

---------------------------
### **11. [Container With Most Water](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ContainerWithMostWater.java)** 
#### Level： Medium   
#### Tags： Array, Two Pointers 
- 限制容量大小的一定是短板，所以短板指针向长板移动，相乘的结果有可能比原来更大，如果长板向短板移动，乘积一定比原来更小
- 状态转移，数学关系判断

---------------------------
### **12. [Integer to Roman](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/IntegerToRoman.java)** 
#### Level: Medium	
#### Tags: Math, String 
- 整数的每一位对应一个罗马数字，123 = 100 + 20 + 3，用3个罗马数字即可表示
- 记录每一个罗马数字对应的整数
> `return nums4[num / 1000] + nums3[(num % 1000) / 100] + nums2[(num % 100) / 10] + nums1[num % 10];`

------------------------------
### **13. [Roman to Integer](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RomanToInteger.java)** 
#### Level: Easy		
#### Tags: Math, String
-  把罗马数字转换成整数放入数组，累加求和，当出现当前数小于后面的数，说明是特殊情况
- 从整体考虑，不要沦陷进细节中

----------------------
### **14. [Longest Common Prefix](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LongestCommonPrefix.java)**	
#### Level: Easy		
#### Tags: String 
* 很无聊的题，虽然官方Solution中给出了多种方法，这道题依旧很烂
* 从左到右依次判断最长前缀，借助一个获取两个字符串共同前缀的函数，一旦遇到字符串为空，直接返回""
* 讨论区有人给出二分法查找共同前缀长度，运行速度比直接匹配快2ms（二分法5ms，直接匹配7ms），其实差距不大，没必要用二分法

-------------------------------



---------------------
### **65. [Valid Number](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ValidNumber.java)** 	
#### Level: Hard		
#### Tags: Math, String 
- 设置状态标志位，每一个位置都对应一个状态。注意hasE = true后将hasNum设为false
>	`boolean hasE = false, hasDot = false, hasNum = false; // e.number标志位`
- 正则表达式：
>	`String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";`

-----------------------




----------------------
### **140. [Word Break II](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/WordBreakII.java)**	
#### Level: Hard		
#### Tags:Dynamic Programming, BackTracking 
- 深度优先搜索：
>	参考代码理解

---------------------------
### **865. [Smallest Subtree with all the Deepest Nodes](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/SmallestSubtreeWithAllTheDeepestNodes.java)**	
#### Level: Medium	
#### Tags: Tree 
- 利用平衡二叉树的性质，若最深节点只有一个，直接返回此节点，否则返回所有最深节点共同的父节点，即返回左右子树深度一样的节点
- 求二叉树某一节点的深度：
```
	private int depthOfNode(TreeNode n) {
		if (n == null)
			return 0;
		return Math.max(depthOfNode(n.left), depthOfNode(n.right)) + 1;
	}
```
	
---------------------
### **866. [Prime Palindrome](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/PrimePalindrome.java)**	
#### Level: Medium	
#### Tags: Math 
- 一开始实现一种很复杂的方法，构建回文时考虑数字的左半边，累加左半边，需要注意进位时构建回文的方法转换。具体参考代码
- 作弊版：将1-Integer.MaxValue范围内的回文素数放进数组，然后找到比当前数字大的下一个回文素数。这方法，真的是，作弊啊啊啊啊
>	假如数据量有限且可查询得到，必要时候直接拿来用，有奇效

-----------------------------
### **867. [Transpose Matrix](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/TransposeMatrix.java)**	
#### Level:Easy	
#### Tags: Array
- 二维数组的转置，ij互换即可

----------------------------------












>>>>>>> refs/remotes/origin/master
