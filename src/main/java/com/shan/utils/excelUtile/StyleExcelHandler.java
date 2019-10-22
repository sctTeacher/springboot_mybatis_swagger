package com.shan.utils.excelUtile;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

@Component
public class StyleExcelHandler implements WriteHandler {

    @Override
    public void sheet(int i, Sheet sheet) {
     
    }

    @Override
    public void row(int i, Row row) {
    }

    @Override
    public void cell(int i, Cell cell) {
     
    	Sheet sheet = cell.getSheet();
        Workbook workbook = sheet.getWorkbook();
        CellStyle cellStyle = createStyle(workbook);
        
        Font font = workbook.createFont();
        font.setFontName("黑体");  
        String sheetName = sheet.getSheetName();
        if(sheetName.equals("员工信息")) {
            //处理标题样式
        	 if(cell.getRowIndex() <= 2) {
        	     //特殊处理指定单元格样式
            	 if (i == 5) {
                     cellStyle.setFillPattern(FillPatternType.LESS_DOTS);//设置前景填充样式
                     cellStyle.setFillBackgroundColor((short)13);
                     cellStyle.setFillForegroundColor((short)13);
                 }
            	
            	 font.setFontHeightInPoints((short) 12); 
        	}else{
        	     //内容样式
        		  font.setFontHeightInPoints((short) 8); 
        	}
        }else {
        	 if(cell.getRowIndex() < 1) {
        		 font.setFontHeightInPoints((short) 12); 
        	 }else {
        		 font.setFontHeightInPoints((short) 8);
        	 }
        }
       
        cellStyle.setFont(font);
        
        cell.getRow().getCell(i).setCellStyle(cellStyle);
    }

    /**
      * 实际中如果直接获取原单元格的样式进行修改, 最后发现是改了整行的样式, 因此这里是新建一个样* 式
      */
    private CellStyle createStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        // 下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
}