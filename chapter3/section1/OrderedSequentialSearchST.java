/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/**
 * 使用有序链表来实现我们的有序符号表API
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first;

    class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        OrderedSequentialSearchST<String, Integer> st;
        st = new OrderedSequentialSearchST<String, Integer>();
        String[] strs = { "S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E" };
        for (int i = 0; i < strs.length; i++) {
            String key = strs[i];
            st.put(key, i);
        }
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
    }

    void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中更新
                x.val = value;
                return;
            }
        }
        // 插入新节点
        first = new Node(key, value, first);

    }

    Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        // 未命中
        return null;
    }

    void delete(Key key) {
        Node prev = null; // 记录上一个节点
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // 命中，删除节点
                if (x == first) {
                    // 首节点命中
                    first = x.next;
                }
                else {
                    // 非首节点，上一个节点链接到下个节点
                    prev.next = x.next;
                }
                return;
            }
            prev = x;
        }
        throw new IllegalArgumentException("key不存在");
    }

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        int counter = 0;
        for (Node x = first; x != null; x = x.next) {
            counter++;
        }
        return counter;
    }

    Key min() {
        Key min = null;
        for (Node x = first; x != null; x = x.next) {
            if (min == null || x.key.compareTo(min) < 0) {
                min = x.key;
            }
        }
        return min;
    }

    Key max() {
        Key max = null;
        for (Node x = first; x != null; x = x.next) {
            if (max == null || x.key.compareTo(max) > 0) {
                max = x.key;
            }
        }
        return max;
    }

    /**
     * 小于等于key的最大键
     *
     * @param key
     * @return
     */
    Key floor(Key key) {
        if (contains(key)) {
            return key;
        }
        return select(rank(key) - 1);
    }

    /**
     * 大于等于key的最小键
     *
     * @param key
     * @return
     */
    Key ceiling(Key key) {
        return select(rank(key));
    }

    /**
     * 小于key的键的数量
     *
     * @param key
     * @return
     */
    int rank(Key key) {
        int counter = 0;
        for (Node x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) < 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * 排名为k的键，k从0计数
     *
     * @param k
     * @return
     */
    Key select(int k) {
        for (Node x = first; x != null; x = x.next) {
            if (rank(x.key) == k) {
                return x.key;
            }
        }
        return null;
    }

    void deleteMin() {
        delete(min());
    }

    void deleteMax() {
        delete(max());
    }

    /**
     * [lo..hi]之间键的数量
     *
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        }
        else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        }
        else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * 排好序的键，范围[lo, hi]
     *
     * @param lo
     * @param hi
     * @return
     */
    Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> list = new ArrayList<Key>();
        int size = size(lo, hi);
        int rankOffset = rank(lo);
        for (int i = 0; i < size; i++) {
            list.add(select(i + rankOffset));
        }
        return list;
    }

    Iterable<Key> keys() {
        return keys(min(), max());
    }
}
