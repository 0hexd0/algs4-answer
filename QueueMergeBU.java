import edu.princeton.cs.algs4.Queue;

/**
 * 2.2.15 自底向上的有序队列归并排序。
 * 用下面的方法编写一个自底向上的归并排序：给定 N 个元素，创建 N 个队列，每个队列包含其中一个元素。
 * 创建一个由这 N 个队列组成的队列，然后不断用练习 2.2.14 中的方法将队列的头两个元素归并，并将结果重新加入到队列结尾，直到队列的队列只剩下一个元素为止。
 */
public class QueueMergeBU {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static Comparable[] sort(Comparable[] a) { // 进行lgN次两两归并
        int N = a.length;
        Queue<Queue<Comparable>> qq = new Queue<Queue<Comparable>>();

        for (int i = 0; i < N; i++) {
            Queue<Comparable> q = new Queue<Comparable>();
            q.enqueue(a[i]);
            qq.enqueue(q);
        }
        while (qq.size() > 1) {
            qq.enqueue(QueueMerge.sort(qq.dequeue(), qq.dequeue()));
        }
        Comparable[] res = new Comparable[N];
        Queue<Comparable> resQ = qq.dequeue();
        for (int i = 0; i < N; i++) {
            res[i] = resQ.dequeue();
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(99);
        SortUtils.printIntegerArr(arr);
        Comparable[] sorted = sort(arr);
        SortUtils.printArr(sorted);
        assert SortUtils.isSorted(sorted, 0, arr.length) : "未全量排序";
    }
}
