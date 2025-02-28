/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class CacheBinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    Key cacheKey;
    int cacheIndex;

    public CacheBinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    void resize(int max) {
        Key[] tempKeys = (Key[]) new Comparable[max];
        Value[] tempValues = (Value[]) new Object[max];
        for (int i = 0; i < N; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempValues;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        if (Objects.equals(key, cacheKey)) {
            return vals[cacheIndex];
        }
        int i = rank(key);
        if (i < N && keys[i].equals(key)) {
            cacheKey = key;
            cacheIndex = i;
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
        if (i < N && keys[i].equals(key)) {
            vals[i] = val;
            return;
        }
        // 新增key
        if (N == keys.length) {
            // 数组满，扩容
            resize(keys.length * 2);
        }
        // 插入位置往后全部右移，腾出插入位置
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
            if (cacheIndex == j) {
                cacheIndex++; // 缓存位置右移
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        if (Objects.equals(key, cacheKey)) {
            // 清除缓存
            cacheKey = null;
            cacheIndex = -1;
        }
        int i = rank(key); // 删除位置
        // 删除位置往后全部左移，覆盖删除位置
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        N--;
        if (N == keys.length / 4) {
            resize(keys.length / 2); // 缩容
        }
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
        if (k > N - 1 || k < 0) {
            return null;
        }
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
        return get(key) != null;
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

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        CacheBinarySearchST<String, Integer> st = new CacheBinarySearchST<String, Integer>(1);
        String lastPutWord = "";
        int wordCounter = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) continue; // 忽略较短单词
            if (!st.contains(word)) st.put(word, 1);
            else {
                st.put(word, st.get(word) + 1);
            }
            lastPutWord = word;
            wordCounter++;
        }
        StdOut.println("The word counter is " + wordCounter);
        StdOut.println("The last put word is " + lastPutWord);
        Queue<String> mostPutWordQ = new Queue<String>();
        int lastMaxTimes = 0;
        for (String word : st.keys()) {
            int curTimes = st.get(word);
            if (curTimes == lastMaxTimes) {
                // 同最大一样，入队
                mostPutWordQ.enqueue(word);
            }
            else if (curTimes > lastMaxTimes) {
                // 超过最大，清空队列后入队
                while (!mostPutWordQ.isEmpty()) {
                    mostPutWordQ.dequeue();
                }
                mostPutWordQ.enqueue(word);
                // 重设最大值
                lastMaxTimes = curTimes;
            }
        }
        // 打印所有最大的key
        StdOut.println("These words have most put times:");
        for (String key : mostPutWordQ) {
            StdOut.println(key + " " + st.get(key));
        }
    }
}
