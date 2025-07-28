package com.hxqzzxk.graph;

/**
 * 无权图测试基类
 */
public class IUnWeightedGraphTest extends AbstractGraphTest {
    /**
     * 设置无权图实例
     */
    @Override
    public void setUp() {
        graph = new AdjacencyList(pairs);
    }
}
