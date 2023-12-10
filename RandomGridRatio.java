/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;


public class RandomGridRatio {
    public static double count(int n, RandomGrid.Connection[] cons) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        Stopwatch timer = new Stopwatch();
        int counter = 0;
        while (uf.count() > 1) {
            RandomGrid.Connection con = cons[counter++];
            uf.union(con.p, con.q);
        }
        return timer.elapsedTime();
    }

    public static double timeTrial(int n, int trials) {
        double timer = 0;
        for (int i = 0; i < trials; i++) {
            RandomGrid.Connection[] cons = RandomGrid.generate(n);
            timer += count(n, cons);
        }
        return timer;
    }

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);

        double prev = timeTrial(125, trials);
        for (int n = 125; true; n += n) {
            double time = timeTrial(n, trials);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
            prev = time;
        }

    }
}
