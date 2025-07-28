package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 使用 DFS 实现的无向图单源最短路径算法类
 */
public class SingleSourceShortestPathDFS {
    /**
     * 无向图对象
     */
    private final IUnDirectedGraph graph;

    /**
     * 起始顶点
     */
    private final int source;

    /**
     * 记录顶点是否被访问过
     */
    private final boolean[] visited;

    /**
     * 记录路径中每个顶点的前一个顶点
     */
    private final int[] prev;

    /**
     * 构造函数，初始化图和起始顶点，并执行DFS查找
     *
     * @param graph  图对象
     * @param source 起始顶点
     */
    public SingleSourceShortestPathDFS(IUnDirectedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.getVertexes()];
        this.prev = new int[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            this.prev[i] = -1;
        }
        dfs(source, source);
    }

    /**
     * 深度优先搜索算法实现
     *
     * @param vertex 当前访问的顶点
     * @param parent 当前顶点的父顶点
     */
    private void dfs(int vertex, int parent) {
        visited[vertex] = true;
        prev[vertex] = parent;
        for (int v : graph.edges(vertex)) {
            if (!visited[v]) {
                dfs(v, vertex);
            }
        }
    }

    /**
     * 判断目标顶点是否与起始顶点连通
     *
     * @param target 目标顶点
     * @return 如果连通返回true，否则返回false
     */
    public boolean isConnected(int target) {
        graph.validateVertex(target);
        return visited[source] == visited[target];
    }

    /**
     * 获取从起点到目标顶点的路径
     *
     * @param target 目标顶点
     * @return 返回路径的顶点迭代器
     */
    public Iterable<Integer> path(int target) {
        graph.validateVertex(target);
        ArrayList<Integer> path = new ArrayList<>();
        if (!isConnected(target)) {
            return path;
        }

        int current = target;
        while (current != source) {
            path.add(current);
            current = prev[current];
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}
