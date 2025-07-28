package com.hxqzzxk.graph;

/**
 * 该类用于计算无向图中的连通分量数量，使用 DFS 实现
 */
public class ComponentCountDFS {
    /**
     * 图对象，表示要处理的无向图
     */
    private IUnDirectedGraph graph;

    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 记录每个顶点的访问状态
     */
    private boolean[] visited;

    /**
     * 构造函数，初始化并计算连通分量的数量
     *
     * @param graph 要处理的无向图
     */
    public ComponentCountDFS(IUnDirectedGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertexes()];
        for (int v = 0; v < graph.getVertexes(); v++) {
            if (!visited[v]) {
                dfs(v);
                count++;
            }
        }
    }

    /**
     * 使用深度优先搜索(DFS)遍历图，标记连通分量
     *
     * @param vertex 当前访问的顶点
     */
    private void dfs(int vertex) {
        visited[vertex] = true;
        for (int v : graph.edges(vertex)) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    /**
     * 返回图中的连通分量数量
     *
     * @return 连通分量的数量
     */
    public int count() {
        return count;
    }
}
