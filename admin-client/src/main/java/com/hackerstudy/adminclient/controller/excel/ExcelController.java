package com.hackerstudy.adminclient.controller.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.hackerstudy.adminclient.dto.ExcelDemoDTO;
import com.hackerstudy.adminclient.listener.excel.ExcelDemoListener;
import com.hackerstudy.adminclient.vo.ExportExcelDemoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @class: ExcelController
 * @description: Excel的操作接口
 * @author: HackerStudy
 * @date: 2020-10-25 10:08
 */
@Api("Excel的操作接口")
@Controller
public class ExcelController {

    @ApiOperation(value="导出用户考试信息Excel", notes="根据用户id导出该用户的考试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true)}
    )
    @GetMapping("/excel/exportUserInfo")
    public void exportUserInfo(HttpServletResponse response) throws IOException {
        // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("用户考试信息", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            //用户信息列表
            List<ExportExcelDemoVO> exportExcelDemoVOS = new ArrayList<>();
            ExportExcelDemoVO exportExcelDemoVO = new ExportExcelDemoVO();
            exportExcelDemoVO.setSerialNumber(1);
            exportExcelDemoVO.setUserId(1);
            exportExcelDemoVO.setSex(0);
            exportExcelDemoVO.setUserName("张三");
            exportExcelDemoVO.setExaminationDate(new Date());
            exportExcelDemoVO.setTotalFraction(653.21);
            exportExcelDemoVOS.add(exportExcelDemoVO);

            ExcelWriter excelWriter = null;
            try {
                // 这里 指定文件
                excelWriter = EasyExcel.write(response.getOutputStream()).build();
                // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
                for (int i = 0; i < 5; i++) {
                    // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(ExportExcelDemoVO.class).build();
                    // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                    excelWriter.write(exportExcelDemoVOS, writeSheet);
                }
            } finally {
                // 千万别忘记finish 会帮忙关闭流
                if (excelWriter != null) {
                    excelWriter.finish();
                }
            }
            // 这里需要设置不关闭流
            //EasyExcel.write(response.getOutputStream(), ExportExcelDemoVO.class).autoCloseStream(Boolean.FALSE).sheet("用户考试信息")
            //        .doWrite(exportExcelDemoVOS);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    @PostMapping("upload")
    @ApiOperation(value="导入excel")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(file.getInputStream()).build();
            ExcelDemoListener oneSheetListener = new ExcelDemoListener();
            ExcelDemoListener twoSheetListener = new ExcelDemoListener();
            // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(ExcelDemoDTO.class).registerReadListener(oneSheetListener).headRowNumber(2).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(ExcelDemoDTO.class).registerReadListener(twoSheetListener).headRowNumber(2).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
            List<ExcelDemoDTO> oneList = oneSheetListener.getCachedDataList();
            List<ExcelDemoDTO> twoList = twoSheetListener.getCachedDataList();
            System.out.println("");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
        return "success";
    }
}
