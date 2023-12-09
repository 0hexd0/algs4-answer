import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.Color;
import java.util.ArrayList;

class Point {
    int n;
    double time;

    public Point(int n, double time) {
        this.n = n;
        this.time = time;
    }
}

public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private DoublingTest() {
    }

    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        int ignore = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void redraw(ArrayList<Point> points) {
        StdDraw.clear();
        Point lastP = points.get(points.size() - 1);
        double maxX = Math.log(lastP.n);
        double maxY = Math.log(lastP.time);
        double offsetYTop = maxY * 0.1;
        double offsetYBottom = maxY * 0.1;
        double offsetXLeft = maxX * 0.1;
        double offsetXRight = maxX * 0.1;
        StdDraw.setXscale(-offsetXLeft, maxX + offsetXRight);
        StdDraw.setYscale(-offsetYBottom, maxY + offsetYTop);
        StdDraw.setPenRadius(.01);
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            // StdDraw.setPenColor(Color.red);
            // StdDraw.point(p.n, p.time);
            StdDraw.setPenColor(Color.green);
            StdDraw.point(Math.log(p.n), Math.log(p.time));
        }
    }


    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<Point>();
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            points.add(new Point(n, time));
            redraw(points);
            StdOut.printf("%7d %7.1f\n", n, time);
        }
    }
}


