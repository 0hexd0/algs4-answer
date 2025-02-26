/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            }
            else if (cmp > 0) {
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        // 查找键，找到更新值，否则插入
        int i = rank(key); // 插入位置
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        // 插入位置往后全部右移，腾出插入位置
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = rank(key); // 删除位置
        // 删除位置往后全部左移，覆盖删除位置
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        N--;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        return select(rank(key));
    }

    public Key floor(Key key) {
        if (contains(key)) {
            return key;
        }
        int i = rank(key);
        return select(Math.min(i - 1, 0));
    }

    public boolean contains(Key key) {
        return select(rank(key)) == key;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(hi);
        }
        return q;
    }

    public static void main(String[] args) {

    }
}
