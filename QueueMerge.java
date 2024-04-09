/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.14  归并有序的队列。 编写一个静态方法, 将两个有序的队列作为参数, 返回一个归并后的有序队列。
 */

public class QueueMerge {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static Queue<Comparable> sort(Queue<Comparable> q1, Queue<Comparable> q2) {
        Queue aux = new Queue<>();
        int qLen = q1.size() + q2.size();
        for (int i = 0; i < qLen; i++) {
            if (q1.isEmpty()) {
                aux.enqueue(q2.dequeue());
            }
            else if (q2.isEmpty()) {
                aux.enqueue(q1.dequeue());
            }
            else if (less(q1.peek(), q2.peek())) {
                aux.enqueue(q1.dequeue());
            }
            else {
                aux.enqueue(q2.dequeue());
            }
        }
        return aux;
    }

    public static void main(String[] args) {
        Queue<Comparable> queue1 = new Queue<Comparable>();
        for (int i = 10; i < 100; i++) {
            queue1.enqueue(Integer.valueOf(i));
        }
        Queue<Comparable> queue2 = new Queue<Comparable>();
        for (int i = 0; i < 20; i++) {
            queue2.enqueue(Integer.valueOf(i));
        }
        Queue<Comparable> q = sort(queue1, queue2);
        while (!q.isEmpty()) {
            StdOut.printf("%d\t", q.dequeue());
        }
        assert q.size() == queue1.size() + queue2.size();
    }
}
