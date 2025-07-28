package com.hxqzzxk.graph;

import java.util.TreeSet;

/**
 * 使用邻接表实现的无向图
 * 支持基本的图操作如添加边、查询相邻顶点等
 */
@SuppressWarnings("unchecked")
public class UnDirectedUnWeightedGraph implements IUnDirectedUnWeightedGraph {
    /**
     * 顶点数量。
     * 表示图中顶点的总数。
     */
    private int vertexes;

    /**
     * 边的数量。
     * 表示图中边的总数。
     */
    private int edges;

    /**
     * 邻接表数组。
     * 每个顶点对应一个TreeSet，存储与其相邻的顶点。
     */
    private TreeSet<Integer>[] sets;

    /**
     * 构造函数，根据顶点数、边数和边对创建图
     *
     * @param vertexes 顶点数
     * @param edges    边数
     * @param pairs    边对数组
     */
    public UnDirectedUnWeightedGraph(int vertexes, int edges, int[][] pairs) {
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
     * 构造函数，自动计算顶点数并创建图
     *
     * @param pairs 边对数组
     */
    public UnDirectedUnWeightedGraph(int[][] pairs) {
        int vertexes = 0;
        int edges = pairs.length;
        for (int[] pair : pairs) {
            vertexes = Math.max(vertexes, Math.max(pair[0], pair[1]));
        }
        this.vertexes = vertexes + 1;
        this.edges = edges;
        initSets(pairs);
    }

    /**
     * 初始化邻接表并添加所有边
     *
     * @param pairs 边对数组
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

    /**
     * 获取图中的顶点总数
     *
     * @return 顶点数量
     */
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

    /**
     * 将图对象转换为字符串表示
     *
     * @return 图的字符串描述
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点数: %d, 边数: %d\n", vertexes,
                edges));
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
