/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.UF;


public class ErdosRenyi {
    public static int count(int n) {
        int edges = 0;
        UF uf = new UF(n);
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

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int[] edges = new int[trials];

        for (int t = 0; t < trials; t++) {
            edges[t] = count(N);
        }

        StdOut.println("1/2 n ln n = " + 0.5 * N * Math.log(N));
        StdOut.println("mean = " + StdStats.mean(edges));

    }
}
