package com.example.bag.demo;

import com.example.bag.util.RegexUtil;
import org.junit.Test;

import java.util.regex.Pattern;

public class RegexDemo {

    /*

    g ()

    * 和 + 限定符都是贪婪的，因为它们会尽可能多的匹配文字，只有在它们的后面加上一个 ? 就可以实现非贪婪或最小匹配。
    ^ 和 $ 分别指字符串的开始与结束，\b 描述单词的前或后边界，\B 表示非单词边界

     */


    /*
        匹配静态网页
     */
    @Test
    public void testUrl() {
        String target = "http://www.runoob.com:80/html/html-tutorial.html";
        String regex = "(\\w+):\\/\\/([^/:]+)(:\\d*)?([^# ]*)";

        RegexUtil.matchGroup(target, regex, 4);
    }

    /*
        对一个正则表达式模式或部分模式两边添加圆括号将导致相关匹配存储到一个临时缓冲区中，
        所捕获的每个子匹配都按照在正则表达式模式中从左到右出现的顺序存储。
        缓冲区编号从 1 开始，最多可存储 99 个捕获的子表达式。每个缓冲区都可以使用 \n 访问

        显示相邻重复的单词
     */
    @Test
    public void testDoubleWord() {
        String target = "Is is the cost of of gasoline going up up";
        String regex = "\\b([a-z]+) \\1\\b";
        // String regex = "\\b([a-z]+) ([a-z]+)\\b";  // Is the of gasoline up
        // String regex = "\\b([a-z]+) Is\\b";

        RegexUtil.match(target, regex, Pattern.CASE_INSENSITIVE);
    }

}
