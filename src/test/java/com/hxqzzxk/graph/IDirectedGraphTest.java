package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * 有向图测试类
 */
public class IDirectedGraphTest extends AbstractGraphTest {
    /**
     * 设置有向图实例
     */
    @Override
    public void setUp() {
        graph = new DirectedGraph(7, 9, pairs);
    }

    /**
     * 测试两个顶点之间是否右边
     */
    @Test
    public void testHasEdge() {
        IDirectedGraph directedGraph = (IDirectedGraph) graph;
        for (int[] pair : pairs) {
            Assert.assertTrue(directedGraph.hasEdge(pair[0], pair[1]));
        }
    }

    /**
     * 测试获取顶点的入度方法
     */
    @Test
    public void testGetInDegree() {
        IDirectedGraph directedGraph = (IDirectedGraph) graph;
        for (int i = 0; i < directedGraph.getVertexes(); i++) {
            int inDegree = 0;
            for (int j = 0; j < directedGraph.getVertexes(); j++) {
                if (directedGraph.hasEdge(j, i)) {
                    inDegree++;
                }
            }
            Assert.assertEquals(directedGraph.getInDegree(i), inDegree);
        }
    }

    /**
     * 测试获取顶点的出度方法
     */
    @Test
    public void testGetOutDegree() {
        IDirectedGraph directedGraph = (IDirectedGraph) graph;
        for (int i = 0; i < directedGraph.getVertexes(); i++) {
            int outDegree = 0;
            for (int j = 0; j < directedGraph.getVertexes(); j++) {
                if (directedGraph.hasEdge(i, j)) {
                    outDegree++;
                }
            }
            Assert.assertEquals(directedGraph.getOutDegree(i), outDegree);
        }
    }
}
