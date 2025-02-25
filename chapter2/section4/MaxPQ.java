/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2.section4;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // 基于堆的完全二叉树
    private int N = 0; // 存储于pq[1..N]中，pq[0]没有使用

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ(Comparable[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        N = a.length;
        int i = N / 2;
        while (i-- >= 1) {
            sink(i);
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (N == pq.length - 1) {
            resize(true);
        }
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        if (N <= (pq.length - 1) / 4) {
            resize(false);
        }
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void resize(boolean bigger) {
        Key[] newPq;
        if (bigger) {
            newPq = (Key[]) new Comparable[2 * N + 1];
        }
        else {
            newPq = (Key[]) new Comparable[(N - 1) / 2 + 1];
        }
        // move elements
        for (int i = 0; i <= N; i++)
            newPq[i] = pq[i];
        pq = newPq;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {

    }
}
