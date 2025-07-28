package com.hxqzzxk.graph;

/**
 * 使用 DFS 实现的二分图检测算法
 * 用于判断一个无向图是否是二分图
 */
public class BipartitionDetectionDFS {
    /**
     * 图对象，表示需要检测的无向图
     */
    private IUnDirectedGraph graph;

    /**
     * 访问标记数组，记录每个顶点是否已被访问
     */
    private boolean[] visited;

    /**
     * 颜色数组，存储每个顶点的颜色（0或1），用于二分图检测
     */
    private int[] colors;

    /**
     * 标记图是否为二分图的布尔值
     */
    private boolean isBipartite;

    /**
     * 构造函数，初始化图并开始二分图检测
     *
     * @param graph 图对象
     */
    public BipartitionDetectionDFS(IUnDirectedGraph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getVertexes()];
        this.colors = new int[graph.getVertexes()];
        this.isBipartite = true;

        for (int v = 0; v < graph.getVertexes(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
                ;
            }
        }
    }

    /**
     * 深度优先搜索核心方法，用于给图着色并检测是否符合二分图特性
     *
     * @param vertex 当前顶点
     * @param color  当前颜色（0或1）
     * @return 如果当前颜色分配有效返回true，否则返回false
     */
    private boolean dfs(int vertex, int color) {
        visited[vertex] = true;
        colors[vertex] = color;
        for (int v : graph.edges(vertex)) {
            if (!visited[v]) {
                if (!dfs(v, 1 - color)) {
                    return false;
                }
            } else if (colors[v] == colors[vertex]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断图是否是二分图
     *
     * @return 如果是二分图返回true，否则返回false
     */
    public boolean isBipartite() {
        return isBipartite;
    }
}
