/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.18  打乱链表。
 * 实现一个分治算法,使用线性对数级别的时间和对数级别的额外空间随机打乱一条链表。
 */
public class ShuffleLinkList {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static Node<Comparable> findMid(Node<Comparable> list) {
        Node<Comparable> fP = list;
        Node<Comparable> sP = list;
        if (fP == null) {
            return null;
        }
        while (fP.next != null && fP.next.next != null) {
            fP = fP.next.next;
            sP = sP.next;
        }
        return sP;
    }

    public static Node<Comparable> merge(Node<Comparable> h1, Node<Comparable> h2) {
        if (h1 == null) {
            return h2;
        }
        else if (h2 == null) {
            return h1;
        }
        Node<Comparable> h;

        int randomVal = StdRandom.uniformInt(0, 2);
        if (randomVal == 1) {
            h = h1;
            h1 = h1.next;
        }
        else {
            h = h2;
            h2 = h2.next;
        }

        Node<Comparable> ht = h;

        while (h1 != null || h2 != null) {
            if (h1 == null) {
                ht.next = h2;
                h2 = h2.next;
                ht = ht.next;
                continue;
            }
            if (h2 == null) {
                ht.next = h1;
                h1 = h1.next;
                ht = ht.next;
                continue;
            }
            randomVal = StdRandom.uniformInt(0, 2);
            if (randomVal == 1) {
                ht.next = h1;
                h1 = h1.next;
                ht = ht.next;
            }
            else {
                ht.next = h2;
                h2 = h2.next;
                ht = ht.next;
            }
        }

        return h;
    }

    public static void shuffle(LinkedList<Comparable> list) {
        Node<Comparable> nHead = shuffle(list.head);
        list.head = nHead;
    }


    public static Node<Comparable> shuffle(Node<Comparable> list) {
        if (list == null || list.next == null) {
            return list;
        }
        Node<Comparable> mid = findMid(list); // 查找中间节点
        // 左右分断两链
        Node<Comparable> nList = mid.next;
        mid.next = null;
        Node<Comparable> lList = shuffle(list);
        Node<Comparable> rList = shuffle(nList);
        return merge(lList, rList);
    }

    public static void main(String[] args) {
        LinkedList<Comparable> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.append(Integer.valueOf(i));
        }

        list.display();
        shuffle(list);
        list.display();
    }
}
