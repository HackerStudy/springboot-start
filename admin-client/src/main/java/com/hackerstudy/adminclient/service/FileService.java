package com.hackerstudy.adminclient.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @class: FileService
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:03
 */
public interface FileService {
    /**
     * 单个文件下载
     * @throws IOException
     */
    void dowloadOne(String filePath,HttpServletResponse response) throws IOException;

    /**
     * 单个文件下载(多线程)
     * @throws IOException
     */
    void dowloadOneAsync(String filePath,HttpServletResponse response) throws IOException;

    /**
     * 多文件下载
     * @throws IOException
     */
    void zipMultipleDowload(List<String> filePaths,HttpServletResponse response) throws IOException;
}
