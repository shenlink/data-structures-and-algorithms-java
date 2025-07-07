package com.hxqzzxk.strings;

/**
 * 蛮力算法实现
 * 该实现采用回溯方式逐个比较字符进行字符串匹配
 */
public class BruteForceMatchBasic implements Match {
    /**
     * 使用蛮力算法查找模式字符串在文本字符串中的首次出现位置
     *
     * @param text    被搜索的文本字符串
     * @param pattern 需要查找的模式字符串
     * @return 如果找到模式字符串，则返回其在文本字符串中首次出现的索引，如果未找到，则返回-1
     */
    @Override
    public int indexOf(String text, String pattern) {
        if (text == null || pattern == null) {
            return -1;
        }
        int tlen = text.length();
        int plen = pattern.length();
        if (plen == 0 || plen == 0 || tlen < plen) {
            return -1;
        }
        int ti = 0;
        int pi = 0;
        while (pi < plen && ti < tlen) {
            if (text.charAt(ti) == pattern.charAt(pi)) {
                ti++;
                pi++;
            } else {
                // ti - pi回到本次比较的位置，+1则是往后移动一位
                ti = ti - pi + 1;
                pi = 0;
            }
        }
        return pi == plen ? ti - pi : -1;
    }
}
