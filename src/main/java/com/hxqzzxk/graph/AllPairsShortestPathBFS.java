package com.hxqzzxk.graph;

/**
 * 所有顶点对之间的最短路径查找，使用 BFS
 * 用于查找无向图中任意两个顶点之间的最短路径
 */
public class AllPairsShortestPathBFS {
    /**
     * 图实例
     */
    private IUnDirectedGraph graph;

    /**
     * 单源最短路径算法数组，每个顶点对应一个起点
     */
    private SingleSourceShortestPathBFS[] paths;

    /**
     * 构造函数，初始化所有顶点作为起点进行单源最短路径查找
     *
     * @param graph 要处理的无向图
     */
    public AllPairsShortestPathBFS(IUnDirectedGraph graph) {
        this.graph = graph;
        paths = new SingleSourceShortestPathBFS[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            paths[i] = new SingleSourceShortestPathBFS(graph, i);
        }
    }

    /**
     * 检查两个顶点之间是否存在路径
     *
     * @param source 起点
     * @param target 终点
     * @return 是否连通
     */
    public boolean isConnected(int source, int target) {
        graph.validateVertex(source);
        graph.validateVertex(target);
        return paths[source].isConnected(target);
    }

    /**
     * 获取两个顶点之间的最短路径
     *
     * @param source 起点
     * @param target 终点
     * @return 路径迭代器
     */
    public Iterable<Integer> path(int source, int target) {
        graph.validateVertex(source);
        graph.validateVertex(target);
        return paths[source].path(target);
    }
}
