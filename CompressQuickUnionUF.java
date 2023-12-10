/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CompressQuickUnionUF {
    private int[] id; // 分量id
    private int count; // 分量数量

    public CompressQuickUnionUF(int N) {
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
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        id[pRoot] = qRoot;

        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        StdOut.println("N " + N);
        CompressQuickUnionUF uf = new CompressQuickUnionUF(N);

        // while (!StdIn.isEmpty()) {
        //     String str = StdIn.readString();
        //     if (str.compareTo("-") == 0) {
        //         break;
        //     }
        //     int p = Integer.parseInt(str);
        //     int q = StdIn.readInt();
        //     if (uf.connected(p, q)) continue; // 已联通，忽略
        //     uf.union(p, q);
        //     StdOut.println(p + " " + q);
        //     StdOut.println(uf.count() + " components");
        // }

        // Perform union operations to create a path of length 4
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);
        uf.union(4, 5);

        // Output the components and connections
        StdOut.println("Components: " + uf.count());
        for (int i = 0; i < N; i++) {
            StdOut.println("id[" + i + "] = " + uf.id[i]);
        }
    }
}
