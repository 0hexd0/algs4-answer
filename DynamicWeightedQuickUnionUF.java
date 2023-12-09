import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class DynamicWeightedQuickUnionUF {
    private Map<Integer, Node> nodes;
    int idCounter;
    private int counter;

    private class Node {
        int id;
        Node root;
        int sz;

        public Node(int id) {
            this.id = id;
            this.root = this;
            this.sz = 1;
        }
    }

    public DynamicWeightedQuickUnionUF() {
        nodes = new HashMap<>();
        counter = 0;
    }

    public int count() {
        return counter;
    }

    private Node findNode(int id) {
        for (int i = idCounter; i <= id; i++) {
            newSite();
        }
        return nodes.get(id);
    }

    public Node findRoot(int id) {
        Node node = findNode(id);

        while (node.root != node) {
            node = node.root;
        }

        return node;
    }

    public int newSite() {
        nodes.put(idCounter, new Node(idCounter++));
        counter++;
        return idCounter - 1;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        Node pRoot = findRoot(p);
        Node qRoot = findRoot(q);

        if (pRoot == qRoot) {
            return;
        }

        if (pRoot.sz < qRoot.sz) {
            pRoot.root = qRoot;
            qRoot.sz += pRoot.sz;
        }
        else {
            qRoot.root = pRoot;
            pRoot.sz += qRoot.sz;
        }

        counter--;
    }

    public static void main(String[] args) {
        In in = new In("./algs4-data/largeUf.txt");
        in.readLine();

        DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF();

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();


            uf.union(p, q);
            StdOut.println(p + " " + q);
            StdOut.println(uf.count() + " components");
        }
    }
}
