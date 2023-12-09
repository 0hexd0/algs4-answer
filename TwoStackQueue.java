/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

// 双栈队列
public class TwoStackQueue<Item> {
    private Stack<Item> stack1;
    private Stack<Item> stack2;


    public TwoStackQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    public int size() {
        return stack1.size() + stack2.size();
    }

    public void enqueue(Item item) {
        stack1.push(item);
    }

    // stack1 => stack2
    public void transfer() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }


    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        if (stack2.isEmpty()) {
            transfer();
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        TwoStackQueue<String> queue = new TwoStackQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }

}
