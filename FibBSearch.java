/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FibBSearch {
    public static int fib(int k) {
        if (k <= 1) {
            return 1;
        }
        return fib(k - 1) + fib(k - 2);
    }

    public static int getFib(int N) {
        int i = 0;
        while (fib(i) < N) {
            i++;
        }
        return i;
    }

    public static int find(int[] arr, int key) {
        int i = 0;
        int k = getFib(arr.length);
        int Fk = fib(k);
        int Fk_1 = fib(k - 1);
        if (arr.length > 0 && arr[0] == key) {
            return 0;
        }
        while (Fk_1 >= 1) {
            int Fk_2 = Fk - Fk_1;
            StdOut.println("Fk : " + Fk);
            StdOut.println("Fk_1 : " + Fk_1);
            StdOut.println("Fk_2 + i: " + Fk_2 + " + " + i);
            // 需要考虑计算的出的索引越界问题
            // 越界当作arr[Fk_2 + i] > key处理
            if (Fk_2 + i >= arr.length || arr[Fk_2 + i] > key) {
                Fk = Fk_2;
                Fk_1 = Fk_1 - Fk_2; // 最后一次 Fk=1,Fk_1=0处理成Fk=1,Fk_1=1
            }
            else if (arr[Fk_2 + i] < key) {
                i += Fk_2;
                Fk = Fk_1;
                Fk_1 = Fk_2;
            }
            else {
                return Fk_2 + i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // read the integers from a file
        In in = new In(args[0]);
        int[] allowlist = in.readAllInts();
        // sort the array
        Arrays.sort(allowlist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            StdOut.println(find(allowlist, key));
        }
    }
}
