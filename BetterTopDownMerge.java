/**
 * 2.2.11 改进
 * 实现 2.2.2 节所述的对归并排序的三项改进：
 * 加快小数组的排序速度，检测数组是否已经有序以及通过在递归中交换参数来避免数组复制
 */
public class BetterTopDownMerge {
    // 在递归中交换参数
    public static void merge(Comparable[] src, Comparable[] dest, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        // 值从辅助数组写回原数组
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dest[k] = src[j++];
            } else if (j > hi) {
                dest[k] = src[i++];
            } else if (less(src[j], src[i])) {
                dest[k] = src[j++];
            } else {
                dest[k] = src[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(a, aux, 0, a.length - 1);
    }

    //  命题0：数组a只有一个元素，视作有序

    //  命题1：如果入参a和aux在lo~hi范围内元素相同，最多顺序不同则sort之后a和aux在lo~hi范围内元素相同，最多顺序不同
    //  归纳法证明：首先假设命题成立
    //    迭代过程：
    //      1) 命中优化场景，对a在lo~hi范围排序，结论成立
    //      2) 切分并分别sort，依据假设，aux和a的性质不发生改变，此后若无merge则将aux复制到a，结论成立；若merge则将aux的数据复制到a上，结论成立
    //    最里层：
    //      1) 命中优化场景，对a在lo~hi范围排序，结论成立
    //      2) hi <= lo，直接return，结论成立

    //  命题2：每次sort完，a数组在lo和hi范围有序
    //  归纳法证明：首先假设命题成立
    //    迭代过程：
    //      1) 命中优化场景，对a在lo~hi范围排序，结论成立
    //      2) 切分并分别sort，依据假设，此后若无merge则将aux复制到a，结论成立；若merge，由假设aux左半边、右半有序，由命题1aux和a的元素相同，由此归并后结论成立
    //    最里层：
    //      1) 命中优化场景，对a在lo~hi范围排序，结论成立
    //      2) hi <= lo，直接return，同时由命题0，结论成立

    // 每次sort完，a数组在lo和hi范围有序（要求a和aux在lo~hi范围内元素相同）
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + 5) {
            // 直接对a排序 => a数组在lo和hi范围有序
            Selection.sort(a, lo, hi + 1);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid); // aux数组在lo和mid范围有序
        sort(aux, a, mid + 1, hi); // aux数组在mid+1和hi范围有序

        if (!less(aux[mid + 1], aux[mid])) {
            // 如果aux在lo和hi范围有序，直接把数据同步到a => a数组在lo和hi范围有序
            System.arraycopy(aux, lo, a, lo, hi - lo + 1);
            return;
        }

        // 如果aux不lo和hi范围有序，归并到a => a数组在lo和hi范围有序
        merge(aux, a, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(99);
        SortUtils.printIntegerArr(arr);
        sort(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
