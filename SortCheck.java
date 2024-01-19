/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryInsertion;
import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.InsertionX;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.MergeBU;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.QuickX;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SortCheck {
    public static boolean check(String alg, Comparable[] a) {
        Comparable[] aCopy = Arrays.copyOf(a, a.length);
        Arrays.sort(aCopy);
        if (alg.equals("Insertion")) Insertion.sort(a);
        else if (alg.equals("InsertionX")) InsertionX.sort(a);
        else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("Selection")) Selection.sort(a);
            // else if (alg.equals("Bubble")) Bubble.sort(a);
        else if (alg.equals("Shell")) Shell.sort(a);
        else if (alg.equals("Merge")) Merge.sort(a);
        else if (alg.equals("MergeX")) MergeX.sort(a);
        else if (alg.equals("MergeBU")) MergeBU.sort(a);
        else if (alg.equals("Quick")) Quick.sort(a);
        else if (alg.equals("Quick3way")) Quick3way.sort(a);
        else if (alg.equals("QuickX")) QuickX.sort(a);
        else if (alg.equals("Heap")) Heap.sort(a);
        else if (alg.equals("System")) Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        // contain the same elements in the same order
        return Arrays.equals(a, aCopy);
    }

    public static Double[] getDoubleArray(int n) {
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble(0.0, 1.0);
        return a;
    }

    public static void main(String[] args) {
        String alg = args[0];
        int n = Integer.parseInt(args[1]);
        boolean res = check(alg, getDoubleArray(n));

        StdOut.printf("For %d random Doubles\n %s Sort check res is %s", n, alg, res);
    }
}
