package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用 BFS 实现的无向图单源最短路径算法
 */
public class SingleSourceShortestPathBFS {
    /**
     * 图对象，表示当前操作的无向图
     */
    private IUnDirectedGraph graph;

    /**
     * 访问状态数组，记录每个顶点是否已被访问
     */
    private boolean[] visited;

    /**
     * 父节点数组，记录每个顶点的前驱顶点用于重建路径
     */
    private int[] parents;

    /**
     * 源点顶点，所有路径计算的起点
     */
    private int source;

    /**
     * 构造函数，初始化图并从指定源点开始BFS遍历
     *
     * @param graph  图对象
     * @param source 源点顶点
     */
    public SingleSourceShortestPathBFS(IUnDirectedGraph graph, int source) {
        this.graph = graph;
        graph.validateVertex(source);
        this.visited = new boolean[graph.getVertexes()];
        this.parents = new int[graph.getVertexes()];
        this.source = source;
        for (int i = 0; i < graph.getVertexes(); i++) {
            parents[i] = -1;
        }
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }
    }

    /**
     * 广度优先搜索核心方法，用于找到图中的连通分量并记录路径
     *
     * @param vertex 起始顶点
     */
    private void bfs(int vertex) {
        visited[vertex] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int v : graph.edges(current)) {
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                    parents[v] = current;
                }
            }
        }
    }

    /**
     * 判断目标顶点是否与源点连通
     *
     * @param target 目标顶点
     * @return 如果连通返回true，否则返回false
     */
    public boolean isConnected(int target) {
        graph.validateVertex(target);
        return visited[target];
    }

    /**
     * 获取从源点到目标顶点的路径
     *
     * @param target 目标顶点
     * @return 路径上的顶点迭代器，如果不可达则返回空列表
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
            current = parents[current];
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}
