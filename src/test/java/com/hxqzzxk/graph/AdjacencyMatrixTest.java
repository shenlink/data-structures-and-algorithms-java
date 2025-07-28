package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试邻接矩阵表示的无向无权图。
 */
public class AdjacencyMatrixTest extends IUnDirectedUnWeightedGraphTest {
    /**
     * 设置测试用例所需的图结构。
     */
    @Override
    public void setUp() {
        graph = new AdjacencyMatrix(pairs);
    }

    /**
     * 测试 toString 方法，验证图的字符串表示是否正确。
     */
    @Test
    public void testToString() {
        Assert.assertEquals("顶点数：7，边数：9\n" +
                "0 1 0 1 0 0 0 \n" +
                "1 0 1 0 0 0 1 \n" +
                "0 1 0 1 0 1 0 \n" +
                "1 0 1 0 1 0 0 \n" +
                "0 0 0 1 0 1 0 \n" +
                "0 0 1 0 1 0 1 \n" +
                "0 1 0 0 0 1 0 \n", graph.toString());
    }
}
