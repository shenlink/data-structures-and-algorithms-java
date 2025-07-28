package com.hxqzzxk.graph;

import java.util.ArrayList;

/**
 * 该类用于计算无向图中的连通分量数量，同时可以获取所有的连通分量，使用 DFS 实现。
 */
@SuppressWarnings("unchecked")
public class ComponentCountProDFS {
    /**
     * 图对象，表示要处理的无向图。
     */
    private IUnDirectedGraph graph;

    /**
     * 连通分量的数量。
     */
    private int count;

    /**
     * 记录每个顶点的访问状态以及所属的连通分量编号。
     */
    private int[] visited;

    /**
     * 构造函数，初始化并计算连通分量的数量。
     *
     * @param graph 要处理的无向图
     */
    public ComponentCountProDFS(IUnDirectedGraph graph) {
        this.graph = graph;
        visited = new int[graph.getVertexes()];
        for (int v = 0; v < graph.getVertexes(); v++) {
            visited[v] = -1;
        }
        for (int v = 0; v < graph.getVertexes(); v++) {
            if (visited[v] == -1) {
                dfs(v, count);
                count++;
            }
        }
    }

    /**
     * 使用深度优先搜索（DFS）遍历图，标记连通分量。
     *
     * @param vertex 当前访问的顶点
     * @param count  当前连通分量的编号
     */
    private void dfs(int vertex, int count) {
        visited[vertex] = count;
        for (int v : graph.edges(vertex)) {
            if (visited[v] == -1) {
                dfs(v, count);
            }
        }
    }

    /**
     * 返回图中的连通分量总数。
     *
     * @return 连通分量的数量
     */
    public int count() {
        int max = visited[0];
        for (int j : visited) {
            max = Math.max(max, j);
        }
        return max + 1;
    }

    /**
     * 检查两个顶点是否在同一个连通分量中。
     *
     * @param source 起始顶点
     * @param target 目标顶点
     * @return 如果两个顶点在同一个连通分量中返回true，否则返回false
     */
    public boolean isConnected(int source, int target) {
        graph.validateVertex(source);
        graph.validateVertex(target);
        return visited[source] == visited[target];
    }

    /**
     * 获取所有连通分量的列表。
     *
     * @return 包含所有连通分量的ArrayList数组
     */
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] components = new ArrayList[count];
        for (int i = 0; i < count; i++) {
            components[i] = new ArrayList<>();
        }

        for (int i = 0; i < visited.length; i++) {
            components[visited[i]].add(i);
        }
        return components;
    }
}
