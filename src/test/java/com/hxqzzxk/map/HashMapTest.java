package com.hxqzzxk.map;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hxqzzxk.map.Map.Visitor;

/**
 * HashMap 测试类，继承自 MapTest
 * 用于验证 HashMap 实现类的基本功能和正确性
 */
public class HashMapTest extends MapTest {
    /**
     * 初始化 HashMap 实例
     * 在每次测试方法执行前调用，用于准备测试环境
     */
    @Override
    public void setUp() {
        map = new HashMap<>();
    }

    @Override
    @Test
    public void testTraversal() {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        List<String> results = new ArrayList<>();
        map.traversal(new Visitor<Integer, String>() {
            @Override
            public boolean visit(Integer key, String value) {
                results.add(String.format("%d-%s", key, value));
                return false;
            }
        });

        // 检查遍历结果中包含所有的键值对
        Assert.assertTrue(results.contains("1-one"));
        Assert.assertTrue(results.contains("2-two"));
        Assert.assertTrue(results.contains("3-three"));

        // 确保结果大小一致
        Assert.assertEquals(3, results.size());
    }
}
