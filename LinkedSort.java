public class LinkedSort {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(LinkedList<Comparable> list) {
        Node<Comparable> h = list.head;
        Node<Comparable> hT = h; // tail
        while (hT.next != null && !less(hT.next.data, hT.data)) {
            hT = hT.next;
        }

        Node<Comparable> hR = hT.next;

        if (hT.next != null) {
            hT.next = null;
        }

        while (hR != null) {
            Node<Comparable> hRT = hR;
            while (hRT.next != null && !less(hRT.next.data, hRT.data)) {
                hRT = hRT.next;
            }
            Node<Comparable> nextRH = hRT.next;
            hRT.next = null;

            h = merge(h, hR);
            hR = nextRH;
        }

        list.head = h;
    }

    public static Node<Comparable> merge(Node<Comparable> h1, Node<Comparable> h2) {
        Node<Comparable> h;
        if (less(h1.data, h2.data)) {
            h = h1;
            h1 = h1.next;
        } else {
            h = h2;
            h2 = h2.next;
        }
        Node<Comparable> hT = h; // head tail
        while (h1 != null || h2 != null) {
            if (h1 == null) {
                hT.next = h2;
                h2 = h2.next;
            } else if (h2 == null) {
                hT.next = h1;
                h1 = h1.next;
            } else if (less(h1.data, h2.data)) {
                hT.next = h1;
                h1 = h1.next;
            } else {
                hT.next = h2;
                h2 = h2.next;
            }
            hT = hT.next;
            hT.next = null;
        }
        return h;
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtils.getRandomIntArr(200);
        LinkedList<Comparable> list = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            list.append(arr[i]);
        }
        list.display();
        sort(list);
        list.display();
    }
}
