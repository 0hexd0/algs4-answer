/**
 * 2.2.16 自然的归并排序。
 * 编写一个自底向上的归并排序，当需要将两个子数组排序时能够利用数组中已经有序的部分。
 * 首先找到一个有序的子数组（移动指针直到当前元素比上一个元素小为止），然后再找出另一个并将它们归并。
 * 根据数组大小和数组中递增子数组的最大长度分析算法的运行时间。
 */
public class NaturalMergeBU {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) { // 进行lgN次两两归并
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        int lo = 0, mid = 1, hi = 1;

        while (mid + 1 < N) {
            while (!less(a[mid + 1], a[mid])) {
                mid++;
            }
            if (mid == N - 1) {
                return;
            }
            hi = mid + 1;
            while (hi + 1 < N && !less(a[hi + 1], a[hi])) {
                hi++;
            }
            merge(a, lo, mid, hi, aux);
            mid = hi;
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = mid + 1;

        // 值复制到辅助数组中
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(100);
        SortUtils.printIntegerArr(arr);
        sort(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
