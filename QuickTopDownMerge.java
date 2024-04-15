public class QuickTopDownMerge {
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
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        if (less(a[mid + 1], a[mid])) {
            merge(a, lo, mid, hi, aux);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(512);
        SortUtils.printArr(arr);
        sort(arr);
        SortUtils.printArr(arr);
    }
}
