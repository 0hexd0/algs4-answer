import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class FilterPoint implements Comparable<FilterPoint> {
    double x;
    double y;
    double z;
    double dis;

    FilterPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.dis = Math.sqrt(1.0 * x * x + y * y + z * z);
    }

    public int compareTo(FilterPoint o) {
        return (int) (dis - o.dis);
    }
}

public class SelectFilter {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MaxPQ<FilterPoint> pq = new MaxPQ<>(M + 1);

        while (!StdIn.isEmpty()) {
            double x = Double.parseDouble(StdIn.readString());
            double y = Double.parseDouble(StdIn.readString());
            double z = Double.parseDouble(StdIn.readString());
            pq.insert(new FilterPoint(x, y, z));
            if (pq.size() == M + 1) {
                pq.delMax();
            }
        }

        while (!pq.isEmpty()) {
            FilterPoint min = pq.delMax();
            StdOut.printf("%f\n", min.dis);
        }
    }
}
