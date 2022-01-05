package com.hackerstudy.adminclient.pojo.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
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
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
        String sexText = context.getReadCellData().getStringValue();
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
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        String sexText = "";
        switch (context.getValue()){
            case 0:
                sexText = SexEnum.MAN.getDescription();
                break;
            case 1:
                sexText = SexEnum.WOMAN.getDescription();
                break;
            default:
                sexText = "";
        }
        return new WriteCellData<>(sexText);
    }
}
