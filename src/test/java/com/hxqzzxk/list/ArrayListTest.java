
package com.hxqzzxk.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ArrayList 动态数组测试类，继承自 AbstractListTest
 */
public class ArrayListTest extends AbstractListTest {

    /**
     * 创建 ArrayList 实例
     */
    @Before
    public void createList() {
        list = new ArrayList<>();
    }

    /**
     * 测试 toString 方法验证内部状态
     */
    @Test
    @Override
    public void testToString() {
        Assert.assertEquals("size: 0, elements: []", list.toString());
        list.add(0);
        Assert.assertEquals("size: 1, elements: [0]", list.toString());
        list.add(1);
        Assert.assertEquals("size: 2, elements: [0, 1]", list.toString());
        list.add(2);
        Assert.assertEquals("size: 3, elements: [0, 1, 2]", list.toString());
    }
}
