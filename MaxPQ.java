import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swin(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swin(int k) {
        int p = k / 2;
        while (p >= 1 && less(p, k)) {
            exch(p, k);
            k = p;
            p = k / 2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int c = k * 2;
            if (c < N && less(c, c + 1)) {
                c++;
            }
            if (!less(k, c)) {
                break;
            }
            exch(k, c);
            k = c;
        }
    }

    public static void main(String[] args) {
        MaxPQ pq = new MaxPQ(10);
        pq.insert("C");
        pq.insert("A");
        pq.insert("E");
        StdOut.println(pq.delMax());
        pq.insert("F");
        pq.insert("Z");
        StdOut.println(pq.delMax());
        StdOut.println(pq.delMax());
    }
}
