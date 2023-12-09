import edu.princeton.cs.algs4.StdOut;

public class CompressWeightedQuickUnionUF {
    private int[] id; // 分量id
    private int[] sz; // 各个根节点所对应的分量大小
    private int count; // 分量数量

    public CompressWeightedQuickUnionUF(int N) {
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
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        while (p != root) {
            int q = id[p];
            id[p] = id[root];
            p = q;
        }
        return root;
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
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }

    public static void main(String[] args) {
        // int N = StdIn.readInt();
        int N = 16;
        StdOut.println("N " + N);
        CompressWeightedQuickUnionUF uf = new CompressWeightedQuickUnionUF(N);

        // Perform union operations to create a path of length 4
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(0, 2);

        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(4, 6);

        uf.union(0, 4);

        uf.union(8, 9);
        uf.union(10, 11);
        uf.union(8, 10);

        uf.union(12, 13);
        uf.union(14, 15);
        uf.union(12, 14);

        uf.union(8, 12);

        uf.union(0, 8);

        // Output the components and connections
        StdOut.println("Components: " + uf.count());
        for (int i = 0; i < N; i++) {
            StdOut.println("id[" + i + "] = " + uf.id[i]);
        }
    }
}
