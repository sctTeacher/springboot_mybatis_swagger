package com.shan.controller;

import com.alibaba.excel.metadata.BaseRowModel;
import com.google.common.io.Files;
import com.shan.common.result.CodeMsg;
import com.shan.common.result.Result;
import com.shan.domain.User;
import com.shan.domain.vo.ExcelOrgatization;
import com.shan.domain.vo.ExcelUser;
import com.shan.mapper.UserMapper;
import com.shan.utils.excelUtile.ExcelUtil;
import com.shan.utils.excelUtile.ExcelUtil.ExcelParameter;
import com.shan.utils.excelUtile.ExcelUtil.MergeParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author sc
 * @createTime 2019/10/17 16:33
 * @description
 */

@Api(description = "用户导入导出管理")
@Slf4j
@Controller
public class ExcelController  {


    @Autowired
    private UserMapper userMapper;


    @PostMapping(value = "/importExcel",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "excel员工批量导入")
    @ResponseBody
    public Result<Boolean> batchAddByExcel(@RequestPart(value = "excel") MultipartFile excel){
        String fileName = excel.getOriginalFilename().toUpperCase();
        if(!fileName.endsWith(".XLSX")){
         return Result.error(CodeMsg.SERVER_EXCEPTION);
        }
        int index = 0;
        try {
            ExcelParameter excelParameter=new ExcelParameter();
            excelParameter.setSheetNo(1);
            //指定标题占几行 只读取标题下的数据
            excelParameter.setHeadLineMun(3);
            List<Object> objects = ExcelUtil.readJavaModel(excel.getInputStream(), excelParameter, ExcelUser.class);
            if(CollectionUtils.isEmpty(objects)){
                return Result.error(CodeMsg.SERVER_EXCEPTION);
            }
          List<User> users=new ArrayList<>();
            for(int i=0 ;i<objects.size();i++){
                index = i + 1;
                ExcelUser excelUser = (ExcelUser)objects.get(i);
                User user =new User();
                user.setUsername(excelUser.getUsername());
                user.setPassword(excelUser.getPassword());
                user.setName(excelUser.getName());
                users.add(user);
            }
            if(!CollectionUtils.isEmpty(users)){
                userMapper.insertBatch(users);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_EXCEPTION.fillArgs("excel第" + index + "行数据错误," + e.getMessage()));
        }
        return Result.success(true);

    }

    /**
     * excel导出 封装数据
     * @return
     */
   @GetMapping("/downloadExcel")
   @ApiOperation(value = "excel导出", notes = "excel导出")
    public ResponseEntity<byte[]> downExcelTemplate(){
        //封装用户信息
        List<ExcelUser> excelUserlist =new ArrayList<>();
        for (int i=0; i<200;i++){
            ExcelUser user= new ExcelUser(i+"","123456","测试"+i,"123@106.com","15510124991","11000000");
            excelUserlist.add(user);
        }
        //封装机构信息
       List<ExcelOrgatization> ecelOrgList=new ArrayList<>();
       for (int i=0 ; i < 50 ; i++){
           ExcelOrgatization org=new  ExcelOrgatization(i+"","中煤","AA"+i,"AA"+i);
           ecelOrgList.add(org);
       }



        List<ExcelParameter> excelParameters = this.generaEmpExcelParam();
        List<List<? extends BaseRowModel>> dataList = Arrays.asList(excelUserlist,ecelOrgList);
        File tempFile = null;
        try {
            tempFile = Files.createTempDir();
            File file = new File(tempFile.getAbsolutePath() + File.separator +System.currentTimeMillis() + ".xlsx");

            if(!file.exists()){
                file.createNewFile();
            }
            OutputStream outputStream=new FileOutputStream(file);
            ExcelUtil.writeExcel(outputStream,excelParameters,dataList);
            return this.downLoad(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
               FileUtils.deleteDirectory(tempFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

/*    private  ResponseEntity<byte[]> downLoad(String filePaths) {
        ResponseEntity <byte[]> result = null;
        try {
            //设置响应头
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", System.currentTimeMillis() + ".xlsx");
            byte[] b = FileUtils.readFileToByteArray(new File(filePaths));
            if(b.length > 0) {
                result = new  ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("downloadAllFile-下载附件失败,错误信息=[{}]",e);
        } finally {
            return result;
        }
    }*/

    /**
     * 下载文件
     * @param filrPath
     * @return
     */
    private ResponseEntity<byte []> downLoad(String filrPath){
        ResponseEntity<byte []> result=null;
        try {

            //设置响应头
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",System.currentTimeMillis()+".xlsx");
            byte[] bytes = FileUtils.readFileToByteArray(new File(filrPath));
            result=new ResponseEntity<byte []>(bytes,headers,HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /**
     * 构造excel相关参数
     * @return
     */
    private List<ExcelParameter> generaEmpExcelParam() {
        List<ExcelParameter> excelParameterList = new ArrayList<>();
        ExcelParameter excelParam1 =  new ExcelParameter();
        excelParam1.setSheetNo(1);
        excelParam1.setHeadLineMun(2);
        excelParam1.setSheetName("员工信息");
        excelParam1.setStartRow(0);
        MergeParameter m1 = new MergeParameter(1,2,0,0);
        List<MergeParameter> mergeParameterList1 = new ArrayList<>();
        mergeParameterList1.add(m1);

        excelParam1.setMergeParameterList(mergeParameterList1);
       /* Map<Integer,Integer> columnWidth = new HashMap<>();
        columnWidth .put(0, 2000);
        columnWidth.put(1, 3000);
        columnWidth.put(2, 4000);
        columnWidth.put(3, 3000);
        columnWidth.put(4, 6000);
        columnWidth.put(5, 4000);
        excelParam1.setColumnWidth(columnWidth);*/
        excelParameterList.add(excelParam1);

        ExcelParameter excelParam2 =  new ExcelParameter();
        excelParam2.setSheetNo(2);
        excelParam2.setSheetName("机构信息");
        excelParam2.setHeadLineMun(1);
        excelParam2.setStartRow(0);
        excelParameterList.add(excelParam2);
        return excelParameterList;
    }

    public static void main(String[] args){
        List<String> list1=new ArrayList<>();
        list1.add("a");
        list1.add("b");
        List<Integer> list2=new ArrayList<>();
        list2.add(1);
        list2.add(2);
        List<? extends List<? extends Serializable>> list = Arrays.asList(list1, list2);
    System.out.println(list.size());
    for(int i=0;i<list.size();i++){
       System.out.println(list.get(i).toString());
    }
    }




}
