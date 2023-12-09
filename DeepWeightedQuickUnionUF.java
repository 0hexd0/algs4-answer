import edu.princeton.cs.algs4.StdOut;

// 依据深度加权
public class DeepWeightedQuickUnionUF {
    private int[] id; // 分量id
    private int[] sz; // 各根节点所对应树的高度
    private int count; // 分量数量

    public DeepWeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 0; // 默认深度0
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

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] = Math.max(sz[j], sz[i] + 1);
        }
        else {
            id[j] = i;
            sz[i] = Math.max(sz[i], sz[j] + 1);
        }

        count--;
    }

    public static void main(String[] args) {
        // int N = StdIn.readInt();
        int N = 16;
        StdOut.println("N " + N);
        DeepWeightedQuickUnionUF uf = new DeepWeightedQuickUnionUF(N);

        // while (!StdIn.isEmpty()) {
        //     int p = StdIn.readInt();
        //     int q = StdIn.readInt();
        //     if (uf.connected(p, q)) continue; // 已联通，忽略
        //     uf.union(p, q);
        //     StdOut.println(p + " " + q);
        //     StdOut.println(uf.count() + " components");
        // }
        // Perform union operations to create a path of length 4
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(0, 2);

        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(4, 6);

        uf.union(0, 4);
        //
        // uf.union(8, 9);
        // uf.union(10, 11);
        // uf.union(8, 10);
        //
        // uf.union(12, 13);
        // uf.union(14, 15);
        // uf.union(12, 14);
        //
        // uf.union(8, 12);
        //
        // uf.union(0, 8);

        // Output the components and connections
        StdOut.println("Components: " + uf.count());
        for (int i = 0; i < N; i++) {
            StdOut.println("id[" + i + "] = " + uf.id[i]);
        }
        for (int i = 0; i < N; i++) {
            StdOut.println("sz[" + i + "] = " + uf.sz[i]);
        }
    }
}
