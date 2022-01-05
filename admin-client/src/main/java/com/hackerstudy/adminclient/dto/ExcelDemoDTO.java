package com.hackerstudy.adminclient.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.hackerstudy.adminclient.pojo.converter.SexConverter;
import lombok.Data;

import java.util.Date;

/**
 * @class: ExcelDemoVO
 * @description: excel的Demo的展示类
 * @author: HackerStudy
 * @date: 2020-10-25 10:10
 */
@Data
public class ExcelDemoDTO {
    /** 序号*/
    private Integer serialNumber;

    private Integer userId;

    private String userName;

    @ExcelProperty(converter = SexConverter.class)
    private Integer sex;

    /**考试时间*/
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date examinationDate;

    /** 总分*/
    @NumberFormat("#.##")
    private Double totalFraction;
}
