import edu.princeton.cs.algs4.StdOut;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MinPQ(Comparable[] arr) {
        pq = (Key[]) new Comparable[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            pq[++N] = (Key) arr[i];
        }
        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        // 将堆移动到一个大小为max的新数组
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key v) {
        if (N == pq.length - 1) {
            resize(pq.length * 2);
        }
        pq[++N] = v;
        swin(N);
        // print_pq();
    }

    public Key min() {
        Key min = pq[1];
        return min;
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        if (N > 0 && N == pq.length / 4) {
            resize(pq.length / 2);
        }
        return min;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    // swim from k to 1
    private void swin(int k) {
        Key cur = pq[k];
        int mp = findMinParent(k); // mp是pq[i] >= qp[k] 中最大的i，其中i是k的所有父节点
        // StdOut.println("\nfindMinParent" + mp);
        int p = k;
        while (p > mp) {
            p = k / 2;
            pq[k] = pq[p];
            k = p;
        }
        if (k != mp) {
            StdOut.println("k != mp");
        }
        pq[k] = cur;
    }

    void print_pq() {
        StdOut.printf("\npq is ");
        for (int i = 1; i <= N; i++) {
            StdOut.printf(" %s", pq[i]);
        }
        StdOut.printf("\n");
    }

    private void sink(int k) {
        Key cur = pq[k];
        while (k * 2 <= N) {
            int c = k * 2;
            if (c != N && less(c + 1, c)) {
                c++;
            }
            if (cur.compareTo(pq[c]) <= 0) {
                break;
            }
            pq[k] = pq[c];
            k = c;
        }
        pq[k] = cur;
    }

    /**
     * 以本节点为根节点的树的高度，从1开始
     **/
    int height(int idx) {
        return (int) Math.floor(Math.log(idx) / Math.log(2)) + 1;
    }

    public int findMid(int start, int end) {
        if (start >= end) {
            return end;
        }
        int hs = height(start);
        int he = height(end);

        int hc = hs + (he - hs) / 2;
        int divTimes = he - hc;
        while (divTimes > 0) {
            end /= 2;
            divTimes--;
        }
        return end;
    }

    public int findMinParent(int k) {
        Key cur = pq[k];
        int mid = findMid(1, k); // find mid
        int cmp = 0;
        while (mid > 2 && (cmp = pq[mid].compareTo(cur)) != 0) {
            StdOut.printf("mid is %d\n", mid);
            if (cmp < 0) {
                // 中点<=cur，中点的父节点更小不用比较
                mid = findMid(mid * 2, k);
            }
            else {
                // mid>cur，1～mid中递归找
                mid = findMid(1, mid);
            }
        }

        if (mid == 2) {
            return pq[1].compareTo(cur) < 0 ? 2 : 1;
        }

        return mid;
    }

    public static void main(String[] args) {
        MinPQ pq = new MinPQ<>(10);

        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");
        StdOut.print(pq.delMin());
        pq.insert("R");
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMin());
        pq.insert("I");
        StdOut.print(pq.delMin());
        pq.insert("T");
        StdOut.print(pq.delMin());
        pq.insert("Y");
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMin());
        pq.insert("Q");
        pq.insert("U");
        pq.insert("E");
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMin());
        StdOut.print(pq.delMin());
        pq.insert("U");
        StdOut.print(pq.delMin());
        pq.insert("E");
    }
}
