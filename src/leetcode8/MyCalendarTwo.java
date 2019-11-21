package leetcode8;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author gumingjie
 * @date 2019/11/20 10:28 下午
 * @descriptiona 731. My Calendar II
 */
public class MyCalendarTwo {

    public static class Node {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    TreeSet<Node> set1, set2;

    public MyCalendarTwo() {
        set1 = new TreeSet<>(Comparator.comparingInt(o -> o.start));
        set2 = new TreeSet<>(Comparator.comparingInt(o -> o.start));
    }

    public boolean book(int start, int end) {
        for (Node n : set2) {
            if (start >= n.end || end <= n.start) {
                continue;
            }
            return false;
        }
        for (Node n : set1) {
            if (start >= n.end || end <= n.start) {
                continue;
            }
            set2.add(new Node(Math.max(start, n.start), Math.min(end, n.end)));
        }
        set1.add(new Node(start, end));
        return true;
    }

    //------------------solution2------------------

    public static class MyCalendarTwo2 {

        Map<Integer, Integer> sortMap;

        public MyCalendarTwo2() {
            sortMap = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            sortMap.compute(start, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return ++v;
            });
            sortMap.compute(end, (k, v) -> {
                if (v == null) {
                    return -1;
                }
                return --v;
            });
            int cnt = 0;
            for (int key : sortMap.keySet()) {
                cnt += sortMap.get(key);
                if (cnt >= 3) {
                    sortMap.compute(start, (k, v) -> --v);
                    sortMap.compute(end, (k, v) -> ++v);
                    return false;
                }
            }
            return true;
        }
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such: MyCalendarTwo obj = new MyCalendarTwo(); boolean
 * param_1 = obj.book(start,end);
 */