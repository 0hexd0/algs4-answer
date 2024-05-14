import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

class MyPoint implements Comparable<MyPoint> {
    int i;
    int j;
    int sum;

    MyPoint(int i, int j) {
        this.i = i;
        this.j = j;
        sum = (int) Math.pow(i, 3) + (int) Math.pow(j, 3);
    }

    public int compareTo(MyPoint o) {
        return sum - o.sum;
    }
}

public class CubeSum {
    public static void main(String[] args) {
        int N = 100;
        MinPQ<MyPoint> pq = new MinPQ<>(N + 1);
        for (int i = 0; i <= N; i++) {
            pq.insert(new MyPoint(i, 0));
        }
        MyPoint min;
        MyPoint lastMin = null;
        while (!pq.isEmpty()) {
            min = pq.delMin();
            if (lastMin != null && lastMin.sum == min.sum) {
                StdOut.printf("%d^3 + %d^3 = %d^3 + %d^3\n", lastMin.i, lastMin.j, min.i, min.j);
            }
            lastMin = min;
            if (min.j < N) {
                pq.insert(new MyPoint(min.i, min.j + 1));
            }
            StdOut.printf("%d\n", min.sum);
        }
    }
}
