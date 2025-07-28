package com.hxqzzxk.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用 BFS 实现的无向图环检测算法
 * 用于判断一个无向图中是否存在环
 */
public class CycleDetectionBFS {
    /**
     * 图对象，表示需要进行环检测的无向图
     */
    private IUnDirectedGraph graph;

    /**
     * 标记顶点是否已被访问的数组
     * 下标对应顶点编号，值为true表示该顶点已访问
     */
    private boolean[] visited;

    /**
     * 记录每个顶点父顶点的数组
     * 用于在遍历过程中追踪路径，下标为顶点编号
     */
    private int[] parents;

    /**
     * 标志位，表示图中是否存在环
     * true 表示存在环，false 表示不存在环
     */
    private boolean hasCycle;

    /**
     * 构造函数，初始化图并开始环检测
     *
     * @param graph 图对象
     */
    public CycleDetectionBFS(IUnDirectedGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertexes()];
        parents = new int[graph.getVertexes()];
        for (int vertex = 0; vertex < graph.getVertexes(); vertex++) {
            if (!visited[vertex]) {
                if (bfs(vertex, vertex)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * 广度优先搜索核心方法，用于检测图中是否存在环
     *
     * @param vertex 起始顶点
     * @param parent 父顶点
     * @return 如果检测到环返回true，否则返回false
     */
    private boolean bfs(int vertex, int parent) {
        visited[vertex] = true;
        parents[vertex] = parent;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int v : graph.edges(current)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                    parents[v] = current;
                } else if (v != parents[current]) {
                    return true;
                }
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
