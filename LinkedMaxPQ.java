import edu.princeton.cs.algs4.StdOut;

class MyNode<T> {
    T key;
    MyNode<T> parent;
    MyNode<T> leftChild;
    MyNode<T> rightChild;

    // Constructor to initialize the node
    MyNode(T key) {
        this.key = key;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }
}

// 基于链表的优先队列
public class LinkedMaxPQ<Key extends Comparable<Key>> {
    private MyNode<Key> root = null; // 哨兵
    private int N = 0;

    public LinkedMaxPQ() {

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // 插入新节点
    public void insert(Key v) {
        MyNode<Key> newNode = new MyNode<>(v);

        if (root == null) {
            root = newNode;
            return;
        }

        MyNode<Key> current = root;

        while (true) {
            if (current.leftChild == null) {
                current.leftChild = newNode;
                return;
            }
            if (current.rightChild == null) {
                current.rightChild = newNode;
                return;
            }
            current = current.leftChild; // 左子节点优先
        }


        current.next = newNode;
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private boolean less(MyNode<Key> n1, MyNode<Key> n2) {
        return n1.key.compareTo(n2.key) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swin(int k) {
        int p = k / 2;
        while (p >= 1 && less(p, k)) {
            exch(p, k);
            k = p;
            p = k / 2;
        }
    }

    private void sink() {
        while (k * 2 <= N) {
            int c = k * 2;
            if (c != N && less(c, c + 1)) {
                c++;
            }
            if (!less(k, c)) {
                break;
            }
            exch(k, c);
            k = c;
        }
    }

    public static void main(String[] args) {
        LinkedMaxPQ pq = new LinkedMaxPQ();
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");

        // String[] arr = { "P", "R", "I", "O" };
        // MaxPQ pq = new MaxPQ(arr);
        StdOut.print(pq.delMax());
        pq.insert("R");
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert("I");
        StdOut.print(pq.delMax());
        pq.insert("T");
        StdOut.print(pq.delMax());
        pq.insert("Y");
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert("Q");
        pq.insert("U");
        pq.insert("E");
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert("U");
        StdOut.print(pq.delMax());
        pq.insert("E");

    }
}
