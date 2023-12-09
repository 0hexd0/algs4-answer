/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StaticSETofInts {
    private int a[];

    public StaticSETofInts(int[] keys) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < keys.length; i++) {
            s.add(keys[i]);
        }
        a = new int[s.size()];
        int i = 0;
        
        for (int key : s) {
            a[i++] = key;
        }

        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public int findFirstIdx(int key) {
        int lo = 0;
        int hi = a.length - 1;
        int firstIdx = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                firstIdx = mid;
                hi = mid - 1;
            }
        }
        return firstIdx;
    }

    public int findLastIdx(int key) {
        int lo = 0;
        int hi = a.length - 1;
        int lastIdx = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                lastIdx = mid;
                lo = mid + 1;
            }
        }
        return lastIdx;
    }

    public int howMany(int key) {
        int firstIdx = findFirstIdx(key);
        if (firstIdx == -1) {
            return 0;
        }
        int lastIdx = findLastIdx(key);
        return lastIdx - firstIdx + 1;
    }
}
