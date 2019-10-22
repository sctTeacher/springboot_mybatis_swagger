package com.shan.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sc
 * @createTime 2019/10/17 16:35
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelUser extends BaseRowModel {

    @ExcelProperty(value = {"人员信息","账户"},index = 0)
    private String username;  //账号
    @ExcelProperty(value = {"人员信息","密码","password"},index = 1)
    private String password;   // 密码
    @ExcelProperty(value = {"人员信息","姓名","name"},index = 2)
    private String name ;// 姓名
    @ExcelProperty(value = {"人员信息","邮箱","emil"},index = 4)
    private String emil ;// 邮箱
    @ExcelProperty(value = {"人员信息","电话","tall"},index = 5)
    private String tall ;// 电话

    @ExcelProperty(value = {"人员信息","身份证","card"},index = 6)
    private String card ;// 身份证

}
