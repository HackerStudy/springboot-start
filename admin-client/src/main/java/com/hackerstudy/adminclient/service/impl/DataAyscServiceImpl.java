package com.hackerstudy.adminclient.service.impl;

import com.hackerstudy.adminclient.service.DataAsycService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


/**
 * @class: DataAyscServiceImpl
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:04
 */
@Service("dataAyscService")
public class DataAyscServiceImpl implements DataAsycService {
    public static final Logger logger = LoggerFactory.getLogger(DataAyscServiceImpl.class);

    @Override
    @Async("taskExecutor")
    public String getDataByAsyc(List<String> dataList) {
        StringBuffer stringBuffer = new StringBuffer();
        for(String data:dataList){
            stringBuffer.append(data+",");
        }
        return stringBuffer.toString();
    }

    @Override
    @Async("taskExecutor")
    public void readAndWriteFileAsyc(File file,File writeFile) throws IOException {
        long start = System.currentTimeMillis();
        logger.info("线程:{},开始执行",Thread.currentThread().getName());
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream =  new FileOutputStream(writeFile);
        int read = 0;
        byte[] buf = new byte[1024 * 10];
        while((read = bufferedInputStream.read(buf)) != -1)
        {
            fileOutputStream.write(buf, 0, read);
        }
        fileInputStream.close() ; // 关闭输入流
        bufferedInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
        long end = System.currentTimeMillis();
        logger.info("线程:{},执行时间:{} 毫秒",Thread.currentThread().getName(),end-start);
        logger.info("线程:{},结束",Thread.currentThread().getName());
    }
}
