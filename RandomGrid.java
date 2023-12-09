/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;

public class RandomGrid {
    public static class Connection {
        int p;
        int q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }

        public String toString() {
            return p + " -> " + q;
        }
    }

    public static Connection[] generate(int n) {
        RandomBag<Connection> consBag = new RandomBag<>();
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < n; q++) {
                consBag.add(new Connection(p, q));
            }
        }

        Connection[] arr = new Connection[consBag.size()];
        int i = 0;

        for (Connection con : consBag) {
            arr[i++] = con;
        }

        return arr;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Connection[] cons = generate(N);
        StdOut.println("cons size is " + cons.length);
        for (Connection con : cons) {
            StdOut.println(con);
        }
    }
}
