/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


public class ErdosRenyiCompare {
    public static int countFind(int n) {
        int edges = 0;
        QuickFindUF uf = new QuickFindUF(n);
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

    public static int countUnion(int n) {
        int edges = 0;
        QuickUnionUF uf = new QuickUnionUF(n);
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

    public static int countWeightUnion(int n) {
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

    public static int countCompressUnion(int n) {
        int edges = 0;
        CompressWeightedQuickUnionUF uf = new CompressWeightedQuickUnionUF(n);
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

    public static double timeTrial(int n, int trials, String type) {
        Stopwatch timer = new Stopwatch();
        if (type.equals("union")) {
            for (int i = 0; i < trials; i++) {
                countUnion(n);
            }
        }
        else if (type.equals("find")) {
            for (int i = 0; i < trials; i++) {
                countFind(n);
            }
        }
        else if (type.equals("weight")) {
            for (int i = 0; i < trials; i++) {
                countWeightUnion(n);
            }
        }
        else {
            for (int i = 0; i < trials; i++) {
                countCompressUnion(n);
            }
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int trials = Integer.parseInt(args[0]);

        for (int n = 250; true; n += n) {
            // double timeFind = timeTrial(n, trials, "find");
            // double timeUnion = timeTrial(n, trials, "union");
            double weightUnion = timeTrial(n, trials, "weight");
            double compressUnion = timeTrial(n, trials, "compress");
            StdOut.printf("%7d %7.1f %7.1f\n", n, weightUnion, compressUnion);
        }

    }
}
