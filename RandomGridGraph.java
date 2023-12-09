/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

public class RandomGridGraph {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomGrid.Connection[] cons = RandomGrid.generate(N);
        StdDraw.setTitle("随机网格连接");
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenRadius(.005);
        for (RandomGrid.Connection con : cons) {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.point(con.p, con.q);
        }
    }
}
