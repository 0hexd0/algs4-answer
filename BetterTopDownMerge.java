import edu.princeton.cs.algs4.Insertion;

public class BetterTopDownMerge {
    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = hi;

        // 前半部分值升序复制到辅助数组中
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }

        // 后半部分值降序复制到辅助数组中
        for (int k = 0; k <= hi - mid - 1; k++) {
            aux[mid + 1 + k] = a[hi - k];
        }

        for (int k = lo; k <= hi; k++) {
            if (less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length]; // 辅助数组
        sort(a, 0, a.length - 1, aux);
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (hi <= lo) return;
        if (hi - lo < 15) {
            // 加快小数组排序
            Insertion.sort(a, lo, hi);
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        if (less(a[mid + 1], a[mid])) {
            // 检测是否有序
            merge(a, lo, mid, hi, aux);
        }
    }


    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(99);
        sort(arr);
        assert SortUtils.isSorted(arr, 0, arr.length);
    }
}
