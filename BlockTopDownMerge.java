/**
 * 2.2.12 次线性的额外空间
 * 用大小 M 将数组分为 N/M 块（简单起见，设 M 是 N 的约数）。
 * 实现一个归并方法，使之所需的额外空间减少到 max(M, N/M)：
 * (i) 可以先将一个块看做一个元素，将块的第一个元素作为块的主键，用选择排序将块排序；
 * (ii) 遍历数组，将第一块和第二块归并，完成后将第二块和第三块归并
 */
public class BlockTopDownMerge {
    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = mid - 1, j = hi - mid - 1;

        // 后半部分值复制到辅助数组中
        for (int k = mid; k < hi; k++) {
            aux[k - mid] = a[k];
        }

        for (int k = hi - 1; k >= lo; k--) {
            if (i < lo) {
                a[k] = aux[j--];
            } else if (j < 0) {
                a[k] = a[i--];
            } else if (less(a[i], aux[j])) {
                a[k] = aux[j--];
            } else {
                a[k] = a[i--];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void sort(Comparable[] a) {
        int blockSize = 10; // 每块几个元素
        int blockNum = (int) Math.ceil(a.length * 1.0 / blockSize);
        // 分块&块内排序
        for (int i = 0; i < a.length; i += blockSize) {
            int hi = Math.min(i + blockSize, a.length);
            Selection.sort(a, i, hi);
        }
        Comparable[] aux = new Comparable[blockSize]; // 辅助数组
        // merge块
        for (int i = 0; i < blockNum - 1; i++) {
            int hi = Math.min((i + 2) * blockSize, a.length);
            merge(a, 0, (i + 1) * blockSize, hi, aux);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(501);
        SortUtils.printIntegerArr(arr, 10);
        sort(arr);
        SortUtils.printIntegerArr(arr, 10);
        assert SortUtils.isSorted(arr, 0, arr.length);
    }
}
