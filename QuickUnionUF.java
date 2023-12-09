/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Optional;

public class QuickUnionUF {
    private int[] id; // 分量id
    private int count; // 分量数量

    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public int findTimes(int p) {
        int counter = 1;
        while (p != id[p]) {
            p = id[p];
            counter++;
        }
        return counter;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        int times = findTimes(p) + findTimes(q);

        if (pRoot == qRoot) {
            return times;
        }

        id[pRoot] = qRoot;

        count--;

        return times + 1;
    }

    public static void main(String[] args) {
        In in = new In("./algs4-data/mediumUF.txt");
        String fl = in.readLine();
        int N = Integer.parseInt(fl);
        QuickUnionUF uf = new QuickUnionUF(N);
        AmortizedCostGraph graph = new AmortizedCostGraph("quick-union算法", 900, 100,
                                                          Optional.ofNullable(800),
                                                          Optional.ofNullable(100));
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            int times = uf.union(p, q);
            graph.addPoint(times);
            StdOut.println("times: " + times);

            StdOut.println(p + " " + q);
            StdOut.println(uf.count() + " components");
        }
    }
}
