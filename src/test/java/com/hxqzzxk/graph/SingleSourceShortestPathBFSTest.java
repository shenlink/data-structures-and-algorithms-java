package com.hxqzzxk.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 测试使用 BFS 实现的无向图单源最短路径算法
 */
public class SingleSourceShortestPathBFSTest {
    /**
     * 图中的边对，用于构造测试用的无向图。
     */
    private int[][] pairs = {
            {0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 6}, {5, 6}
    };
    /**
     * 测试使用的无向无权图实例。
     */
    private IUnDirectedUnWeightedGraph graph =
            new UnDirectedUnWeightedGraph(pairs);
    /**
     * 单源最短路径算法的BFS实现实例，起点为顶点0。
     */
    private SingleSourceShortestPathBFS SingleSourcePathBFS =
            new SingleSourceShortestPathBFS(graph, 0);

    /**
     * 测试所有顶点是否与起始点连通。
     */
    @Test
    public void testIsConnected() {
        Assert.assertTrue(SingleSourcePathBFS.isConnected(1));
        Assert.assertTrue(SingleSourcePathBFS.isConnected(2));
        Assert.assertTrue(SingleSourcePathBFS.isConnected(3));
        Assert.assertTrue(SingleSourcePathBFS.isConnected(4));
        Assert.assertTrue(SingleSourcePathBFS.isConnected(5));
        Assert.assertTrue(SingleSourcePathBFS.isConnected(6));
    }

    /**
     * 测试从起点到目标顶点的路径是否正确。
     */
    @Test
    public void testPath() {
        ArrayList<Integer> list = new ArrayList<>();
        SingleSourcePathBFS.path(6).forEach(list::add);
        Assert.assertEquals("[0, 2, 6]", list.toString());
    }
}
