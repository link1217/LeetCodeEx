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
### **15. [3Sum](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/threeSum.java)**
#### Level: Medium
#### Tags: Array, Two Pointers 
* 先排序，遍历数组确定第一个数，剩下两个数从两头遍历
* 注意一定要跳过不需要计算的数，不然会浪费大量时间
> 第一个数不需要重复选取，第二第三个数选定后不需要选取相等的数
```
		if (i == 0 || nums[i] != nums[i - 1]) {···}
		
		if (nums[j] + nums[k] == a) {
			listAll.add(Arrays.asList(nums[i], nums[j], nums[k]));
			while (j < k && nums[j] == nums[j + 1])
				j++;
			while (j < k && nums[k] == nums[k - 1])
				k--;
			j++;
			k--;
		}
```

-------------------------------
### **16. [3Sum Closest](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/Sum3Closest.java)**
#### Level: Medium
#### Tags: Array, Two Pointers 
* 双指针问题，一大一小往中间移动
* 和3Sum类似，区别在于这里需要返回三个数值之和，所求的三个数之和需要与输入最接近，可以使用math函数
```
					if (diff > Math.abs(a + nums[j] + nums[k] - target)) {
						diff = Math.abs(a + nums[j] + nums[k] - target);
						res = a + nums[j] + nums[k];
					}
```
* 具体细节参考代码

---------------------
### **17. [Letter Combinations of a Phone Number](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/LetterCombinationsOfAPhoneNumber.java)** 
#### Level: Medium
#### Tags: String, Backtracking
* 遍历字符数组和list，按顺序拼接，注意使用LinkedList，因为LinkedList增删快。详情参考代码

* 回溯法： 不熟悉，有待了解



----------------------
### **18. [4Sum](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/Sum4.java)**
#### Level: Medium
#### Tags: Array, Hash Table, Two Pointers
* 类似于3Sum和2Sum，先确定两个数，然后从两端筛选其余两个数。
* 注意过滤条件，过滤掉不符合的情况，可以极大地提高代码的运行效率
> 过滤条件设置不足时耗时46ms，超过77%，新增几个过滤之后耗时14ms，超过100%
* 详情参考代码



-----------------------
### **19. [Remove Nth Node From End of List](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RemoveNthNodeFromEndOfList.java)**
#### Level: Medium
#### Tags: LinkedList, Two Pointers
* 设置指向头结点的结点，最后返回 preHead.next
```
	ListNode preHead = new ListNode(-1);	
	preHead.next = head;
```
* 注意结点移动的边界
* 其余参考代码


-------------------
### **20. [Valid Parentheses](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ValidParentheses.java)**
#### Level: Easy
#### Tags: String, Stack 
* 用栈实现括号匹配，或者自定义数组模仿栈
* 详情参考代码

---------
### **21. [Merge Two Sorted Lists](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/MergeTwoSortedLists.java)**
#### Level: Easy
#### Tags: LinkedList
* 注意开始部分的null值判断，如果其中一个为null，直接返回另一个
* 非递归版： 新建头结点，依次判断两个链表的值，最后链接上剩余部分
* 递归版： 简单实用，想法值得借鉴
```
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
```

-------------
### **22. [Generate Parentheses](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/GenerateParentheses.java)**
#### Level: Medium
#### Tags: String, BackTracking 
* 回溯法，用StringBuilder代替String拼接，可以提速些许
* 注意right < left 过程中，右括号的长度一定不大左括号
```
		if (left + right == n * 2)
			list.add(str.toString());
		if (left < n) {
			backtrack(list, str.append("("), left + 1, right, n);
			str.deleteCharAt(str.length() - 1);
		}
		if (right < left) {
			backtrack(list, str.append(")"), left, right + 1, n);
			str.deleteCharAt(str.length() - 1);
		}
```
* 还有遍历集合拼接法，详情参考代码


----------------
### **23. [Merge k Sorted Lists](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/MergeKSortedLists.java)**
#### Level: Hard
#### Tags: LinkedList, Heap, Divide and Conquer 
* 归并排序思路对多路链表归并，需要先实现两路归并
* 归并过程如下：
```
		while (len > 1) {
			for (int i = 0; i < len / 2; i++) {
				lists[i] = merge2List(lists[i], lists[len - 1 - i]);
			}
			len = (len + 1) / 2;
		}
```
* 同样可以利用优先级队列，相当于堆排序过程，速度较归并慢一点，详情参考代码

---------------------
### **24. [Swap Nodes in Pairs](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/SwapNodesInPairs.java)**
#### Level: Medium
#### Tags: LinkedList 
* 递归版：简单明了，就是不知道是否符合题目常数空间复杂度的要求
* 非递归版：额外空间少，速度上差不多
> 非递归版注意设置节点的先驱节点，新建一个用于返回的头节点即可
* 详情参考代码


------------------------
### **25. [Reverse Nodes in k-Group](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ReverseNodesInKGroup.java)**
#### Level: Hard
#### Tags: LinkedList
* 递归版：容易实现
* 非递归版，叫麻烦，注意重排后的端点连接
* 详情参考代码

