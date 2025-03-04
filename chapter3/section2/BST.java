/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // 树节点数量
        private int H; // 树高度
        private int L; // 树内部路径长度

        public Node(Key key, Value val, int N, int H, int L) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.H = H;
            this.L = L;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        else {
            return x.N;
        }
    }

    public int height() {
        return height(root);
    }

    public int height(Node x) {
        if (x == null) {
            return 0;
        }
        else {
            return x.H;
        }
    }

    public int length() {
        return length(root);
    }

    public int length(Node x) {
        if (x == null) {
            return 0;
        }
        return x.L;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, 0, 0);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(height(x.left), height(x.right)) + 1;
        x.L = length(x.left) + size(x.left) + length(x.right) + size(x.right);
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        else {
            return min(x.left);
        }
    }

    public Key max() {
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        else {
            return max(x.right);
        }
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    public Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        }
        else {
            return x;
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    public Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        }
        else {
            return x;
        }
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    public Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int leftSize = size(x.left);
        if (leftSize == k) {
            return x;
        }
        else if (leftSize > k) {
            return select(x.left, k);
        }
        else {
            return select(x.right, k - leftSize - 1);
        }
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    public int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return size(x.left);
        }
        else if (cmp < 0) {
            return rank(key, x.left);
        }
        else {
            return size(x.left) + 1 + rank(key, x.right);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        else {
            x.left = deleteMin(x.left);
            x.N = size(x.left) + size(x.right) + 1;
            x.H = Math.max(height(x.left), height(x.right)) + 1;
            x.L = length(x.left) + size(x.left) + length(x.right) + size(x.right);
            return x;
        }
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    public Node delete(Key key, Node x) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(key, x.left);
        }
        else if (cmp > 0) {
            x.right = delete(key, x.right);
        }
        else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(height(x.left), height(x.right)) + 1;
        x.L = length(x.left) + size(x.left) + length(x.right) + size(x.right);
        return x;
    }

    void print(Node x) {
        if (x == null) {
            return;
        }
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 & cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    // 节点高度
    public int getNodeHeight(Node x) {
        if (x == null) {
            return 0;
        }
        return Math.max(getNodeHeight(x.left), getNodeHeight(x.right)) + 1;
    }

    // 内部路径长度
    public int getNodeLength(Node x) {
        if (x == null) {
            return 0;
        }
        return getNodeHeight(x.left) + size(x.left) + getNodeHeight(x.right) + size(x.right);
    }

    // 随机命中查找的平均比较次数
    public float avgCompares(Node x) {
        if (x == null) {
            return 0;
        }
        return length(x) / ((float) size(x)) + 1;
    }

    // 接受一个整型参数N并计算一棵最优(完美平衡的)二叉查找树中的一次随机查找命中平均所需的比较次数
    // 如果树中的链接数量为2的幂,那么所有的空链接都应该在同一层,否则则分布在最底部的两层中
    public static float optCompares(int N) {
        int deep = 0;
        int sum = 0;
        while (Math.pow(2, deep + 1) - 1 <= N) {
            sum += deep * Math.pow(2, deep);
            deep++;
        }
        // 完全二叉树的深度是deep-1
        int others = N - (int) (Math.pow(2, deep) - 1);
        sum += others * deep;
        return sum / ((float) N) + 1;
    }

    public static void main(String[] args) {
        StdOut.println(optCompares(11));
    }
}
