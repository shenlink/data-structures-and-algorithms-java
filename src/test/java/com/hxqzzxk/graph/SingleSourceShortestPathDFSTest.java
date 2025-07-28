package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 测试使用 DFS 实现的无向图单源最短路径算法
 */
public class SingleSourceShortestPathDFSTest {
    /**
     * 图中的边对，用于构造测试用的无向图。
     */
    private int[][] pairs = new int[][]{
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}
    };
    /**
     * 测试使用的无向图实例。
     */
    private IUnDirectedUnWeightedGraph graph =
            new UnDirectedUnWeightedGraph(7, 6, pairs);
    /**
     * 单源路径算法的DFS实现实例，起点为顶点0。
     */
    private SingleSourceShortestPathDFS SingleSourcePathDFS =
            new SingleSourceShortestPathDFS(graph, 0);

    /**
     * 测试所有顶点是否与起始点连通。
     */
    @Test
    public void testIsConnected() {
        Assert.assertTrue(SingleSourcePathDFS.isConnected(1));
        Assert.assertTrue(SingleSourcePathDFS.isConnected(2));
        Assert.assertTrue(SingleSourcePathDFS.isConnected(3));
        Assert.assertTrue(SingleSourcePathDFS.isConnected(4));
        Assert.assertTrue(SingleSourcePathDFS.isConnected(6));
        Assert.assertFalse(SingleSourcePathDFS.isConnected(5));
    }

    /**
     * 测试从起点到目标顶点的路径是否正确。
     */
    @Test
    public void testPath() {
        Iterable<Integer> path = SingleSourcePathDFS.path(3);
        ArrayList<Integer> list = new ArrayList<>();
        path.forEach(list::add);
        Assert.assertArrayEquals(new Integer[]{0, 1, 3}, list.toArray());
    }
}
