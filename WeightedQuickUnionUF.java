import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Optional;

public class WeightedQuickUnionUF {
    private int[] id; // 分量id
    private int[] sz; // 各个根节点所对应的分量大小
    private int count; // 分量数量

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
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
        int i = find(p);
        int j = find(q);

        int times = findTimes(p) + findTimes(q);

        if (i == j) {
            return times;
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;

        return times + 4;
    }

    public static void main(String[] args) {
        In in = new In("./algs4-data/mediumUF.txt");
        String fl = in.readLine();
        int N = Integer.parseInt(fl);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        AmortizedCostGraph graph = new AmortizedCostGraph("加权quick-union算法", 900, 20,
                                                          Optional.empty(),
                                                          Optional.empty());
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
