
package com.hxqzzxk.graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 有向图拓扑排序类，基于深度优先搜索实现
 * 提供对有向图进行拓扑排序的功能，并检测图中是否存在环
 */
public class DirectedTopoSortDFS {
    /**
     * 表示图中是否有环
     */
    private boolean hasCycle = false;

    /**
     * 存储拓扑排序的结果
     */
    private ArrayList<Integer> result;

    /**
     * 构造函数，对指定的有向图进行拓扑排序
     *
     * @param graph 要排序的有向图
     */
    public DirectedTopoSortDFS(IDirectedGraph graph) {
        if ((new DirectedCycleDetection(graph)).hasCycle()) {
            hasCycle = true;
        }
        GraphDFSR dfs = new GraphDFSR(graph);
        result = new ArrayList<>();
        for (int v : dfs.post()) {
            result.add(v);
        }
        Collections.reverse(result);
    }

    /**
     * 判断图中是否有环
     *
     * @return 如果图中存在环返回true，否则返回false
     */
    public boolean hasCycle() {
        return hasCycle;
    }

    /**
     * 获取拓扑排序的结果
     *
     * @return 返回一个可迭代对象，包含拓扑排序后的顶点序列
     */
    public Iterable<Integer> result() {
        return result;
    }
}
