/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class ErdosRenyi {
    public static int count(UF uf, int n) {
        int link = 0;
        while (uf.count() > 1) {
            int r1 = StdRandom.uniformInt(0, n);
            int r2 = StdRandom.uniformInt(0, n);
            if (!uf.connected(r1, r2)) {
                uf.union(r1, r2);
                link++;
            }
        }
        return link;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        QuickFindUF uf = new QuickFindUF(N);
        StdOut.printf("link nums: " + count(uf, N));
    }
}
