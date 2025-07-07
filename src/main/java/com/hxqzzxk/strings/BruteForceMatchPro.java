package com.hxqzzxk.strings;

/**
 * 优化的蛮力算法实现
 * 在原始蛮力算法基础上增加了对最大匹配位置的限制以提高效率
 */
public class BruteForceMatchPro implements Match {
    /**
     * 使用优化的蛮力算法查找模式字符串在文本字符串中的首次出现位置
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
        // ti的最后一个位置，当ti等于tlen - plen时，
        // 此时的pattern的长度更好匹配剩下的长度
        int tiMax = tlen - plen;
        // ti – pi 是指每一轮比较中 text 首个比较字符的位置，
        // 显而易见，这个位置不能超过tiMax，否则就越界了
        while (pi < plen && ti - pi <= tiMax) {
            if (text.charAt(ti) == pattern.charAt(pi)) {
                ti++;
                pi++;
            } else {
                ti = ti - pi + 1;
                pi = 0;
            }
        }
        return pi == plen ? ti - pi : -1;
    }
}
