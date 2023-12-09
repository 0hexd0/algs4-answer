/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */
 
import edu.princeton.cs.algs4.Stack;

public class TwoStackSteque<Item> {
    private Stack<Item> stack1; // 主栈
    private Stack<Item> stack2; // 操作栈


    public TwoStackSteque() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }


    public int size() {
        return stack1.size();
    }

    public void push(Item item) {
        stack1.push(item);
    }

    public Item pop() {
        return stack1.pop();
    }

    public void enqueue(Item item) {
        transfer(stack1, stack2);
        stack2.push(item);
        transfer(stack2, stack1);
    }

    // stack1 => stack2
    public void transfer(Stack<Item> s1, Stack<Item> s2) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }

    public static void main(String[] args) {
        TwoStackSteque<Integer> steque = new TwoStackSteque<>();

        // Enqueue elements
        steque.enqueue(1);
        steque.enqueue(2);
        steque.enqueue(3);

        // Push elements
        steque.push(4);
        steque.push(5);

        // Pop elements
        System.out.println("Pop: " + steque.pop());  // Output: 5
        System.out.println("Pop: " + steque.pop());  // Output: 4

        // Enqueue more elements
        steque.enqueue(6);
        steque.enqueue(7);

        // Pop remaining elements
        while (!steque.isEmpty()) {
            System.out.println("Pop: " + steque.pop());
        }
    }
}
