/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class SortBarGraph {
    private int maxWigth = 1000;
    private int maxHeight = 500;
    private static double padding = 60;
    private static double fontPaddingY = 22;
    private static double barPadding = 5;
    private static double halfBarWidth = 5;
    String title = "XXX排序";
    int cWidth = 0;
    int cHeight = 0;
    int xLen = 0;
    int yLen = 0;

    public SortBarGraph(String title, int xLen, int yLen, Optional<Integer> canvasWidth,
                        Optional<Integer> canvasHeight) {
        StdDraw.setTitle("排序柱状图");
        this.title = title;
        this.xLen = xLen;
        this.yLen = yLen;
        this.cWidth = canvasWidth.orElse(xLen);
        this.cHeight = canvasHeight.orElse(yLen);
        initGraph();
    }

    // 初始化图像（坐标轴、标题）
    void initGraph() {
        double ratio = Math.min(
                Math.min(maxWigth / (double) cWidth, maxHeight / (double) cHeight), 1); // 缩放比例
        int fcWidth = (int) Math.ceil(cWidth * ratio + padding * 2);
        int fcHeight = (int) Math.ceil(cHeight * ratio + padding * 2);
        StdDraw.setCanvasSize(fcWidth, fcHeight);
        double paddingScaleX = padding * xLen / fcWidth;
        double paddingScaleY = padding * yLen / fcHeight;
        StdDraw.setXscale(-paddingScaleX, xLen + paddingScaleX);
        StdDraw.setYscale(-paddingScaleY, yLen + paddingScaleY);
        StdDraw.setPenRadius(.002);
        StdDraw.line(0, 0, xLen, 0);
        StdDraw.line(0, 0, 0, yLen);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        double fontScaleY = fontPaddingY * paddingScaleY / padding;
        StdDraw.text(0, -fontScaleY, "0"); // 原点
        StdDraw.text(xLen, -fontScaleY, xLen + ""); // x轴label
        StdDraw.text(0, yLen + fontScaleY, yLen + ""); // y轴label
        StdDraw.text(xLen / 2.0, yLen, title); // 标题
        StdDraw.setPenRadius(.008);
    }

    // 添加一条柱状图
    void addBar(int n, double value) {
        double halfHeight = value / 2;
        double centerX = barPadding * n + (n - 1) * 2 * halfBarWidth + halfBarWidth;
        double centerY = halfHeight;
        StdDraw.filledRectangle(centerX, centerY, halfBarWidth, halfHeight);
    }

    // 添加一组柱状图
    void show(Double[] vals) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < vals.length; i++) {
            addBar(i + 1, vals[i]);
        }
    }

    public static SortBarGraph createGraph(String title, Double[] vals) {
        double maxX = vals.length * (halfBarWidth * 2 + barPadding) + barPadding;
        double maxY = Collections.max(Arrays.asList(vals)) + 40;
        SortBarGraph graph = new SortBarGraph(title, (int) maxX, (int) maxY,
                                              Optional.empty(),
                                              Optional.empty());
        return graph;
    }

    public static void main(String[] args) {
        // In in = new In("./algs4-data/sortData.txt");
        // Double[] vals = DoubleStream.of(in.readAllDoubles()).boxed().toArray(Double[]::new);
        int N = 400;
        Double[] vals = new Double[N];
        for (int i = 0; i < N; i++)
            vals[i] = StdRandom.uniformDouble(0, 2000);
        SortBarGraph graph = SortBarGraph.createGraph("XX排序", vals);
        graph.show(vals);

        Arrays.sort(vals);
        graph.show(vals);
    }
}
