package com.example.bag.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.bag.vo.ExcelRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板的读取类
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class ExcelRecordVoListener extends AnalysisEventListener<ExcelRecordVo> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelRecordVoListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    List<ExcelRecordVo> list = new ArrayList<>();

    public ExcelRecordVoListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelRecordVo data, AnalysisContext context) {
        // LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        for (ExcelRecordVo data : list) {
            check(data.getBrandId(), data.getLogo());
        }
        LOGGER.info("存储数据库成功！");
    }

    //================/================ 业务分割线 ================/================
    private void check(Long brandId, String logo) {
        String sub = logo.substring(logo.lastIndexOf("/") + 1);

        String head = logo.substring(0, logo.lastIndexOf("/") + 1);

        String ret = null;
        try {
            ret = URLEncoder.encode(sub, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (!sub.equals(ret)) {
            System.out.println(brandId + " " + logo + " " + head + ret);
        }
    }
}
