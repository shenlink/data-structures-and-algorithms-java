package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 图的 BFS 测试类
 */
public class GraphBFSTest {
    /**
     * 测试用的图的边集合。
     * 包含一个连通分量
     */
    private int[][] pairs = {
            {0, 1}, {0, 2}, {1, 3}, {1, 4},
            {2, 3}, {2, 6}, {5, 6}
    };

    /**
     * 被测试的图实例
     */
    private IGraph graph = new UnDirectedUnWeightedGraph(pairs);

    /**
     * BFS遍历器实例
     */
    private GraphBFS bfs = new GraphBFS(graph);

    /**
     * 测试BFS遍历顺序。
     * 验证遍历顺序是否符合预期
     * 预期结果：
     * - 遍历顺序应该是[0, 1, 2, 3, 4, 6, 5]
     */
    @Test
    public void testOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        bfs.order().forEach(list::add);
        Assert.assertEquals(list.size(), graph.getVertexes());
        Assert.assertEquals("[0, 1, 2, 3, 4, 6, 5]", list.toString());
    }
}
