/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Whitelist {
    public static void main(String[] args) {
        int[] w = new In(args[0]).readAllInts();
        StaticSETofInts set = new StaticSETofInts(w);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            StdOut.println(set.howMany(key));
        }
    }
}