-----------------------
### **26. [Remove Duplicates from Sorted Array](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RemoveDuplicatesFromSortedArray.java)**
#### Level: Easy
#### Tags: Array, Two Pointers 
* 设置一个不重复元素的下标，自增覆盖···
* 无意义的一道题

------------------
### **27. [Remove Element](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/RemoveElement.java)**
#### Level: Easy
#### Tags: Array, Two Pointers
* 类似于26题，同样无聊的一题
* 毫无参考必要

-----------------------
### **28. [Implement strStr()](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/ImplementStrStr.java)**
#### Level: Easy
#### Tags: String, Two Pointers
* 烂题三连发，自定义实现String.indexOf()函数
* 拼手速的题

-------------------
### **29. [Divide Two Integers](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode/DivideTwoIntegers.java)**
#### Level: Medium
#### Tags: Math, Binary Search 
* 利用二分查找思路。
```
		while (dvs <= dvd) {
			long tmp = dvs;
			int mul = 1;
			while (dvd > (tmp << 1)) {
				tmp <<= 1;
				mul <<= 1;
			}
			res += mul;
			dvd -= tmp;
		}
```

------------------






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
### **140. [Word Break II](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode2/WordBreakII.java)**	
#### Level: Hard		
#### Tags:Dynamic Programming, BackTracking 
- 深度优先搜索：
>	参考代码理解

---------------------------
### **865. [Smallest Subtree with all the Deepest Nodes](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode9/SmallestSubtreeWithAllTheDeepestNodes.java)**	
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
### **866. [Prime Palindrome](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode9/PrimePalindrome.java)**	
#### Level: Medium	
#### Tags: Math 
- 一开始实现一种很复杂的方法，构建回文时考虑数字的左半边，累加左半边，需要注意进位时构建回文的方法转换。具体参考代码
- 作弊版：将1-Integer.MaxValue范围内的回文素数放进数组，然后找到比当前数字大的下一个回文素数。这方法，真的是，作弊啊啊啊啊
>	假如数据量有限且可查询得到，必要时候直接拿来用，有奇效

-----------------------------
### **867. [Transpose Matrix](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode9/TransposeMatrix.java)**	
#### Level:Easy	
#### Tags: Array
- 二维数组的转置，ij互换即可

----------------------------------
### **868. [Binary Gap](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode9/BinaryGap.java)**	
#### Level: Easy
#### Tags: Math
* 查找二进数据中两个1的最大距离，首先定位到第一个1，然后再计算距离。
* 可以用一个循环实现，设置一个初始值`int cur = -32; `表示当前1的位置，每次遇到1后重置cur
 ```		
			while (n > 0) {
				if ((n & 1) == 1) {
					res = Math.max(res, cur);
					cur = 0;
				}
				n >>= 1;
				cur++;
			}
  ```
* 把while循环该成for循环好像会快一点点

-------------
### **869. [Reordered Power of 2](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode9/ReorderedPowerOf2.java)** 
#### Level: Medium
#### Tags: Math
* 思路就是把一个整数转换成这些数字可以组成的最大数，然后从1开始匹配2的n次方，一旦匹配到直接返回true，结束循环条件如下：
```	
 		change(m)的作用是重排数字，返回最大组成的数
		while (n > change(m) || n > m) {
			m *= 2;
			if (n == change(m))
				return true;
		}
		return n == change(m);
```
* 注意重排数字后int接收可能越界，用long接收

* 还可以根据每一个整数的数字组成来判断输入是否可以重组成为2的n次幂
```
	//记录整数中每一个数字出现的次数： 11234400 =>21122 第i位表示i出现过几次
	public int counter(int N) {
		int res = 0;
		for (; N > 0; N /= 10)
			res += (int) Math.pow(10, N % 10);
		return res;
	}
```

---------------
### **870. [Advantage Shuffle](https://github.com/lanrengufeng/LeetCodeEx/blob/master/src/leetcode9/AdvantageShuffle.java)**
#### Level: Medium
#### Tags: Math, HashMap 
* 田忌赛马？
* 对A排序，把B的value和index映射放入map，然后根据value排序，从A的最大值开始匹配B的最大值，小于等于就匹配B的次最大值
* 详情参考代码，有详细注释
* 更新：使用优先级队列代替哈希表效果更好，而且给结果集赋值部分可以优化为一次循环赋值完毕。
* 优先级队列：
```
		//队列中存放数组，数组中存放B中的元素值和下标，然后自定义比较器以元素值排序
		//jdk1.8以后可以使用lambda表达式：PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->b[1]-a[1]);
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>(10, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o2[1] - o1[1];
			}
		});
```
* 循环赋值部分：
```
		int start = 0, end = len - 1;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();	//堆顶的数组，存放着B中的最大值和下标
			int bIndex = cur[0];
			int bVal = cur[1];
			if (A[end] > bVal)
				res[bIndex] = A[end--];		//A>B则令当下标对应的值为A。双指针思路
			else
				res[bIndex] = A[start++];	//否则依次获取A的最小值赋值给当前下标
		}	
```


--------------------------










