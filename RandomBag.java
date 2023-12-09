/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item> {

    private Item[] items = (Item[]) new Object[1];
    private int usedSize = 0;

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < usedSize; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    public boolean isEmpty() {
        return usedSize == 0;
    }

    public int size() {
        return usedSize;
    }

    public void add(Item item) {
        if (usedSize == items.length) {
            resize(2 * items.length);
        }
        items[usedSize] = item;
        usedSize += 1;
    }

    private class ListIterator implements Iterator<Item> {
        private int current = 0;

        public boolean hasNext() {
            return current < usedSize;
        }

        public void remove() {
            //
        }

        public Item next() {
            return items[current++];
        }
    }

    public Iterator<Item> iterator() {
        StdRandom.shuffle(items, 0, usedSize);
        return new ListIterator();
    }

    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        StdOut.println("bag size: " + bag.size());
        bag.add(9);
        bag.add(8);
        bag.add(7);
        bag.add(6);
        StdOut.println("bag size: " + bag.size());
        for (int i = 0; i < 10; i++) {
            StdOut.print("bag items: ");
            for (Integer item : bag) {
                StdOut.print(item + " ");
            }
            StdOut.println();
        }
    }
}
