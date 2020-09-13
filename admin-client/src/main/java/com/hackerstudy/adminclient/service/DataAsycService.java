package com.hackerstudy.adminclient.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @class: DataAsycService
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:03
 */
public interface DataAsycService {
    String getDataByAsyc(List<String> dataList);

    /**
     * 异步读写文件
     */
    void readAndWriteFileAsyc(File file,File writeFile) throws IOException;
}
