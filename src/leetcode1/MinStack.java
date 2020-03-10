package leetcode1;

import java.util.Stack;

/**
 * 155. Min Stack
 */
public class MinStack {

    private static Stack<Integer> stack1 = new Stack<>();
    private static Stack<Integer> stack2 = new Stack<>();

    public MinStack() {
        stack1.clear();
        stack2.clear();
    }

    public void push(int x) {
        stack1.push(x);
        if (stack2.isEmpty())
            stack2.push(x);
        else
            stack2.push(x <= stack2.peek() ? x : stack2.peek());
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }
}

class MinStack2 {

    public static class Node {
        int val;
        int min;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private static Stack<Node> stack = new Stack<>();

    public MinStack2() {
        stack.clear();
    }

    public void push(int x) {
        if (stack.isEmpty())
            stack.push(new Node(x, x));
        else
            stack.push(new Node(x, x <= stack.peek().min ? x : stack.peek().min));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }
}

class MinStack3 {

    private static Stack<Integer> stack = new Stack<>();
    private static int min;

    public MinStack3() {
        stack.clear();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (min == stack.pop())
            min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}