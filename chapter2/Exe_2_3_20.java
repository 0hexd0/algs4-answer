/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

package chapter2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exe_2_3_20 {
    public static class SortInfo {
        public SortInfo(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        int lo;
        int hi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        StdRandom.shuffle(arr);
        sort(arr);
        for (int i = 0; i < n; i++) {
            arr[i] = i;
            StdOut.printf("%d ", arr[i]);
        }
    }

    public static void sort(Comparable a[]) {
        StdRandom.shuffle(a);
        Stack<SortInfo> s = new Stack<>();
        s.push(new SortInfo(0, a.length - 1));
        sort(a, s);
    }

    public static void sort(Comparable a[], Stack<SortInfo> s) {
        while (!s.isEmpty()) {
            SortInfo info = s.pop();

            if (info.lo < info.hi) {
                int j = partition(a, info.lo, info.hi);
                s.push(new SortInfo(info.lo, j - 1));
                s.push(new SortInfo(j + 1, info.hi));
            }
        }
    }

    public static int partition(Comparable a[], int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (i < j) {
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

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
