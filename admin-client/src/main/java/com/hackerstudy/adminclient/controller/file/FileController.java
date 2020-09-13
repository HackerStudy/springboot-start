package com.hackerstudy.adminclient.controller.file;

import com.hackerstudy.adminclient.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @class: FileController
 * @description:
 * @author: yangpeng03614
 * @date: 2019-04-04 14:57
 */
@Api(description = "文件服务API接口")
@Controller
public class FileController {
    public static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @ApiOperation(value="下载文件", notes="下载文件")
    @RequestMapping(value = "/file/download",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void dowload(HttpServletResponse response,@RequestParam("filePaths") String[] filePaths) {
        try {
            List<String> filePathList = Arrays.asList(filePaths);
            if(null!=filePathList&&filePathList.size()>1){
                String name = new String("文件.zip".getBytes("UTF-8"),"ISO8859-1");
                String type = new MimetypesFileTypeMap().getContentType(name);
                response.reset();
                response.setHeader("Content-type",type);
                response.setHeader("Content-Disposition", "attachment;filename=" + name );
                fileService.zipMultipleDowload(filePathList,response);
            }else if(null!=filePathList&&filePathList.size()==1){
                String filePath = filePathList.get(0);
                String fileName = new String(filePath.substring(filePath.lastIndexOf("/")+1).getBytes("UTF-8"),"ISO8859-1");
                String type = new MimetypesFileTypeMap().getContentType(fileName);
                response.setHeader("Content-type",type);
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
                fileService.dowloadOne(filePath,response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件下载错误={}",e.getMessage());
        }
    }

    @ApiOperation(value="文件列表", notes="文件列表")
    @GetMapping("/file/fileList")
    public String jumpFileList() {
        return "/thymeleaf/file/file_list";
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @ApiOperation(value="上传文件", notes="上传文件")
    @PostMapping("/file/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName.indexOf("\\") != -1){
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        String filePath = "src/main/resources/static/files/";
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功!";
    }
}
