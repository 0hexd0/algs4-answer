/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class RandomGridGraph {
    // uf index : grid index
    private Map<Integer, Integer> map = new HashMap<>();

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Point getPoint(int idx, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i * N + j == idx) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public static void draw(WeightedQuickUnionUF uf, int N) {
        StdDraw.setTitle("随机网格连接");
        StdDraw.setXscale(-1, 25);
        StdDraw.setYscale(-1, 25);
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                StdDraw.point(i, j);
            }
        }

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(.005);

        for (int i = 0; i < uf.count(); i++) {

            for (int j = 0; j < N; j++) {
                int idx = i * N + j;
                int bottomIdx = (i + 1) * N + j;
                if (i + 1 < N && uf.connected(idx, bottomIdx)) {
                    StdDraw.line(i, j, i + 1, j);
                }
                int rightIdx = i * N + j + 1;
                if (j + 1 < N && uf.connected(idx, rightIdx)) {
                    StdDraw.line(i, j, i, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        In in = new In("./algs4-data/mediumUF.txt");
        String fl = in.readLine();
        int N = (int) Math.sqrt(Integer.parseInt(fl));
        // int N = Integer.parseInt(args[0]);
        // RandomGrid.Connection[] cons = RandomGrid.generate(N * N);


        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N * N);
        // for (RandomGrid.Connection con : cons) {
        //     uf.union(con.p, con.q);
        // }

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            int times = uf.union(p, q);
            // graph.addPoint(times);
            StdOut.println("times: " + times);

            StdOut.println(p + " " + q);
            StdOut.println(uf.count() + " components");
        }

        draw(uf, N);
    }
}
