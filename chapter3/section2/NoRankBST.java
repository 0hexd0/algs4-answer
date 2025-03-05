/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class NoRankBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val, int N, int H, int L) {
            this.key = key;
            this.val = val;
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
            return size(x.left) + size(x.right) + 1;
        }
    }

    public int height() {
        return height(root);
    }

    public int height(Node x) {
        if (x == null) {
            return 0;
        }
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public int length() {
        return length(root);
    }

    public int length(Node x) {
        if (x == null) {
            return 0;
        }
        return length(x.left) + size(x.left) + length(x.right) + size(x.right);
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
        return x;
    }

    public Key min() {
        Node taget = min(root);
        if (taget != null) {
            return taget.key;
        }
        else {
            return null;
        }
    }

    public Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        else {
            return min(x.left);
        }
    }

    public Key max() {
        Node target = max(root);
        if (target != null) {
            return target.key;
        }
        else {
            return null;
        }
    }

    public Node max(Node x) {
        if (x == null) {
            return null;
        }
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

    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        else {
            x.left = deleteMin(x.left);
            return x;
        }
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    public Node deleteMax(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x.left;
        }
        else {
            x.right = deleteMax(x.right);
            return x;
        }
    }

    public void delete(Key key) {
        root = delete(key, root);
    }

    public boolean contains(Key key) {
        return contains(key, root);
    }

    public boolean contains(Key key, Node x) {
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return contains(key, x.left);
        }
        else if (cmp > 0) {
            return contains(key, x.right);
        }
        else {
            return true;
        }
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
        if (x == null || lo == null || hi == null) {
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

    // 随机命中查找的平均比较次数
    public float avgCompares(Node x) {
        if (x == null) {
            return 0;
        }
        return length(x) / ((float) size(x)) + 1;
    }

    // 接受一个整型参数N并计算一棵最优(完美平衡的)二叉查找树中的一次随机查找命中平均所需的比较次数
    // 如果树中的链接数量为2的幂,那么所有的空链接都应该在同一层,否则则分布在最底部的两层中
    public static double optCompares(int N) {
        int deep = 0;
        int sum = 0;
        while (Math.pow(2, deep + 1) - 1 <= N) {
            sum += deep * Math.pow(2, deep);
            deep++;
        }
        // 完全二叉树的深度是deep-1
        int others = N - (int) (Math.pow(2, deep) - 1);
        sum += others * deep;
        return sum / ((double) N) + 1;
    }

    public static void main(String[] args) {
        StdOut.println(optCompares(10000000));
        StdOut.println(1.39 * Math.log(10000000) / Math.log(2));
    }
}
