/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SameNums {
    public static void main(String[] args) {
        In in1 = new In(args[0]);
        int[] sortedlist1 = in1.readAllInts();
        In in2 = new In(args[1]);
        int[] sortedlist2 = in2.readAllInts();

        int i = 0;
        int j = 0;
        while (i < sortedlist1.length && j < sortedlist2.length) {
            if (sortedlist1[i] == sortedlist2[j]) {
                StdOut.println(sortedlist1[i]);
                i++;
                j++;
            }
            else if (sortedlist1[i] > sortedlist2[j]) {
                j++;
            }
            else {
                i++;
            }
        }
    }
}
