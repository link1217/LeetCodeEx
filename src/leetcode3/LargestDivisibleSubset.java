package leetcode3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author gumingjie
 * @date 2019/12/6 9:23 上午
 * @description 368. Largest Divisible Subset
 * @url https://leetcode.com/problems/largest-divisible-subset/
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        //排序
        Arrays.sort(nums);
        Queue<List<Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.size() - o1.size()));
        for (int num : nums) {
            boolean found = false;
            Iterator<List<Integer>> iterator = queue.iterator();
            while (iterator.hasNext()) {
                List<Integer> curList = iterator.next();
                if (num % curList.get(curList.size() - 1) == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.addAll(curList);
                    list.add(num);
                    queue.add(list);
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                queue.add(list);
            }
        }
        queue.add(new ArrayList<>());
        return queue.poll();
    }

    public static void main(String[] args) {
        int[] nums = {2,4,6,9,19,81,729};
        List<Integer> list = new LargestDivisibleSubset().largestDivisibleSubset(nums);
        System.out.println(list);

        Queue<List<Integer>> queue = new PriorityQueue<>(((o1, o2) -> o2.size() - o1.size()));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list1.add(1);
        list2.add(1);
        list2.add(1);
        list3.add(1);
        list3.add(1);
        list3.add(1);
        queue.offer(list3);
        queue.offer(list1);
        queue.offer(list2);
        queue.offer(list2);
        queue.offer(list);
        queue.offer(list3);
        Iterator<List<Integer>> iterator = queue.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("-------");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }
}
