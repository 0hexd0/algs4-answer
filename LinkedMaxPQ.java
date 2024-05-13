import edu.princeton.cs.algs4.StdOut;

class MyNode<T> {
    T key;
    MyNode<T> parent;
    MyNode<T> leftChild;
    MyNode<T> rightChild;
    int count = 0;

    // Constructor to initialize the node
    MyNode(T key) {
        this.key = key;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        count = 1;
    }

    /** 以本节点为根节点的树的高度，从1开始 **/
    int height() {
        return (int) Math.ceil(Math.log(count + 1) / Math.log(2));
    }

    /** 以本节点为根节点的树，是否是满二叉树 **/
    boolean isFull() {
        return count == Math.pow(2, height()) - 1;
    }
}

// 基于链表的优先队列
public class LinkedMaxPQ<Key extends Comparable<Key>> {
    private MyNode<Key> root = null;
    private MyNode<Key> tail = null; // 尾巴，方便删除
    private int N = 0;

    public LinkedMaxPQ() {

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    MyNode<Key> findNextNode(MyNode<Key> root) {
        if (root.leftChild == null || root.rightChild == null) {
            return root;
        }

        if (root.isFull()) {
            // 完全二叉树，选取最左下叶子节点
            while (root != null && root.leftChild != null) {
                root = root.leftChild;
            }
            return root;
        }
        else if (!root.leftChild.isFull()) {
            // 左子树没满
            return findNextNode(root.leftChild);
        }
        else {
            return findNextNode(root.rightChild);
        }
    }

    MyNode<Key> findTail(MyNode<Key> root) {
        if (root.leftChild == null) {
            return root;
        }
        if (root.rightChild == null) {
            return root.leftChild;
        }
        if (root.isFull()) {
            // 完全二叉树，选取最右下叶子节点
            while (root != null) {
                root = root.rightChild;
            }
            return root;
        }
        else if (root.leftChild.isFull()) {
            return findTail(root.rightChild);
        }
        else {
            return findTail(root.leftChild);
        }
    }

    // 插入新节点
    public void insert(Key v) {
        // StdOut.println("key " + v);
        MyNode<Key> newNode = new MyNode<>(v);
        tail = newNode;

        if (root == null) {
            root = newNode;
            return;
        }

        MyNode<Key> nextNode = findNextNode(root);
        // StdOut.println("nextNode " + nextNode.key);

        if (nextNode.leftChild == null) {
            nextNode.leftChild = newNode;
        }
        else {
            nextNode.rightChild = newNode;
        }
        newNode.parent = nextNode;
        while (nextNode != null) {
            nextNode.count += 1;
            nextNode = nextNode.parent;
        }
        swin(newNode);
    }

    public Key delMax() {
        Key max = root.key;
        root.key = tail.key;
        MyNode<Key> p = tail.parent;

        if (p.leftChild == tail) {
            p.leftChild = null;
        }
        else {
            p.rightChild = null;
        }
        tail = prevTail;
        root.count -= 1;
        sink(root);
        return max;
    }

    private boolean less(MyNode<Key> n1, MyNode<Key> n2) {
        return n1.key.compareTo(n2.key) < 0;
    }

    private void exch(MyNode<Key> n1, MyNode<Key> n2) {
        Key temp = n1.key;
        n1.key = n2.key;
        n2.key = temp;
    }

    private void swin(MyNode<Key> node) {
        MyNode<Key> p = node.parent;
        while (p != null && less(p, node)) {
            exch(p, node);
            node = p;
            p = node.parent;
        }
    }

    private void sink(MyNode<Key> node) {
        while (node != null) {
            if (node.leftChild == null) {
                break;
            }
            MyNode<Key> biggerChild = node.leftChild;
            if (node.rightChild != null && less(node.leftChild, node.rightChild)) {
                biggerChild = node.rightChild;
            }
            if (!less(node, biggerChild)) {
                break;
            }
            exch(node, biggerChild);
            node = biggerChild;
        }
    }

    public static void main(String[] args) {
        LinkedMaxPQ pq = new LinkedMaxPQ();
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");

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
