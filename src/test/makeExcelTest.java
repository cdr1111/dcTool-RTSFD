package test;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;



public class makeExcelTest {

	@Test
	public void test() {
		try {
			buildExcelDocument();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void buildExcelDocument( ) throws Exception {
		String filename = "表单.xlsx";//
        filename = new String(filename.getBytes("GB2312"), "ISO8859-1");
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		
		HSSFSheet sheet = workbook.createSheet("表单详细");
        sheet.setColumnWidth(0, 15*256);
        sheet.setColumnWidth(1, 15*256);
        sheet.setColumnWidth(2, 15*256);
        sheet.setColumnWidth(3, 10*256);
        sheet.setColumnWidth(4, 10*256);
        sheet.setColumnWidth(5, 10*256);
        sheet.setColumnWidth(6, 10*256);
        sheet.setColumnWidth(7, 15*256);
        
        this.setMaster(sheet);
        this.setSlave(sheet, style);
        
     
        FileOutputStream fileOut = null;  
        fileOut = new FileOutputStream("d:\\workbook.xls");  
        
        workbook.write(fileOut);
        workbook.close();
        
        fileOut.flush();
        fileOut.close();
	}
	
	private void setMaster(HSSFSheet sheet) {
		HSSFRow row1 = sheet.createRow(0);
		HSSFRow row2 = sheet.createRow(1);
		
		HSSFCell cell11 = row1.createCell(0);
		cell11.setCellValue("时间："+ new Date());
		//合并第一行1，2列
		CellRangeAddress region11 = new CellRangeAddress(0, 0, 0, 1);
		sheet.addMergedRegion(region11);
		
		HSSFCell cell12 = row1.createCell(2);
		cell12.setCellValue("仓库："+"Test 仓库");
		
		//合并第一行3，4列
		CellRangeAddress region12 = new CellRangeAddress(0, 0, 2, 4);
		sheet.addMergedRegion(region12);
		
		HSSFCell cell13 = row1.createCell(5);
		cell13.setCellValue("操作员："+"Test 操作员");

		//合并第一行5，6列
		CellRangeAddress region13 = new CellRangeAddress(0, 0, 5, 7);
		sheet.addMergedRegion(region13);
		
		HSSFCell cell21 = row2.createCell(0);
		cell21.setCellValue("单据号："+"Test 单据号");
		//合并第二行1，2列
		CellRangeAddress region21 = new CellRangeAddress(1, 1, 0, 1);
		sheet.addMergedRegion(region21);
		
		HSSFCell cell22 = row2.createCell(2);
		//cell22.setCellValue("领用人："+outOperationReoprt.getUseStaff().getName());
		//合并第二行1，2列
		CellRangeAddress region22 = new CellRangeAddress(1, 1, 2, 4);
		sheet.addMergedRegion(region22);
		
	}
	
	private void setSlave(HSSFSheet sheet, HSSFCellStyle style) {
		String[] headers = {"物品编码", "物品名称", "数量", "单价（含税）", "总额（含税）", 
				             "税率", "单价（不含税）", "总额（不含税）","备注"};
		
		List<Date> list = Arrays.asList(new Date(0), new Date(1),new Date(2));
		int size = list.size();
		HSSFRow row4 = sheet.createRow(3);
		for(int i=0; i<headers.length; i++) {
			HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
		}
		for(int j=0; j<size; j++) {
			HSSFRow rowj = sheet.createRow(j+4);
			Date outReportItem = list.get(j);
			
//			Product product = outReportItem.getProduct();

			HSSFCell cell1 = rowj.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue("0");
			
			HSSFCell cell2 = rowj.createCell(1);
			cell2.setCellStyle(style);
			cell2.setCellValue("1");
			
			HSSFCell cell3 = rowj.createCell(2);
			cell3.setCellStyle(style);
			cell3.setCellValue("2");
			
			HSSFCell cell4 = rowj.createCell(3);
			cell4.setCellStyle(style);
			cell4.setCellValue("3");
			
			HSSFCell cell5 = rowj.createCell(4);
			cell5.setCellStyle(style);
			cell5.setCellValue("4");
			
			HSSFCell cell6 = rowj.createCell(5);
			cell6.setCellStyle(style);
			cell6.setCellValue("5");
			
			HSSFCell cell7 = rowj.createCell(6);
			cell7.setCellStyle(style);
			cell7.setCellValue("6");
			
			HSSFCell cell8 = rowj.createCell(7);
			cell8.setCellStyle(style);
			cell8.setCellValue("7");
			
			HSSFCell cell9 = rowj.createCell(8);
			cell9.setCellStyle(style);
			cell9.setCellValue("8");
		}
	}

}
