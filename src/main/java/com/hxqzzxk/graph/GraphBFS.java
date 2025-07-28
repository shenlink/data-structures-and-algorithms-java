package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的 BFS 广度优先搜索遍历的递归实现类，适用于所有图
 */
public class GraphBFS {
    /**
     * 被遍历的图对象
     */
    private IGraph graph;

    /**
     * 记录顶点是否被访问过的数组
     * 索引对应顶点编号，值表示是否已访问
     */
    private boolean[] visited;

    /**
     * 存储遍历结果的列表
     * 按照 BFS 的顺序保存访问的顶点
     */
    private ArrayList<Integer> order = new ArrayList<>();

    /**
     * 构造函数，初始化图并执行 BFS 遍历
     *
     * @param graph 需要被遍历的图对象
     */
    public GraphBFS(IGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertexes()];
        for (int i = 0; i < graph.getVertexes(); i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }
    }

    /**
     * 从指定顶点开始执行广度优先搜索
     *
     * @param vertex 遍历起始顶点
     */
    private void bfs(int vertex) {
        visited[vertex] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            order.add(current);
            for (int v : graph.edges(current)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
    }

    /**
     * 返回 BFS 遍历结果
     *
     * @return 包含 BFS 遍历顺序的可迭代对象
     */
    public Iterable<Integer> order() {
        return order;
    }
}
