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
    ///** 忽略这个字段*/
    //@ExcelIgnore
    @ExcelProperty(value = {"用户考试信息","序号"}, index = 0)  //复杂头写入,指定
    private Integer serialNumber;

    @ColumnWidth(15)
    @ExcelProperty(value = {"用户考试信息","用户id"}, index = 1)
    private Integer userId;

    @ColumnWidth(15)
    @ExcelProperty(value = {"用户考试信息","用户名字"}, index = 2)
    private String userName;

    @ExcelProperty(value = {"用户考试信息","性别"}, index = 3,converter = SexConverter.class)  //自定义格式转换
    private Integer sex;

    /**考试时间*/
    @ColumnWidth(21) //定义列宽
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss") //时间的转换
    @ExcelProperty(value = {"用户考试信息","考试时间"}, index = 4)
    private Date examinationDate;

    /** 总分*/
    @NumberFormat("#.##") //小数点的转换
    @ExcelProperty(value = {"用户考试信息","总分"}, index = 5)
    private Double totalFraction;
}
