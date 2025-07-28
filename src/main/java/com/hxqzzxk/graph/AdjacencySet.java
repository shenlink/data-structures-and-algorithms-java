package com.hxqzzxk.graph;

import java.util.TreeSet;

/**
 * 邻接集表示法的无向无权图实现
 * 使用TreeSet来存储邻接点，保证有序性和快速查找
 */
@SuppressWarnings("unchecked")
public class AdjacencySet implements IUnDirectedUnWeightedGraph {
    /**
     * 顶点数量
     */
    private int vertexes;

    /**
     * 边的数量
     */
    private int edges;

    /**
     * 邻接集数组，每个顶点对应一个TreeSet存储相邻顶点
     */
    private TreeSet<Integer>[] sets;

    /**
     * 构造函数，指定顶点数、边数和边集合
     *
     * @param vertexes 顶点数
     * @param edges    边数
     * @param pairs    边数组
     */
    public AdjacencySet(int vertexes, int edges, int[][] pairs) {
        if (vertexes <= 0 || edges <= 0) {
            throw new IllegalArgumentException("顶点数和边数必须大于0");
        }
        if (pairs.length != edges) {
            throw new IllegalArgumentException("边数和边数组长度不匹配");
        }

        this.vertexes = vertexes;
        this.edges = edges;
        initSets(pairs);
    }

    /**
     * 构造函数，根据边数组自动计算顶点数
     *
     * @param pairs 边数组
     */
    public AdjacencySet(int[][] pairs) {
        int vertexes = 0;
        for (int[] pair : pairs) {
            vertexes = Math.max(pair[0], Math.max(pair[1], vertexes));
        }
        this.vertexes = vertexes + 1;
        this.edges = pairs.length;
        initSets(pairs);
    }

    /**
     * 初始化邻接集
     *
     * @param pairs 边数组
     */
    private void initSets(int[][] pairs) {
        this.sets = new TreeSet[vertexes];
        for (int i = 0; i < vertexes; i++) {
            sets[i] = new TreeSet<>();
        }
        for (int[] pair : pairs) {
            int vertex1 = pair[0];
            int vertex2 = pair[1];
            validateVertex(vertex1);
            validateVertex(vertex2);
            if (vertex1 == vertex2) {
                throw new IllegalArgumentException("不允许有自环边");
            }
            if (sets[vertex1].contains(vertex2)) {
                throw new IllegalArgumentException("不允许有平行边");
            }
            sets[vertex1].add(vertex2);
            sets[vertex2].add(vertex1);
        }
    }

    @Override
    public int getVertexes() {
        return vertexes;
    }

    @Override
    public int getEdges() {
        return edges;
    }

    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        return sets[vertex1].contains(vertex2);
    }

    @Override
    public Iterable<Integer> edges(int vertex) {
        validateVertex(vertex);
        return sets[vertex];
    }

    @Override
    public int degree(int vertex) {
        validateVertex(vertex);
        return sets[vertex].size();
    }

    @Override
    public void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexes) {
            throw new IllegalArgumentException("顶点" + vertex + "不合法");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点数：%d，边数：%d\n", vertexes, edges));
        for (int i = 0; i < vertexes; i++) {
            stringBuilder.append(i).append(": ");
            for (int v : sets[i]) {
                stringBuilder.append(v).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
