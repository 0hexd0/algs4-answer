/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class OneQueueStack<Item> {
    private Queue<Item> queue;

    public OneQueueStack() {
        queue = new Queue<>();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public int size() {
        return queue.size();
    }

    public void push(Item item) {
        queue.enqueue(item);
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        for (int i = 1; i < queue.size(); i++) {
            queue.enqueue(queue.dequeue());
        }
        return queue.dequeue();
    }

    public static void main(String[] args) {
        OneQueueStack<String> stack = new OneQueueStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
