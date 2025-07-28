package com.hxqzzxk.graph;

import com.hxqzzxk.uf.QuickUnionWithPathCompression;
import com.hxqzzxk.uf.UnionFind;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 使用 Kruskal 算法获取无向带权图的最小生成树
 */
public class Kruskal implements IMinimumSpanningTree {
    /**
     * 存储最小生成树中的边
     */
    private ArrayList<UnDirectedWeightedEdge> mst;

    /**
     * 构造函数，使用给定的图构建最小生成树
     *
     * @param graph 给定的无向带权图
     */
    public Kruskal(IWeightedGraph graph) {
        mst = new ArrayList<>();
        ComponentCountDFS cc =
                new ComponentCountDFS((IUnDirectedGraph) graph);
        if (cc.count() > 1) {
            return;
        }
        ArrayList<UnDirectedWeightedEdge> edges = new ArrayList<>();
        for (int from = 0; from < graph.getVertexes(); from++) {
            for (int to : graph.edges(from)) {
                if (from < to) {
                    edges.add(new UnDirectedWeightedEdge(from, to,
                            graph.getWeight(from
                                    , to)));
                }
            }
        }
        Collections.sort(edges);
        UnionFind uf = new QuickUnionWithPathCompression(graph.getVertexes());
        for (UnDirectedWeightedEdge edge : edges) {
            int from = edge.getFrom();
            int to = edge.getTo();
            if (!uf.isConnected(from, to)) {
                mst.add(edge);
                uf.union(from, to);
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
