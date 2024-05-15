import edu.princeton.cs.algs4.StdOut;

public class DoublePQ<Key extends Comparable<Key>> {
    private Key[] min_pq; // 最大优先队列
    private Key[] max_pq; // 最小优先队列
    private int N = 0;

    public DoublePQ(int maxN) {
        min_pq = (Key[]) new Comparable[maxN + 1];
        max_pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void exch(Key[] pq, int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(Key[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void insert(Key v) {
        if (N == max_pq.length) {
            throw new IllegalArgumentException("超出最大长度");
        }
        max_pq[++N] = v;
        swinMax(N);

        min_pq[N] = v;
        swinMin(N);
    }

    private void swinMax(int k) {
        Key cur = max_pq[k];
        int p = k / 2;
        while (p >= 1 && max_pq[p].compareTo(cur) < 0) {
            max_pq[k] = max_pq[p];
            k = p;
            p = k / 2;
        }
        max_pq[k] = cur;
    }

    private void swinMin(int k) {
        Key cur = min_pq[k];
        int p = k / 2;
        while (p >= 1 && min_pq[p].compareTo(cur) > 0) {
            min_pq[k] = min_pq[p];
            k = p;
            p = k / 2;
        }
        min_pq[k] = cur;
    }

    private void sinkMax(int k) {
        Key cur = max_pq[k];
        while (k * 2 <= N) {
            int c = k * 2;
            if (c != N && less(max_pq, c, c + 1)) {
                c++;
            }
            if (cur.compareTo(max_pq[c]) >= 0) {
                break;
            }
            max_pq[k] = max_pq[c];
            k = c;
        }
        max_pq[k] = cur;
    }

    private void sinkMin(int k) {
        Key cur = min_pq[k];
        while (k * 2 <= N) {
            int c = k * 2;
            if (c != N && less(min_pq, c + 1, c)) {
                c++;
            }
            if (cur.compareTo(min_pq[c]) <= 0) {
                break;
            }
            min_pq[k] = min_pq[c];
            k = c;
        }
        min_pq[k] = cur;
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("空队列无法删除");
        }
        // 删除min_pq中的最大值
        int firstLeaf = N / 2 + 1; // 第一个叶子
        // 遍历所有叶子，找出最大值
        int maxIdx = firstLeaf;
        for (int i = firstLeaf + 1; i <= N; i++) {
            if (min_pq[i].compareTo(min_pq[maxIdx]) > 0) {
                maxIdx = i;
            }
        }
        // 交换最大值和最后一个：最大值的叶子节点变小了，不会影响堆的性质
        exch(min_pq, maxIdx, N);
        // 可选：删除最后一个
        min_pq[N] = null;

        // 删除max_pq中的最大值
        Key max = max_pq[1];
        exch(max_pq, 1, N--);
        max_pq[N + 1] = null;
        sinkMax(1);
        return max;
    }

    public Key delMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("空队列无法删除");
        }
        // 删除max_pq中的最小值
        int firstLeaf = N / 2 + 1; // 第一个叶子
        // 遍历所有叶子，找出最小值
        int minIdx = firstLeaf;
        for (int i = firstLeaf + 1; i <= N; i++) {
            if (max_pq[i].compareTo(max_pq[minIdx]) < 0) {
                minIdx = i;
            }
        }
        // 交换最小值和最后一个：最小值的叶子节点变小了，不会影响堆的性质
        exch(max_pq, minIdx, N);
        // 可选：删除最后一个
        max_pq[N] = null;

        // 删除min_pq中的最小值
        Key min = min_pq[1];
        exch(min_pq, 1, N--);
        min_pq[N + 1] = null;
        sinkMin(1);
        return min;
    }

    public Key max() {
        StdOut.printf("\n max is %s\n", max_pq[1]);
        return isEmpty() ? null : max_pq[1];
    }

    public Key min() {
        StdOut.printf("\n min is %s\n", min_pq[1]);
        return isEmpty() ? null : min_pq[1];
    }

    public static void main(String[] args) {
        DoublePQ pq = new DoublePQ(10);
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");
        pq.min();
        pq.max();
        StdOut.print(pq.delMax());
        pq.insert("R");
        pq.min();
        pq.max();
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMax());
        pq.insert("I");
        StdOut.print(pq.delMin());
        pq.insert("T");
        StdOut.print(pq.delMax());
        pq.insert("Y");
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert("Q");
        pq.insert("U");
        pq.min();
        pq.insert("E");
        pq.min();
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMax());
        StdOut.print(pq.delMax());
        pq.insert("U");
        StdOut.print(pq.delMax());
        pq.insert("E");
        pq.min();
    }
}
