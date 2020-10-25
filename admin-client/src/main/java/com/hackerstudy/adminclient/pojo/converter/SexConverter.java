package com.hackerstudy.adminclient.pojo.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.hackerstudy.adminclient.enums.SexEnum;

/**
 * @class: SexConverter
 * @description:
 * @author: HackerStudy
 * @date: 2020-10-25 10:40
 */
public class SexConverter implements Converter<Integer> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String sexText = cellData.getStringValue();
        Integer sex = null;
        switch (sexText){
            case "男":
                sex = SexEnum.MAN.getCode();
                break;
            case "女":
                sex = SexEnum.WOMAN.getCode();
                break;
            default:
                sex = -1;
        }
        return sex;
    }

    @Override
    public CellData convertToExcelData(Integer s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String sexText = "";
        switch (s){
            case 0:
                sexText = SexEnum.MAN.getDescription();
                break;
            case 1:
                sexText = SexEnum.WOMAN.getDescription();
                break;
            default:
                sexText = "";
        }
        return new CellData(sexText);
    }
}
