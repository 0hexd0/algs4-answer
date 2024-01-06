/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 363, 1093
        while (h >= 1) {
            int l = N / h;
            int[] temp = new int[l];
            for (int i = 0; i < h; i++) {
                // 构建递增序列
                for (int n = 0; n < l; n++) {
                    temp[n] = i + n * h;
                }

                // 执行插入排序
                for (int m = 1; m < l; m++) {
                    for (int n = m; n > 0 && less(a[temp[n]], a[temp[n - 1]]); n--) {
                        exch(a, temp[n], temp[n - 1]);
                    }
                }
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
        StdOut.println();
    }

    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readStrings();
        sort(a);
        assert isSorted(a);
        StdOut.println("show a");
        show(a);
    }
}
