package com.example.bag.demo;

import com.example.bag.util.FileUtil;
import org.junit.Test;

import java.io.File;

public class FileDemo {

    @Test
    public void readAndWrite() {
        System.out.println("start...");

        String sourceFilePath = "/start.txt";
        String targetFilePath = "/end.txt";
        FileUtil.readFileContent(new File(sourceFilePath), new File(targetFilePath));

        System.out.println("end!");
    }

    @Test
    public void write() {
        String sourceFilePath = "/know.txt";
        FileUtil.readFileContent(new File(sourceFilePath), null);
    }
}
