import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

class SubArrPos {
    int lo;
    int hi;

    SubArrPos(int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
    }

    int len() {
        return hi - lo + 1;
    }
}

/**
 * 2.3.20 非递归的快速排序。
 * 实现一个非递归的快速排序，使用一个循环来将弹出栈的子数组切分并将结果子数组重新压入栈。
 * 注意：先将较小的子数组压入栈，这样就可以保证栈最多只会有lgN个元素。
 */
public class NonRecursiveQuick {

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        Stack<SubArrPos> s = new Stack<>();
        s.push(new SubArrPos(lo, hi));
        while (!s.isEmpty()) {
            SubArrPos l = s.pop();
            int j = partition(a, l.lo, l.hi);

            SubArrPos pos1 = new SubArrPos(l.lo, j - 1); // < j
            SubArrPos pos2 = new SubArrPos(j + 1, l.hi); // >= j

            if (pos1.len() < pos2.len()) {
                // 先将较小的子数组压入栈，栈最大长度会小些
                if (pos1.len() > 1) {
                    s.push(pos1);
                }

                if (pos2.len() > 1) {
                    s.push(pos2);
                }
            } else {
                if (pos2.len() > 1) {
                    s.push(pos2);
                }

                if (pos1.len() > 1) {
                    s.push(pos1);
                }
            }

        }
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
        Integer[] arr = SortUtils.getRandomIntArr(1000);
        SortUtils.printIntegerArr(arr);
        sort(arr);
        SortUtils.printIntegerArr(arr);
        assert SortUtils.isSorted(arr, 0, arr.length) : "未全量排序";
    }
}
