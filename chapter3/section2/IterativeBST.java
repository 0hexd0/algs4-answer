/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class IterativeBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // 树节点数量

        public Node(Key key, Value val, int N, int H) {
            this.key = key;
            this.val = val;
            this.N = N;
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

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            }
            else if (cmp > 0) {
                x = x.right;
            }
            else {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        Node t = x;
        Node p = null;
        int cmp = 0;
        while (t != null) {
            cmp = key.compareTo(t.key);
            if (cmp == 0) {
                t.val = val;
                return x; // 命中返回根节点
            }
            else if (cmp < 0) {
                p = t; // 记录父节点
                t = t.left; // 小于当前节点，继续遍历左子节点
            }
            else {
                p = t; // 记录父节点
                t = t.right; // 大于当前节点，继续遍历右子节点
            }
        }
        Node newNode = new Node(key, val, 1, 0);
        // 传入node为null
        if (p == null) {
            return newNode;
        }
        // 新增场景，基于最后一次比较结果插在父节点左或右
        if (cmp < 0) {
            p.left = newNode;
        }
        else {
            p.right = newNode;
        }
        // 再次遍历查找路径，更新node内部记录值
        t = x;
        while (t != null) {
            cmp = key.compareTo(t.key); // 这里不会为0
            if (cmp == 0) {
                // 找到本次插入的节点，结束
                break;
            }
            else if (cmp < 0) {
                t.N++; // 被插入的父节点，增加计数
                t = t.left; // 小于当前节点，继续遍历左子节点
            }
            else {
                t.N++; // 被插入的父节点，增加计数
                t = t.right; // 大于当前节点，继续遍历右子节点
            }
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
        while (x.left != null) {
            x = x.left;
        }
        return x;
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
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    public Node floor(Node x, Key key) {
        Node p = null;
        int cmp = 0;
        while (x != null) {
            cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x;
            }
            if (cmp < 0) {
                x = x.left;
            }
            else {
                if (x.right == null || key.compareTo(x.right.key) < 0) {
                    return x;
                }
                else {
                    x = x.right;
                }
            }
            p = x;
        }
        return p;
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

    public static void main(String[] args) {

    }
}
