package com.hxqzzxk.graph;

/**
 * 使用 DFS 实现的无向图环检测算法
 * 用于判断一个无向图中是否存在环
 */
public class CycleDetectionDFS {
    /**
     * 被检测的图对象
     */
    private IUnDirectedGraph graph;

    /**
     * 记录各个顶点是否已被访问
     */
    private boolean[] visited;

    /**
     * 标记图中是否存在环
     */
    private boolean hasCycle;

    /**
     * 构造函数，初始化图并开始环检测
     *
     * @param graph 图对象
     */
    public CycleDetectionDFS(IUnDirectedGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertexes()];
        for (int vertex = 0; vertex < graph.getVertexes(); vertex++) {
            if (!visited[vertex]) {
                if (dfs(vertex, vertex)) {
                    break;
                }
            }
        }
    }

    /**
     * 深度优先搜索核心方法，用于检测图中是否存在环
     *
     * @param vertex 当前顶点
     * @param parent 父顶点
     * @return 如果检测到环返回true，否则返回false
     */
    private boolean dfs(int vertex, int parent) {
        visited[vertex] = true;
        for (int v : graph.edges(vertex)) {
            if (!visited[v]) {
                if (dfs(v, vertex)) {
                    return true;
                }
            } else if (v != parent) {
                hasCycle = true;
                return true;
            }
        }
        return false;
    }

    /**
     * 判断图中是否存在环
     *
     * @return 如果存在环返回true，否则返回false
     */
    public boolean hasCycle() {
        return hasCycle;
    }
}
