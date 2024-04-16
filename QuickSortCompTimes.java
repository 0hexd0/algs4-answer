import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.3.6 编写一段代码来计算 CN 的准确值
 * 在 N=100、 1000 和 10 000 的情况下比较准确值和估计值2NlnN 的差距。
 */
public class QuickSortCompTimes {
    private static int times = 0;

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        times++;
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        StdOut.printf("real times\ttheory times\n");
        for (int N = 100; N <= 10000; N *= 10) {
            times = 0;
            Integer[] arr = SortUtils.getRandomIntArr(N);
            sort(arr);
            StdOut.printf("%d\t%d\n", times, (int) (2 * N * Math.log(N)));
        }
    }
}
