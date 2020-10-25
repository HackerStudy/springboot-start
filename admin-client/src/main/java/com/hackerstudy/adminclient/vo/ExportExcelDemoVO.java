package com.hackerstudy.adminclient.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.hackerstudy.adminclient.pojo.converter.SexConverter;
import lombok.Data;

import java.util.Date;

/**
 * @class: ExportExcelDemoVO
 * @description: 导出excel的Demo的展示类
 * @author: HackerStudy
 * @date: 2020-10-25 10:10
 */
@Data
public class ExportExcelDemoVO {
    /** 序号*/
    @ExcelProperty(value = {"用户考试信息","序号"}, index = 0)
    private Integer serialNumber;

    @ColumnWidth(15)
    @ExcelProperty(value = {"用户考试信息","用户id"}, index = 1)
    private Integer userId;

    @ColumnWidth(15)
    @ExcelProperty(value = {"用户考试信息","用户名字"}, index = 2)
    private String userName;

    @ExcelProperty(value = {"用户考试信息","性别"}, index = 3,converter = SexConverter.class)
    private Integer sex;

    /**考试时间*/
    @ColumnWidth(21)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = {"用户考试信息","考试时间"}, index = 4)
    private Date examinationDate;

    /** 总分*/
    @NumberFormat("#.##")
    @ExcelProperty(value = {"用户考试信息","总分"}, index = 5)
    private Double totalFraction;
}
