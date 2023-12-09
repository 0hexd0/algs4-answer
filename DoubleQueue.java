/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

import java.util.NoSuchElementException;

public class DoubleQueue<Item> {
    private Stack<Item> stack;
    private TwoStackSteque<Item> steque;


    public DoubleQueue() {
        stack = new Stack<>();
        steque = new TwoStackSteque<>();
    }

    boolean isEmpty() {
        return steque.size() == 0;
    }

    int size() {
        return steque.size();
    }

    void pushLeft(Item item) {
        steque.push(item);
    }

    Item popLeft() {
        return steque.pop();
    }

    void pushRight(Item item) {
        steque.enqueue(item);
    }

    Item popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        while (steque.size() > 1) {
            stack.push(steque.pop());
        }
        Item res = steque.pop();
        while (!stack.isEmpty()) {
            steque.push(stack.pop());
        }
        return res;
    }

    public static void main(String[] args) {
        DoubleQueue<Integer> deque = new DoubleQueue<>();

        // Push elements
        deque.pushLeft(1);
        deque.pushRight(2);
        deque.pushLeft(3);

        // Pop elements
        int popLeftResult = deque.popLeft();
        int popRightResult = deque.popRight();

        System.out.println("After popping elements: " + deque);
        System.out.println("Pop Left result: " + popLeftResult); // 期望输出 3
        System.out.println("Pop Right result: " + popRightResult); // 期望输出 2

        // Push more elements
        deque.pushRight(4);
        deque.pushLeft(5);

        // Pop more elements
        int popLeftResult2 = deque.popLeft();
        int popRightResult2 = deque.popRight();

        System.out.println("Pop Left result 2: " + popLeftResult2); // 期望输出 5
        System.out.println("Pop Right result 2: " + popRightResult2); // 期望输出 4
    }
}
