/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;

import java.util.Optional;

// 获取次数
interface TimesCalculator {
    double apply(int n);
}


// 绘制摊销图像
public class AmortizedCostGraph {
    private final static double padding = 60;
    private final static double fontPaddingY = 22;
    private int n = 0;
    private int total = 0;

    public AmortizedCostGraph(String title, int xLen, int yLen, Optional<Integer> canvasWidth,
                              Optional<Integer> canvasHeight) {
        int maxWigth = 1000;
        int maxHeight = 500;
        StdDraw.setTitle("均摊成本的图像");
        int cWidth = canvasWidth.orElse(xLen);
        int cHeight = canvasHeight.orElse(yLen);
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

    public void addPoint(int times) {
        total += times;
        n++;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(n, times);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.point(n, (double) total / n);
    }

    public static void drawGraph(int xLen, int yLen, int N, TimesCalculator calculator) {
        double total = 0.0;
        StdDraw.setXscale(-xLen * padding, xLen + xLen * padding);
        StdDraw.setYscale(-yLen * padding, yLen + yLen * padding);

        StdDraw.setPenRadius(.002);
        StdDraw.text(-2, -20, "0");
        StdDraw.text(xLen, -20, xLen + "");
        StdDraw.text(0, yLen, yLen + "");
        StdDraw.line(0, 0, xLen, 0);
        StdDraw.line(0, 0, 0, yLen);
        StdDraw.setPenRadius(.005);

        for (int i = 1; i <= N; i++) {
            double times = calculator.apply(i * i);
            total += times;
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(i, times);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(i, total / N);
        }
    }

    public static void main(String[] args) {
        // 获取次数
        TimesCalculator addition = (int n) -> n * Math.log(n);
        StdDraw.setCanvasSize(700, 700);
        drawGraph(100, 100 * 100, 100, addition);
    }
}
