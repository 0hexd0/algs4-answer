/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class InterpolationBinarySearchST<Value> {
    private Double[] keys;
    private Value[] vals;
    private int N;

    public InterpolationBinarySearchST(int capacity) {
        keys = new Double[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    void resize(int max) {
        Double[] tempKeys = new Double[max];
        Value[] tempValues = (Value[]) new Object[max];
        for (int i = 0; i < N; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempValues;
    }

    public Value get(Double key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].equals(key)) {
            return vals[i];
        }
        else {
            return null;
        }
    }

    public int rank(Double key) {
        if (isEmpty()) {
            return 0;
        }
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            double rate = (key - min()) / (max() - min());
            if (Double.isNaN(rate) || Double.isInfinite(rate)) {
                rate = 0;
            }
            if (rate > 1) {
                rate = 1;
            }
            int mid = lo + (int) Math.floor((hi - lo) * rate);
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

    public void put(Double key, Value val) {
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
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Double key) {
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
        if (N == keys.length / 4) {
            resize(keys.length / 2); // 缩容
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Double min() {
        return keys[0];
    }

    public Double max() {
        return keys[N - 1];
    }

    public Double select(int k) {
        if (k > N - 1 || k < 0) {
            return null;
        }
        return keys[k];
    }

    public Double ceiling(Double key) {
        return select(rank(key));
    }

    public Double floor(Double key) {
        if (contains(key)) {
            return key;
        }
        int i = rank(key);
        return select(Math.min(i - 1, 0));
    }

    public boolean contains(Double key) {
        int index = rank(key);
        return key.equals(select(index));
    }

    public Iterable<Double> keys(Double lo, Double hi) {
        Queue<Double> q = new Queue<Double>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(hi);
        }
        return q;
    }

    public Iterable<Double> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        InterpolationBinarySearchST<Integer>
                st = new InterpolationBinarySearchST<Integer>(2);
        Double lastPutWord = 0.0;
        int wordCounter = 0;
        while (!StdIn.isEmpty()) {
            Double key = StdIn.readDouble();
            if (!st.contains(key)) st.put(key, 1);
            else {
                st.put(key, st.get(key) + 1);
            }
            lastPutWord = key;
            wordCounter++;
        }
        StdOut.println("The word counter is " + wordCounter);
        StdOut.println("The last put word is " + lastPutWord);
        Queue<Double> mostPutWordQ = new Queue<Double>();
        int lastMaxTimes = 0;
        for (Double word : st.keys()) {
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
        for (Double key : mostPutWordQ) {
            StdOut.println(key + " " + st.get(key));
        }
    }
}
