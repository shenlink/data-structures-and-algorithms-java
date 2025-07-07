package com.hxqzzxk.strings;

/**
 * 优化的KMP算法实现
 * 在基础KMP算法上进一步优化next数组的计算，避免相同字符的无效跳转
 */
public class KMPPro implements Match {
    /**
     * 使用优化的KMP算法查找模式字符串在文本字符串中的首次出现位置
     *
     * @param text    被搜索的文本字符串
     * @param pattern 需要查找的模式字符串
     * @return 如果找到模式字符串，则返回其在文本字符串中首次出现的索引，如果未找到，则返回-1
     */
    @Override
    public int indexOf(String text, String pattern) {
        // 检查输入字符串是否为null，如果是，则返回-1
        if (text == null || pattern == null) {
            return -1;
        }
        // 获取文本和模式字符串的长度
        int tlen = text.length();
        int plen = pattern.length();
        // 如果模式字符串为空或者文本字符串长度小于模式字符串长度，则返回-1
        if (plen == 0 || plen == 0 || tlen < plen) {
            return -1;
        }
        // 初始化文本和模式字符串的索引
        int ti = 0;
        int pi = 0;
        // 计算文本字符串中可能的匹配开始位置的最大值
        int tiMax = tlen - plen;
        // 生成模式字符串的next数组，用于KMP算法
        int[] next = next(pattern);
        // 遍历文本字符串和模式字符串，使用KMP算法进行匹配
        while (pi < plen && ti - pi <= tiMax) {
            // 如果模式字符串索引为-1或者当前字符匹配成功，则移动到下一个字符
            if (pi < 0 || text.charAt(ti) == pattern.charAt(pi)) {
                ti++;
                pi++;
            } else {
                // 如果当前字符匹配失败，则根据next数组移动模式字符串的索引
                pi = next[pi];
            }
        }
        // 如果模式字符串完全匹配，则返回匹配开始的索引；否则返回-1
        return pi == plen ? ti - pi : -1;
    }

    /**
     * 计算优化的KMP算法中的next数组
     * next数组记录了模式串中每个位置之前的子串的最长公共前后缀的长度
     * 此方法进行了优化，避免相同字符的无效跳转
     *
     * @param pattern 模式串
     * @return 返回pattern对应的优化后的next数组
     */
    private int[] next(String pattern) {
        // 模式串的长度
        int len = pattern.length();
        // 初始化next数组，长度与模式串相同
        int[] next = new int[len];
        // 初始化变量i（当前考察的字符位置）和n（最长公共前后缀的下一个位置）
        int i = 0;
        int n = -1;
        // 设置初始条件，next数组的第一个元素为-1
        next[i] = n;
        // 模式串最后一个字符的位置
        int iMax = len - 1;
        // 遍历模式串构建next数组
        while (i < iMax) {
            // 当前考察的字符与n位置的字符匹配或n小于0（初始条件），尝试更新i和n
            if (n < 0 || pattern.charAt(i) == pattern.charAt(n)) {
                i++;
                n++;
                // 如果当前考察的字符与n位置的字符不匹配，通过跳转优化next数组的构建
                if (pattern.charAt(i) != pattern.charAt(n)) {
                    next[i] = next[n];
                } else {
                    next[i] = n;
                }
            } else {
                // 当前考察的字符与n位置的字符不匹配，n回退到上一个匹配的位置
                n = next[n];
            }
        }
        // 返回构建好的next数组
        return next;
    }
}
