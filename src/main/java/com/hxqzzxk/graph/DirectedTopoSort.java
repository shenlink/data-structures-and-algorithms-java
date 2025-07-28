package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 有向图的拓扑排序类
 * 用于检测图中是否存在环，并生成拓扑排序结果
 */
public class DirectedTopoSort {
    /**
     * 存储拓扑排序结果的列表
     */
    private ArrayList<Integer> result;

    /**
     * 标记图中是否存在环
     */
    private boolean hasCycle;

    /**
     * 构造函数，对给定有向图进行拓扑排序
     *
     * @param graph 给定的有向图
     */
    public DirectedTopoSort(IDirectedGraph graph) {
        result = new ArrayList<>();
        int[] inDegrees = new int[graph.getVertexes()];
        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < graph.getVertexes(); v++) {
            inDegrees[v] = graph.getInDegree(v);
            if (inDegrees[v] == 0) {
                queue.offer(v);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            for (int v : graph.edges(current)) {
                inDegrees[v]--;
                if (inDegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (result.size() != graph.getVertexes()) {
            hasCycle = true;
            result.clear();
        }
    }

    /**
     * 判断图中是否存在环
     *
     * @return 如果存在环返回true，否则返回false
     */
    public boolean hasCycle() {
        return hasCycle;
    }

    /**
     * 获取拓扑排序结果
     *
     * @return 返回拓扑排序结果的可迭代对象
     */
    public Iterable<Integer> result() {
        return result;
    }
}
