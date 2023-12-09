import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfInts {
    private int[] a;  // holds the items
    private int n;       // number of items in stack

    // create an empty stack with given capacity
    public FixedCapacityStackOfInts(int capacity) {
        a = new int[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == a.length;
    }

    public void push(int item) {
        a[n++] = item;
    }

    public int pop() {
        return a[--n];
    }

    public int peek() {
        return a[n - 1];
    }

    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        FixedCapacityStackOfInts stack = new FixedCapacityStackOfInts(max);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(Integer.parseInt(item));
            else if (stack.isEmpty()) StdOut.println("BAD INPUT");
            else StdOut.print(stack.pop() + " ");
        }
        StdOut.println();

        // print what's left on the stack
        StdOut.print("Left on stack: ");
        for (int i = 0; i < stack.n; i++) {
            StdOut.print(stack.a[i] + " ");
        }
        StdOut.println();
    }
}


