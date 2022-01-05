package com.hackerstudy.adminclient.listener.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.hackerstudy.adminclient.dto.ExcelDemoDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 有个很重要的点 Listener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class ExcelDemoListener implements ReadListener<ExcelDemoDTO> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    public List<ExcelDemoDTO> getCachedDataList() {
        return cachedDataList;
    }

    /**
     * 缓存的数据
     */
    //private List<ExcelDemoDTO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private List<ExcelDemoDTO> cachedDataList = new ArrayList<>();

    ///**
    // * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
    // */
    //private DemoDAO demoDAO;
    //
    //public DemoDataListener() {
    //    // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
    //    demoDAO = new DemoDAO();
    //}

    ///**
    // * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
    // *
    // * @param demoDAO
    // */
    //public DemoDataListener(DemoDAO demoDAO) {
    //    this.demoDAO = demoDAO;
    //}

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelDemoDTO data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        //// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        //if (cachedDataList.size() >= BATCH_COUNT) {
        //    saveData();
        //    // 存储完成清理 list
        //    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        //}
    }

    @Override
    public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {
        //读取额外的信息，比如备注之类的
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        //saveData();
        log.info("所有数据解析完成！");
    }

    ///**
    // * 加上存储数据库
    // */
    //private void saveData() {
    //    log.info("{}条数据，开始存储数据库！", cachedDataList.size());
    //    demoDAO.save(cachedDataList);
    //    log.info("存储数据库成功！");
    //}
}