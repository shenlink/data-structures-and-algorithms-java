package com.hxqzzxk.graph;

import java.util.Set;
import java.util.TreeSet;

/**
 * 有向图类，使用邻接表实现。
 * 支持常见的图操作，如添加边、检查边是否存在、获取入度出度等。
 */
@SuppressWarnings("unchecked")
public class DirectedGraph implements IDirectedGraph {
    /**
     * 图中的顶点数量
     */
    private int vertexes;

    /**
     * 图中的边数量
     */
    private int edges;

    /**
     * 邻接表数组，每个顶点对应一个集合，存储其邻接顶点
     */
    private Set<Integer>[] sets;

    /**
     * 每个顶点的入度数组
     */
    private int[] inDegree;

    /**
     * 每个顶点的出度数组
     */
    private int[] outDegree;

    /**
     * 构造一个有向图。
     *
     * @param vertexes 图中的顶点数
     * @param edges    图中的边数
     * @param pairs    边的数组，每项为两个顶点组成的数组，表示一条边
     */
    public DirectedGraph(int vertexes, int edges, int[][] pairs) {
        if (vertexes <= 0 || edges <= 0) {
            throw new IllegalArgumentException("顶点数和边数必须大于0");
        }
        if (pairs.length != edges) {
            throw new IllegalArgumentException("边数和边数组长度不匹配");
        }

        this.vertexes = vertexes;
        this.edges = edges;
        inDegree = new int[vertexes];
        outDegree = new int[vertexes];
        initSets(pairs);
    }

    /**
     * 根据给定的边数组构造有向图。
     * 自动计算最大顶点编号并设置顶点总数。
     *
     * @param pairs 边的数组，每项为两个顶点组成的数组，表示一条边
     */
    public DirectedGraph(int[][] pairs) {
        int vertexes = 0;
        int edges = pairs.length;
        for (int[] pair : pairs) {
            vertexes = Math.max(vertexes, Math.max(pair[0], pair[1]));
        }
        this.vertexes = vertexes + 1;
        this.edges = edges;
        inDegree = new int[this.vertexes];
        outDegree = new int[this.vertexes];
        initSets(pairs);
    }

    /**
     * 初始化邻接表并添加所有边。
     *
     * @param pairs 边的数组，用于初始化邻接表
     */
    private void initSets(int[][] pairs) {
        this.sets = new TreeSet[vertexes];
        for (int i = 0; i < vertexes; i++) {
            sets[i] = new TreeSet<>();
        }
        for (int[] pair : pairs) {
            int from = pair[0];
            int to = pair[1];
            validateVertex(from);
            validateVertex(to);
            if (from == to) {
                throw new IllegalArgumentException("不允许有自环边");
            }
            if (sets[from].contains(to)) {
                throw new IllegalArgumentException("不允许有平行边");
            }
            sets[from].add(to);
            inDegree[to]++;
            outDegree[from]++;
        }
    }

    /**
     * 获取图中顶点的数量。
     *
     * @return 顶点数量
     */
    @Override
    public int getVertexes() {
        return vertexes;
    }

    /**
     * 获取图中边的数量。
     *
     * @return 边的数量
     */
    @Override
    public int getEdges() {
        return edges;
    }

    /**
     * 判断指定的两个顶点之间是否存在边。
     *
     * @param vertex1 起始顶点
     * @param vertex2 目标顶点
     * @return 如果存在边则返回 true，否则返回 false
     */
    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        return sets[vertex1].contains(vertex2);
    }

    /**
     * 获取指定顶点的所有邻接顶点。
     *
     * @param vertex 给定的顶点
     * @return 包含所有邻接顶点的可迭代对象
     */
    @Override
    public Iterable<Integer> edges(int vertex) {
        validateVertex(vertex);
        return sets[vertex];
    }

    /**
     * 获取指定顶点的入度。
     *
     * @param vertex 给定的顶点
     * @return 入度值
     */
    @Override
    public int getInDegree(int vertex) {
        validateVertex(vertex);
        return inDegree[vertex];
    }

    /**
     * 获取指定顶点的出度。
     *
     * @param vertex 给定的顶点
     * @return 出度值
     */
    @Override
    public int getOutDegree(int vertex) {
        validateVertex(vertex);
        return outDegree[vertex];
    }

    /**
     * 验证给定的顶点是否合法。
     *
     * @param vertex 待验证的顶点
     * @throws IllegalArgumentException 如果顶点不合法，抛出异常
     */
    @Override
    public void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexes) {
            throw new IllegalArgumentException("顶点" + vertex + "不合法");
        }
    }

    /**
     * 返回图的字符串表示形式。
     *
     * @return 图的字符串表示
     */
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
