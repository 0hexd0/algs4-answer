/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 双调查找，要求数组先升后降
public class BitonicSearch {
    // 从升序数组查找
    public static int findInAsc(int[] a, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == key) {
                return mid;
            }
            else if (a[mid] > key) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // 从降序数组查找
    public static int findInDesc(int[] a, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == key) {
                return mid;
            }
            else if (a[mid] > key) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static int find(int[] a, int key) {
        int maxIdx = findMax(a);
        int res = findInAsc(a, key, 0, maxIdx);
        if (res == -1) {
            res = findInDesc(a, key, maxIdx + 1, a.length - 1);
        }
        return res;
    }

    // 查找最大索引
    public static int findMax(int[] a) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid > 0 && a[mid - 1] > a[mid]) {
                end = mid - 1;
            }
            else if (mid < a.length - 1 && a[mid + 1] > a[mid]) {
                start = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        // 1. 假设数组元素先递增后递减
        int[] a = in.readAllInts();
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int res = find(a, key);
            StdOut.println(res);
        }
    }
}
