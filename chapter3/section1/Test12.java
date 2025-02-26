/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Queue;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test12<Key extends Comparable<Key>, Value> {
    private Item[] items;
    private int N;

    class Item implements Comparable<Item> {
        Key key;
        Value val;

        public Item(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(Item o) {
            return this.key.compareTo(o.key);
        }
    }

    public Test12(int capacity) {
        items = (Item[]) Array.newInstance(Item.class, capacity);
    }

    public Test12(Item[] items) {
        Item[] temps = Arrays.copyOf(items, items.length);
        Merge.sort(temps);
        this.items = temps;
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && items[i].key.compareTo(key) == 0) {
            return items[i].val;
        }
        else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(items[mid].key);
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
        if (i < N && items[i].key.compareTo(key) == 0) {
            items[i].val = val;
            return;
        }
        // 插入位置往后全部右移，腾出插入位置
        for (int j = N; j > i; j--) {
            items[j] = items[j - 1];
        }
        items[i] = new Item(key, val);
        N++;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = rank(key); // 删除位置
        // 删除位置往后全部左移，覆盖删除位置
        for (int j = i; j < N - 1; j++) {
            items[j] = items[j + 1];
        }
        // 删除最后一个item的引用
        items[N - 1] = null;
        N--;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key min() {
        return items[0].key;
    }

    public Key max() {
        return items[N - 1].key;
    }

    public Key select(int k) {
        return items[k].key;
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
            q.enqueue(items[i].key);
        }
        if (contains(hi)) {
            q.enqueue(hi);
        }
        return q;
    }

    public static void main(String[] args) {

    }
}
