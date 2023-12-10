/* *****************************************************************************
 *  Name:              He XuDong
 **************************************************************************** */


import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ErdosRenyiUFGraph {
    public static List<Integer> count(int n) {
        List<Integer> arrayList = new ArrayList<>();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (uf.count() > 1) {
            int i = StdRandom.uniformInt(0, n);
            int j = StdRandom.uniformInt(0, n);
            arrayList.add(uf.union(i, j));
        }
        return arrayList;
    }

    public static Integer findMaxValue(List<Integer> list) {
        // 检查列表是否为空
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }

        // 初始化最大值为列表的第一个元素
        Integer max = list.get(0);

        // 遍历列表，比较每个元素与当前最大值
        for (Integer num : list) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        List<Integer> list = count(N);
        int maxCost = findMaxValue(list);
        AmortizedCostGraph graph = new AmortizedCostGraph("weighted-quick-union算法",
                                                          list.size(),
                                                          maxCost,
                                                          Optional.empty(),
                                                          Optional.empty());

        for (Integer num : list) {
            graph.addPoint(num);
        }
    }
}
