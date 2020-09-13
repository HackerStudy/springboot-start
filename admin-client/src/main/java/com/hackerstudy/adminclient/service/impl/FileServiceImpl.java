package com.hackerstudy.adminclient.service.impl;

import com.hackerstudy.adminclient.service.FileService;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * @class: FileServiceImpl
 * @description:
 * @author: Administrator
 * @date: 2019-05-24 11:04
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public void dowloadOne(String filePath,HttpServletResponse response) throws IOException {
        File file = new File(filePath) ;
        InputStream inputStream = new FileInputStream(file) ;
        ServletOutputStream fileOutputStream = response.getOutputStream();
        BufferedInputStream bufferStream = new BufferedInputStream(inputStream);
        int read = 0;
        byte[] buf = new byte[1024 * 10];
        while((read = bufferStream.read(buf,0, 1024 * 10)) != -1)
        {
            fileOutputStream.write(buf, 0, read);
        }
        inputStream.close() ; // 关闭输入流
        fileOutputStream.flush(); // 清空缓存数据
        fileOutputStream.close() ;    // 关闭输出流
    }

    @Override
    public void zipMultipleDowload(List<String> filePaths,HttpServletResponse response) throws IOException {
        ZipOutputStream zipStream = new ZipOutputStream(response.getOutputStream());
        FileInputStream inputStream = null;
        BufferedInputStream bufferStream = null;
        //构造最终压缩包的输出流
        for (String filePath:filePaths){
            File file = new File(filePath);
            if(file.exists()){
                inputStream = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                /**
                 * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                 * 文件名和之前的重复就会导致文件被覆盖
                 */
                ZipEntry zipEntry = new ZipEntry(file.getName());//在压缩目录中文件的名字
                zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                zipStream.setEncoding("GBK");
                bufferStream = new BufferedInputStream(inputStream);
                int read = 0;
                byte[] buf = new byte[1024 * 10];
                while((read = bufferStream.read(buf)) != -1)
                {
                    zipStream.write(buf, 0, read);
                }
                inputStream.close() ; // 关闭输入流
            }
        }
        zipStream.flush(); // 清空缓存数据
        zipStream.close() ;    // 关闭输出流
    }

    @Override
    public void dowloadOneAsync(String filePath,HttpServletResponse response) throws IOException {
        File file = new File(filePath) ;
        InputStream inputStream = new FileInputStream(file) ;
        ServletOutputStream fileOutputStream = response.getOutputStream();
        BufferedInputStream bufferStream = new BufferedInputStream(inputStream);
        int read = 0;
        byte[] buf = new byte[1024 * 10];
        while((read = bufferStream.read(buf,0, 1024 * 10)) != -1)
        {
            fileOutputStream.write(buf, 0, read);
        }
        inputStream.close() ; // 关闭输入流
        fileOutputStream.flush(); // 清空缓存数据
        fileOutputStream.close() ;    // 关闭输出流
    }
}
