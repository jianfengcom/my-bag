package com.example.bag.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static void matchGroup(String target, String regex, int groupNum) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(target);
        while (m.find()) {
            for (int i = 0; i <= groupNum; i++) {
                System.out.println(m.group(i));
            }
        }
    }

    public static void match(String target, String regex, int flags) {
        Pattern p;
        if (flags == 2) {
            p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            p = Pattern.compile(regex);
        }

        Matcher m = p.matcher(target);
        while (m.find()) { // 执行一次find(), 匹配项少一个
            System.out.println(m.group(0));
        }
    }

}
