package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 测试使用 BFS 实现的无向图中所有顶点对之间的路径查找类。
 */
public class AllPairsShortestPathBFSTest {
    /**
     * 图中的边对数组。
     */
    private int[][] pairs = new int[][]{
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}, {5, 6}
    };

    /**
     * 图实例。
     */
    private IUnDirectedGraph graph = new UnDirectedGraph(pairs);

    /**
     * 使用 BFS 的所有顶点对路径查找实例。
     */
    private AllPairsShortestPathBFS AllPairsPathBFS = new AllPairsShortestPathBFS(graph);

    /**
     * 测试 isConnected 方法，确保指定的顶点对之间是连通的。
     */
    @Test
    public void testIsConnected() {
        for (int[] pair : pairs) {
            Assert.assertTrue(AllPairsPathBFS.isConnected(pair[0], pair[1]));
            Assert.assertTrue(AllPairsPathBFS.isConnected(pair[1], pair[0]));
        }
    }

    /**
     * 测试 path 方法，确保从一个顶点到另一个顶点的路径正确。
     */
    @Test
    public void testPath() {
        Iterable<Integer> path = AllPairsPathBFS.path(0, 3);
        ArrayList<Integer> list = new ArrayList<>();
        path.forEach(list::add);
        Assert.assertArrayEquals(new Integer[]{0, 1, 3}, list.toArray());
    }
}
