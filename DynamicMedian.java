import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

// 动态中位数
public class DynamicMedian<Key extends Comparable<Key>> {
    MaxPQ<Key> left_pq; // 中位数存放于 left_pq中最大值
    MinPQ<Key> right_pq;


    public DynamicMedian(int maxN) {
        this.left_pq = new MaxPQ<>(maxN);
        this.right_pq = new MinPQ<>(maxN);
    }

    // 查找中位数 时间复杂度1
    Key findMedian() {
        return left_pq.max();
    }

    // 删除中位数 时间复杂度lgN
    void deleteMedian() {
        left_pq.delMax();
        reBalance();
    }

    // 插入元素 时间复杂度lgN
    public void insert(Key key) {
        if (left_pq.isEmpty()) {
            left_pq.insert(key);
        } else {
            Key oldMid = findMedian();
            if (key.compareTo(oldMid) <= 0) {
                left_pq.insert(key);
            } else {
                right_pq.insert(key);
            }
        }
        reBalance();
    }

    // 重新平衡左右 时间复杂度lgN
    void reBalance() {
        // 数量平衡保证左-右=0|1
        if (left_pq.size() - right_pq.size() > 1) {
            // 左边必右边多超过一个，移动一个到右边
            right_pq.insert(left_pq.delMax());
        } else if (left_pq.size() < right_pq.size()) {
            // 右边比左边多，移动一个到左边
            left_pq.insert(right_pq.delMin());
        }
    }


    public static void main(String[] args) {
        DynamicMedian dm = new DynamicMedian(10);
        dm.insert("A");
        dm.insert("B");
        dm.insert("C");
        StdOut.println("find median: " + dm.findMedian());
        dm.deleteMedian();
        dm.deleteMedian();
        StdOut.println("find median: " + dm.findMedian());
        dm.insert("D");
        dm.insert("A");
        StdOut.println("find median: " + dm.findMedian());
        dm.insert("B");
        StdOut.println("find median: " + dm.findMedian());
        dm.insert("E");
        StdOut.println("find median: " + dm.findMedian());
    }
}
