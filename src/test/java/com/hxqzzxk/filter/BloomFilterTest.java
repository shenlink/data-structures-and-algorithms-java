package com.hxqzzxk.filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * BloomFilter 的单元测试类，用于验证布隆过滤器的核心功能。
 */
public class BloomFilterTest {

    /**
     * 测试使用的布隆过滤器实例。
     */
    private BloomFilter<String> bloomFilter;

    /**
     * 在每个测试用例执行前初始化一个新的布隆过滤器。
     */
    @Before
    public void setUp() {
        bloomFilter = new BloomFilter<>(100, 0.05);
    }

    /**
     * 验证布隆过滤器的基本插入和查找功能。
     * 插入一个元素后，应能正确识别其存在。
     */
    @Test
    public void testPutAndContains() {
        Assert.assertFalse(bloomFilter.contains("testItem"));
        String item = "testItem";
        bloomFilter.put(item);
        Assert.assertTrue(bloomFilter.contains(item));
    }

    /**
     * 测试向布隆过滤器插入 null 值时是否抛出 IllegalArgumentException。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullValuePut() {
        bloomFilter.put(null);
    }

    /**
     * 测试检查 null 值是否存在时是否抛出 IllegalArgumentException。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullValueContains() {
        bloomFilter.contains(null);
    }

    /**
     * 验证布隆过滤器的误判率是否在预期范围内。
     * 插入一组元素后，检查未插入的元素是否被错误报告为存在的次数。
     */
    @Test
    public void testFalsePositiveRate() {
        int totalElements = 100;
        int falsePositives = 0;

        for (int i = 0; i < totalElements; i++) {
            String element = "element" + i;
            bloomFilter.put(element);
        }

        for (int i = 0; i < totalElements; i++) {
            String notInsertedElement = "notInsertedElement" + i;
            if (bloomFilter.contains(notInsertedElement)) {
                falsePositives++;
            }
        }

        double actualFalsePositiveRate = (double) falsePositives / totalElements;
        Assert.assertTrue(actualFalsePositiveRate <= 0.05 + 0.01); // 允许一定误差
    }

    /**
     * 测试多次插入同一元素时的行为。
     * 第一次插入应返回 false（之前不存在），
     * 第二次插入应返回 true（已存在）。
     */
    @Test
    public void testMultipleInsertions() {
        String item = "item";

        Assert.assertFalse(bloomFilter.contains(item));
        Assert.assertFalse(bloomFilter.put(item));

        Assert.assertTrue(bloomFilter.contains(item));
        Assert.assertTrue(bloomFilter.put(item));
    }
}
