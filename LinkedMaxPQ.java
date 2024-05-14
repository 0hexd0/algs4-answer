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

    /**
     * 以本节点为根节点的树的高度，从1开始
     **/
    int height() {
        return (int) Math.ceil(Math.log(count + 1) / Math.log(2));
    }

    /**
     * 以本节点为根节点的树，是否是满二叉树
     **/
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
        } else if (!root.leftChild.isFull()) {
            // 左子树没满
            return findNextNode(root.leftChild);
        } else {
            return findNextNode(root.rightChild);
        }
    }

    // 插入新节点，复杂度lgN
    public void insert(Key v) {
        MyNode<Key> newNode = new MyNode<>(v);
        tail = newNode;

        if (root == null) {
            root = newNode;
            return;
        }

        MyNode<Key> nextNode = findNextNode(root); // 查找插入点，复杂度lgN

        if (nextNode.leftChild == null) {
            nextNode.leftChild = newNode;
        } else {
            nextNode.rightChild = newNode;
        }
        newNode.parent = nextNode; // 在插入点插入新节点
        while (nextNode != null) {
            nextNode.count += 1; //
            nextNode = nextNode.parent; // 更新插入点及其父节点计数器，复杂度lgN
        }
        swin(newNode); // 新节点上升到合适位置，复杂度lgN
    }

    // 删除最大节点，复杂度lgN
    public Key delMax() {
        // 记录最大值，交换最大节点和尾节点
        Key max = root.key;
        root.key = tail.key;
        if (tail == root) {
            // 删除根节点
            tail = null;
            root = null;
            return max;
        }
        // 删除尾节点，父节点不链接尾节点
        MyNode<Key> tp = tail.parent;
        if (tp.leftChild == tail) {
            tp.leftChild = null;
        } else {
            tp.rightChild = null;
        }
        // 尾节点的所有祖先计数-1，复杂度lgN
        while (tp != null) {
            tp.count -= 1;
            tp = tp.parent;
        }
        // 查找新的尾节点 复杂度lgN
        MyNode<Key> nextNode = findNextNode(root);
        if (nextNode.leftChild != null) {
            nextNode = nextNode.leftChild;
        } else if (nextNode.rightChild != null) {
            nextNode = nextNode.rightChild;
        }
        tail = nextNode;

        sink(root); // 首节点沉到正确位置
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
