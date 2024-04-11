public class MergeBU {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) { // 进行lgN次两两归并
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) // sz子数组大小
            for (int lo = 0; lo < N - sz; lo += sz + sz) // lo:子数组索引
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1), aux);
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
        Integer[] arr = SortUtils.getRandomIntArr(99);
        SortUtils.printIntegerArr(arr);
        sort(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
