/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


public class ErdosRenyiRatio {
    public static int count(int n) {
        int edges = 0;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (uf.count() > 1) {
            int i = StdRandom.uniformInt(0, n);
            int j = StdRandom.uniformInt(0, n);
            if (uf.find(i) != uf.find(j)) {
                uf.union(i, j);
            }
            edges++;
        }
        return edges;
    }

    public static double timeTrial(int n, int trials) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < trials; i++) {
            count(n);
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);

        double prev = timeTrial(125, trials);
        for (int n = 250; true; n += n) {
            double time = timeTrial(n, trials);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
            prev = time;
        }

    }
}
