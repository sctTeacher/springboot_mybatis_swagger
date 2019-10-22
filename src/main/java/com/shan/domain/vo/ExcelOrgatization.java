package com.shan.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelOrgatization extends BaseRowModel{

	
	@ExcelProperty(value = {""} ,index = 0)
    private String id;
	
 	@ExcelProperty(value = {"机构名称"} ,index = 1)
    private String orgName;
 	
 	@ExcelProperty(value = {"机构编号"} ,index = 2)
    private String orgNumber;
 	
 	@ExcelProperty(value = {"机构编码"} ,index = 3)
    private String orgCode;

}
