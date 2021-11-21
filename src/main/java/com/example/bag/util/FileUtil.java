package com.example.bag.util;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    /*
        ##: 读取File
     */
    public static void readFileContent(File source, File target) {
        BufferedReader bufferedReader = null;

        try {
            if (target != null && !target.exists()) {
                try {
                    target.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 创建一个读取文件的流对象 FileReader
            // 用BufferedReader封装一下，读取效率更高
            bufferedReader = new BufferedReader(new FileReader(source));

            /*
                bufferedReader.readLine(): 读取文件的一行，如果有多行，会逐行读取。
             */
            String line;
            // List<String> tempList = new ArrayList<>(); temp1
            StringBuilder sb = new StringBuilder(); // temp2
            int incr = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (StringUtils.hasText(line))
                    continue;

                // ================================= 核心处理逻辑 start =================================
                // JSONObject obj = JSON.parseObject(line);

                String out = "模板" + incr++;
                // tempList.add(out); temp1
                sb.append(line); // temp2

                // ================================= 核心处理逻辑 end =================================

            }
            /*if (!tempList.isEmpty()) { temp1
                WriterUtil.writeFile(target, tempList);
            }*/
            String[] result = stringToStringArray(sb.toString(), 50); // temp2
            for (String l : result) {
                System.out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 最后不要忘记关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static String[] stringToStringArray(String src, int length) {
        //检查参数是否合法
        if (null == src || src.equals("")) {
            return null;
        }

        if (length <= 0) {
            return null;
        }
        int n = (src.length() + length - 1) / length; //获取整个字符串可以被切割成字符子串的个数
        String[] split = new String[n];
        for (int i = 0; i < n; i++) {
            if (i < (n - 1)) {
                split[i] = src.substring(i * length, (i + 1) * length);
            } else {
                split[i] = src.substring(i * length);
            }
        }
        return split;
    }
}
