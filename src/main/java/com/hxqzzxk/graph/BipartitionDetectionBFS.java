package com.hxqzzxk.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图的二分图检测器，使用 BFS 实现
 * 用于判断一个无向图是否是二分图
 */
public class BipartitionDetectionBFS {
    /**
     * 图实例
     */
    private IUnDirectedGraph graph;

    /**
     * 记录顶点访问状态
     */
    private boolean[] visited;

    /**
     * 存储每个顶点的颜色（0或1）
     */
    private int[] colors;

    /**
     * 标记图是否为二分图
     */
    private boolean isBipartite;

    /**
     * 构造函数，初始化并进行二分图检测
     *
     * @param graph 要检测的无向图
     */
    public BipartitionDetectionBFS(IUnDirectedGraph graph) {
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
     * 使用BFS进行二分图检测
     *
     * @param vertex 初始遍历顶点
     * @param color  初始颜色
     * @return 是否成功完成二分染色
     */
    private boolean dfs(int vertex, int color) {
        visited[vertex] = true;
        colors[vertex] = color;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int v : graph.edges(current)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                    colors[v] = 1 - colors[current];
                } else if (colors[v] == colors[current]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 返回该图是否为二分图
     *
     * @return 是否为二分图
     */
    public boolean isBipartite() {
        return isBipartite;
    }
}
