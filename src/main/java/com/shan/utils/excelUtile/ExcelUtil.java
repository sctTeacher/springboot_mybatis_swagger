package com.shan.utils.excelUtile;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtil {

	private static StyleExcelHandler styleExcelHandler;
 
	@Autowired
    public void setStyleExcelHandler(StyleExcelHandler styleExcelHandler){
		ExcelUtil.styleExcelHandler = styleExcelHandler;
    }
	
	  /**
	   * @param inputStream 输入流
	   * @param sheetNo     工作表编号
	   * @param headLineMun 标题占用行数
	   */
	public static List<Object> readJavaModel(InputStream inputStream, ExcelParameter excelParam, Class<? extends BaseRowModel> clazz) {
	      List<Object> data = null;
	      try {
	          data = EasyExcelFactory.read(inputStream, new Sheet(excelParam.getSheetNo(), excelParam.getHeadLineMun(), clazz));
	          inputStream.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } finally {
	          try {
	              inputStream.close();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }
	      
	      return data;
	  }

	/**
	 * 数据写入excel
	 * @param outputStream   输出流
	 * @param excelParameterList  excel相关参数
	 * @param employeeDateList   数据源
	 */
	public static void writeExcel(OutputStream outputStream, List<ExcelParameter> excelParameterList,
			List<List<? extends BaseRowModel>> employeeDateList) {
	 
		
		 try {         
			 ExcelWriter writer = new ExcelWriter(null, outputStream, ExcelTypeEnum.XLSX, true, styleExcelHandler);
			  
			// ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
			 
			 for (int j = 0; j < excelParameterList.size(); j++) {
				 ExcelParameter parameter = excelParameterList.get(j);
				 List<? extends BaseRowModel> data = employeeDateList.get(j);
				 Class<? extends BaseRowModel> t;
	 	         if (data != null && data.size() > 0) {
	 	            t = data.get(0).getClass();
	 	         } else {
	 	           continue;
	 	         }
	 	         
	 	        Sheet sheet = new Sheet(parameter.sheetNo, parameter.headLineMun, t, parameter.sheetName, null);
	 	        sheet.setStartRow(parameter.getStartRow());
	 	         
	 	        //设置列宽 设置每列的宽度
	 	        if (parameter.columnWidth != null && parameter.columnWidth.size() > 0) {
	 	            sheet.setColumnWidthMap(parameter.columnWidth);
	 	            
	 	        } else {
	 	            // 设置自适应宽度
	 	            sheet.setAutoWidth(Boolean.TRUE);
	 	        }
	 	       
	 	        writer.write(data, sheet);
	 	        //合并单元格
	 	        if (parameter.getMergeParameterList() != null && parameter.getMergeParameterList().size() > 0) {
	 	            for (MergeParameter mergeParameter : parameter.getMergeParameterList()) {
	 	                writer.merge(mergeParameter.firstRow, mergeParameter.lastRow, mergeParameter.firstCol, mergeParameter.lastCol);
	 	            }
	 	        }
	 	       
			}
			 writer.finish();
		 } catch (RuntimeException e) {
	 	        e.printStackTrace();
	 	    } finally {
	 	        try {
	 	            outputStream.close();
	 	        } catch (IOException e) {
	 	            e.printStackTrace();
	 	        }
	 	    }
	}


	/**
	 * excel相关参数实体
	 */
	@Data
    public static class ExcelParameter {
        /**
         * 工作表编号
         */
        private Integer sheetNo;
        /**
         * 标题占用行数
         */
        private Integer headLineMun;
        /**
         * 工作表名称
         */
        private String sheetName;
        /**
         * 表头
         */
        private List<List<String>> listStringHead;
        /**
         * 宽度
         */
        private Map<Integer, Integer> columnWidth;
        /**
         * 开始行号
         */
        private Integer startRow;
        
        /**
         * 合并单元格参数
         */
        private List<MergeParameter> mergeParameterList;
    }

    /**
     * 单元格合并参数
     */
    @Data
    @AllArgsConstructor
	public  static class MergeParameter {
        /**
         * 起始行
         */
        private Integer firstRow;
        /**
         * 结束行
         */
        private Integer lastRow;
        /**
         * 起始列
         */
        private Integer firstCol;
        /**
         * 结束列
         */
        private Integer lastCol;
    }
}
