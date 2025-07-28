package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 邻接矩阵表示法的无向无权图实现
 * 使用二维数组存储边关系
 */
public class AdjacencyMatrix implements IUnDirectedUnWeightedGraph {
    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    /**
     * 顶点数量
     */
    private int vertexes;

    /**
     * 边的数量
     */
    private int edges;

    /**
     * 构造函数，指定顶点数、边数和边集合
     *
     * @param vertexes 顶点数
     * @param edges    边数
     * @param pairs    边数组
     */
    public AdjacencyMatrix(int vertexes, int edges, int[][] pairs) {
        if (vertexes <= 0 || edges <= 0) {
            throw new IllegalArgumentException("顶点数和边数必须大于0");
        }
        if (pairs.length != edges) {
            throw new IllegalArgumentException("边数和边数组长度不匹配");
        }

        this.vertexes = vertexes;
        this.edges = edges;
        initMatrix(pairs);
    }

    /**
     * 构造函数，根据边数组自动计算顶点数
     *
     * @param pairs 边数组
     */
    public AdjacencyMatrix(int[][] pairs) {
        int vertexes = 0;
        for (int[] pair : pairs) {
            int vertex1 = pair[0];
            int vertex2 = pair[1];
            vertexes = Math.max(vertexes, Math.max(vertex1, vertex2));
        }
        this.vertexes = vertexes + 1;
        this.edges = pairs.length;
        initMatrix(pairs);
    }

    /**
     * 初始化邻接矩阵
     *
     * @param pairs 边数组
     */
    private void initMatrix(int[][] pairs) {
        this.matrix = new int[vertexes][vertexes];
        for (int[] pair : pairs) {
            int vertex1 = pair[0];
            int vertex2 = pair[1];
            if (vertex1 == vertex2) {
                throw new IllegalArgumentException("不允许有自环边");
            }
            if (matrix[vertex1][vertex2] == 1) {
                throw new IllegalArgumentException("不允许有平行边");
            }
            matrix[vertex1][vertex2] = 1;
            matrix[vertex2][vertex1] = 1;
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
        return matrix[vertex1][vertex2] == 1;
    }

    @Override
    public Iterable<Integer> edges(int vertex) {
        validateVertex(vertex);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vertexes; i++) {
            if (matrix[vertex][i] == 1) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public int degree(int vertex) {
        validateVertex(vertex);
        int total = 0;
        for (int i = 0; i < vertexes; i++) {
            if (matrix[vertex][i] == 1) {
                total++;
            }
        }
        return total;
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
            for (int j = 0; j < vertexes; j++) {
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}