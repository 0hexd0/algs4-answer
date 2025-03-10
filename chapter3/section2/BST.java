/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private Node cacheNode; // 缓存节点

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

    public int size(Key lo, Key hi) {
        return size(root, lo, hi);
    }

    public int size(Node x, Key lo, Key hi) {
        if (x == null) {
            return 0;
        }
        int cmplo = x.key.compareTo(lo);
        int comhi = x.key.compareTo(hi);
        if (cmplo < 0) {
            return size(x.right, lo, hi);
        }
        if (comhi > 0) {
            return size(x.left, lo, hi);
        }
        return size(x.left, lo, hi) + 1 + size(x.right, lo, hi);
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
        // 命中缓存
        if (cacheNode != null && key.compareTo(cacheNode.key) == 0) {
            return cacheNode.val;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        cacheNode = x;
        return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, 0, 0);
        }
        // 命中缓存
        if (cacheNode != null && key.compareTo(cacheNode.key) == 0) {
            cacheNode.val = val;
            return x;
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
            cacheNode = x;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(height(x.left), height(x.right)) + 1;
        x.L = length(x.left) + size(x.left) + length(x.right) + size(x.right);
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

    public Key select(int k) {
        Node target = select(root, k);
        if (target != null) {
            return target.key;
        }
        return null;
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
        if (x == null) {
            return null;
        }
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
            x.N = size(x.left) + size(x.right) + 1;
            x.H = Math.max(height(x.left), height(x.right)) + 1;
            x.L = length(x.left) + size(x.left) + length(x.right) + size(x.right);
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
        int index = rank(key, x);
        return key.equals(select(index));
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

    private ArrayList<Key> iterativeKeys() {
        ArrayList<Key> keys = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (stack.size() > 0) {
            Node peek = stack.peek();
            if (peek.left != null && !keys.contains(peek.left.key)) {
                stack.push(peek.left);
            }
            else {
                Node poped = stack.pop();
                keys.add(poped.key);
                if (poped.right != null) {
                    stack.push(poped.right);
                }
            }
        }
        return keys;
    }

    public void printLevel() {
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (queue.size() > 0) {
            Node x = queue.dequeue();
            StdOut.println(x.key);
            if (x.left != null) {
                queue.enqueue(x.left);
            }
            if (x.right != null) {
                queue.enqueue(x.right);
            }
        }
    }

    public void printLevel(Node x, Queue<Key> queue) {
        if (x == null) {
            return;
        }
        queue.enqueue(x.key);
        printLevel(x.left, queue);
        printLevel(x.right, queue);
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
        return getNodeLength(x.left) + size(x.left) + getNodeLength(x.right) + size(x.right);
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

    public Key randomKey() {
        return randomKey(root);
    }

    public Key randomKey(Node x) {
        Node target = select(x, StdRandom.uniformInt(0, size(x)));
        if (target != null) {
            return target.key;
        }
        return null;
    }

    public void draw() {
        StdDraw.setTitle("BST");
        int height = height(root);
        int rows = (height + 2) * 2 + 1;
        double rowHeight = 1.0 / rows;
        drawNode(root, rowHeight, 1 - rowHeight, rowHeight, rowHeight);
        StdOut.println(keys());
    }

    public void drawNode(Node n, double loX, double hiX, double loY, double rowHeight) {
        double midX = loX + (hiX - loX) / 2;
        double midY = 1 - (loY + rowHeight / 2);
        StdDraw.setPenRadius(0.005);
        // 绘制左侧连接线
        double leftCMidX = loX + (midX - loX) / 2;
        double leftCMidY = 1 - (loY + 2 * rowHeight + rowHeight / 2);
        StdDraw.line(midX, midY, leftCMidX, leftCMidY);
        // 绘制右侧连接线
        double rightCMidX = midX + (hiX - midX) / 2;
        double rightCMidY = 1 - (loY + 2 * rowHeight + rowHeight / 2);
        StdDraw.line(midX, midY, rightCMidX, rightCMidY);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(midX, midY, rowHeight / 2); // 填充圆
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.002);
        StdDraw.circle(midX, midY, rowHeight / 2); // 绘制圆
        StdDraw.text(midX, midY, n.key.toString()); // 绘制文字
        if (n.left != null) {
            drawNode(n.left, loX, midX, loY + 2 * rowHeight, rowHeight);
        }
        if (n.right != null) {
            drawNode(n.right, midX, hiX, loY + 2 * rowHeight, rowHeight);
        }
    }

    public boolean isBinaryTree() {
        return isBinaryTree(root);
    }

    public boolean isBinaryTree(Node x) {
        if (x == null) {
            return true;
        }
        if (size(x) == size(x.left) + size(x.right) + 1) {
            return isBinaryTree(x.left) && isBinaryTree(x.right);
        }
        return false;
    }

    public boolean isOrdered() {
        return isOrdered(root, min(), max());
    }

    public boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        int minCmp = x.key.compareTo(min);
        int maxCmp = x.key.compareTo(max);
        if (minCmp < 0 || maxCmp > 0) {
            return false;
        }
        if (x.left != null && x.left.key.compareTo(x.key) >= 0) {
            return false;
        }
        if (x.right != null && x.right.key.compareTo(x.key) <= 0) {
            return false;
        }
        return isOrdered(x.left, min, max) && isOrdered(x.right, min, max);
    }

    public boolean hasNoDuplicates() {
        return hasNoDuplicates(root);
    }

    public boolean hasNoDuplicates(Node x) {
        if (x == null) {
            return true;
        }
        ArrayList<Key> list = new ArrayList<>();
        return hasNoDuplicates(x, list);
    }

    public boolean hasNoDuplicates(Node x, List<Key> list) {
        if (x == null) {
            return true;
        }
        if (list.contains(x.key)) {
            return false;
        }
        list.add(x.key);
        return hasNoDuplicates(x.left) && hasNoDuplicates(x.right);
    }

    public boolean isBST() {
        return isBST(root);
    }

    public boolean isBST(Node x) {
        if (!isBinaryTree(root)) {
            return false;
        }
        if (!isOrdered(root, min(), max())) {
            return false;
        }
        if (!hasNoDuplicates(root)) {
            return false;
        }
        return true;
    }

    public boolean checkRankAndSelect() {
        if (root == null) {
            return true;
        }
        for (int i = 0; i < size(root); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (!Objects.equals(key, select(rank(key)))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // StdOut.println(optCompares(10000000));
        // StdOut.println(1.39 * Math.log(10000000) / Math.log(2));
        BST<String, Integer> st = new BST<>();
        String[] words = { "c1", "c4", "a1", "a1", "a2", "b2", "a1", "b1", "b2", "b3", "a2", "c1" };

        for (String word : words) {
            st.put(word, 1);
        }
        StdOut.println("isBinaryTree: " + st.isBinaryTree());
        StdOut.println("isOrdered: " + st.isOrdered());
        StdOut.println("hasNoDuplicates: " + st.hasNoDuplicates());
        StdOut.println("isBST: " + st.isBST());
        StdOut.println("checkRankAndSelect: " + st.checkRankAndSelect());
        StdOut.println("printLevel: ");
        st.printLevel();
        StdOut.println("iterativeKeys: " + st.iterativeKeys());
        st.draw();
    }
}
