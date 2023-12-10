/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Optional;

public class QuickFindUF {
    private int[] id; // 分量id
    private int count; // 分量数量

    public QuickFindUF(int N) {
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
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        int times = 2;

        if (pID == qID) {
            return times;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
                times += 2;
            }
            else {
                times += 1;
            }
        }

        count--;
        return times;
    }

    public static void main(String[] args) {
        In in = new In("./algs4-data/mediumUF.txt");
        String fl = in.readLine();
        int N = Integer.parseInt(fl);
        QuickFindUF uf = new QuickFindUF(N);
        AmortizedCostGraph graph = new AmortizedCostGraph("quick-find算法", 900, 1300,
                                                          Optional.empty(), Optional.empty());
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
