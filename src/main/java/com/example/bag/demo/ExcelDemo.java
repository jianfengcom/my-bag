package com.example.bag.demo;

import com.alibaba.excel.EasyExcel;
import com.example.bag.listener.ExcelRecordVoListener;
import com.example.bag.vo.ExcelRecordVo;

public class ExcelDemo {

    public static void main(String[] args) {
        String fileName = "/future/demo/brand_logos.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExcelRecordVo.class, new ExcelRecordVoListener()).sheet().doRead();
    }

}
