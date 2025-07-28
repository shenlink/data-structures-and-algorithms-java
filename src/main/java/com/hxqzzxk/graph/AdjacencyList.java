package com.hxqzzxk.graph;

import java.util.LinkedList;

/**
 * 使用邻接表实现无向无权图的数据结构
 */
@SuppressWarnings("unchecked")
public class AdjacencyList implements IUnDirectedUnWeightedGraph {
    /**
     * 顶点数量
     */
    private int vertexes;

    /**
     * 边数量
     */
    private int edges;

    /**
     * 邻接表数组，每个顶点对应一个链表存储相邻顶点
     */
    private LinkedList<Integer>[] lists;

    /**
     * 构造函数，使用顶点数、边数和边列表创建图
     *
     * @param vertexes 顶点数
     * @param edges    边数
     * @param pairs    边列表
     */
    public AdjacencyList(int vertexes, int edges, int[][] pairs) {
        if (vertexes <= 0 || edges <= 0) {
            throw new IllegalArgumentException("顶点数和边数必须大于0");
        }
        if (pairs.length != edges) {
            throw new IllegalArgumentException("边数和边数组长度不匹配");
        }
        this.vertexes = vertexes;
        this.edges = edges;
        initLists(pairs);
    }

    /**
     * 构造函数，使用边列表自动推导顶点数
     *
     * @param pairs 边列表
     */
    public AdjacencyList(int[][] pairs) {
        int vertexes = 0;
        for (int[] pair : pairs) {
            vertexes = Math.max(vertexes, Math.max(pair[0], pair[1]));
        }
        this.vertexes = vertexes + 1;
        this.edges = pairs.length;
        initLists(pairs);
    }

    /**
     * 初始化邻接表
     *
     * @param pairs 边列表
     */
    private void initLists(int[][] pairs) {
        this.lists = new LinkedList[vertexes];
        for (int i = 0; i < vertexes; i++) {
            lists[i] = new LinkedList<>();
        }
        for (int[] pair : pairs) {
            int vertex1 = pair[0];
            int vertex2 = pair[1];
            validateVertex(vertex1);
            validateVertex(vertex2);
            if (vertex1 == vertex2) {
                throw new IllegalArgumentException("不允许有自环边");
            }
            if (lists[vertex1].contains(vertex2)) {
                throw new IllegalArgumentException("不允许有平行边");
            }
            lists[vertex1].add(vertex2);
            lists[vertex2].add(vertex1);
        }
    }

    /**
     * 获取图中的顶点数
     *
     * @return 顶点数
     */
    @Override
    public int getVertexes() {
        return vertexes;
    }

    /**
     * 获取图中的边数
     *
     * @return 边数
     */
    @Override
    public int getEdges() {
        return edges;
    }

    /**
     * 判断两个顶点之间是否存在边
     *
     * @param vertex1 第一个顶点
     * @param vertex2 第二个顶点
     * @return 如果存在边返回true，否则返回false
     */
    @Override
    public boolean hasEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        return lists[vertex1].contains(vertex2);
    }

    /**
     * 获取与指定顶点相邻的所有顶点
     *
     * @param vertex 指定的顶点
     * @return 相邻顶点的迭代器
     */
    @Override
    public Iterable<Integer> edges(int vertex) {
        validateVertex(vertex);
        return lists[vertex];
    }

    /**
     * 获取指定顶点的度（连接的边的数量）
     *
     * @param vertex 指定的顶点
     * @return 顶点的度
     */
    @Override
    public int degree(int vertex) {
        validateVertex(vertex);
        return lists[vertex].size();
    }

    /**
     * 验证顶点是否合法
     *
     * @param vertex 要验证的顶点
     */
    @Override
    public void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= vertexes) {
            throw new IllegalArgumentException("顶点" + vertex + "不合法");
        }
    }

    /**
     * 返回图的字符串表示
     *
     * @return 图的字符串表示
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点数：%d，边数：%d\n", vertexes, edges));
        for (int i = 0; i < vertexes; i++) {
            stringBuilder.append(i).append(": ");
            for (int v : lists[i]) {
                stringBuilder.append(v).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

