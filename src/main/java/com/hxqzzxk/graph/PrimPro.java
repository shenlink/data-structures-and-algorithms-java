package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 使用改进版的 Prim 算法获取无向带权图的最小生成树
 */
public class PrimPro implements IMinimumSpanningTree {
    /**
     * 存储最小生成树中的边
     */
    private final ArrayList<UnDirectedWeightedEdge> mst;

    /**
     * 构造函数，使用给定的图构建最小生成树
     *
     * @param graph 给定的无向带权图
     */
    public PrimPro(IWeightedGraph graph) {
        mst = new ArrayList<>();
        ComponentCountDFS cc =
                new ComponentCountDFS((IUnDirectedGraph) graph);
        if (cc.count() > 1) {
            return;
        }
        boolean[] visited = new boolean[graph.getVertexes()];
        visited[0] = true;
        Queue<UnDirectedWeightedEdge> queue = new PriorityQueue<>();
        for (int v : graph.edges(0)) {
            queue.offer(new UnDirectedWeightedEdge(0, v, graph.getWeight(0,
                    v)));
        }
        while (!queue.isEmpty()) {
            UnDirectedWeightedEdge minEdge = queue.poll();
            if (visited[minEdge.getFrom()] && visited[minEdge.getTo()]) {
                continue;
            }
            mst.add(minEdge);
            int newVertex = visited[minEdge.getFrom()] ? minEdge.getTo() :
                    minEdge.getFrom();
            visited[newVertex] = true;
            for (int v : graph.edges(newVertex)) {
                if (!visited[v]) {
                    queue.offer(new UnDirectedWeightedEdge(newVertex, v,
                            graph.getWeight(newVertex, v)));
                }
            }
        }
    }

    /**
     * 返回最小生成树中的所有边
     *
     * @return 最小生成树中的所有边
     */
    @Override
    public Iterable<UnDirectedWeightedEdge> mst() {
        return mst;
    }
}
