/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

import java.util.NoSuchElementException;

// 两个栈实现双向队列
public class ThreeStackDeque<Item> {
    private Stack<Item> leftStack; // 基本栈
    private Stack<Item> rightStack;
    private Stack<Item> tempStack;

    public ThreeStackDeque() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
        tempStack = new Stack<>();
    }

    boolean isEmpty() {
        return leftStack.isEmpty() && rightStack.isEmpty();
    }

    int size() {
        return leftStack.size() + rightStack.size();
    }

    void pushLeft(Item item) {
        leftStack.push(item);
    }

    // stack1 => stack2
    public void transfer(Stack<Item> s1, Stack<Item> s2) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }

    Item popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque underflow");
        }
        if (leftStack.isEmpty()) {
            transfer(rightStack, leftStack);
        }
        return leftStack.pop();
    }

    void pushRight(Item item) {
        rightStack.push(item);
    }

    Item popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque underflow");
        }
        if (rightStack.isEmpty()) {
            transfer(leftStack, rightStack);
        }
        return rightStack.pop();
    }

    public static void main(String[] args) {
        ThreeStackDeque<Integer> deque = new ThreeStackDeque<>();

        // Push elements
        deque.pushLeft(1);
        deque.pushLeft(2);
        deque.pushRight(3);
        deque.pushRight(4);

        System.out.println("Pop Left result: " + deque.popLeft()); // 期望输出 2
        System.out.println("Pop Left result: " + deque.popLeft()); // 期望输出 1
        System.out.println("Pop Left result: " + deque.popLeft()); // 期望输出 4
        System.out.println("Pop Left result: " + deque.popLeft()); // 期望输出 3
    }
}
