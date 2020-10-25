package com.hackerstudy.adminclient.controller.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.hackerstudy.adminclient.vo.ExportExcelDemoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/excel/exportUserInfo/{userId}")
    public void exportUserInfo(@PathVariable("userId") Integer userId, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
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
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), ExportExcelDemoVO.class).autoCloseStream(Boolean.FALSE).sheet("用户考试信息")
                    .doWrite(exportExcelDemoVOS);
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
}
