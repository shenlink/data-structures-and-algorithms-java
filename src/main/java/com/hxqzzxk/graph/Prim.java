package com.hxqzzxk.graph;

import java.util.ArrayList;

/**
 * 使用 Prim 算法获取无向带权图的最小生成树
 */
public class Prim implements IMinimumSpanningTree {
    /**
     * 存储最小生成树中的边
     */
    private final ArrayList<UnDirectedWeightedEdge> mst;

    /**
     * 构造函数，使用给定的图构建最小生成树
     *
     * @param graph 给定的无向带权图
     */
    public Prim(IUnDirectedWeightedGraph graph) {
        mst = new ArrayList<>();
        ComponentCountDFS cc =
                new ComponentCountDFS((IUnDirectedGraph) graph);
        if (cc.count() > 1) {
            return;
        }
        boolean[] visited = new boolean[graph.getVertexes()];
        visited[0] = true;
        UnDirectedWeightedEdge minEdge = null;
        for (int i = 1; i < graph.getVertexes(); i++) {
            minEdge = new UnDirectedWeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < graph.getVertexes(); v++) {
                if (visited[v]) {
                    for (int w : graph.edges(v)) {
                        if (!visited[w] && graph.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new UnDirectedWeightedEdge(v, w,
                                    graph.getWeight(v, w));
                        }
                    }
                }
            }
            mst.add(minEdge);
            visited[minEdge.getFrom()] = true;
            visited[minEdge.getTo()] = true;
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
