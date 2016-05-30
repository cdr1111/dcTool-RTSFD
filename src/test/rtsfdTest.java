package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import dc.rtsfd.DBTable;
import dc.rtsfd.DBTableColumn;

public class rtsfdTest {

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://192.168.158.209:3306/hsstorage_test?Unicode=true&amp;characterEncoding=utf8";
	String user = "hsstorage";
	String password = "26e9b91e9f34";
	
	String catalog = null;
	String schemaPattern = null;
	String tableNamePattern=null;
	String[] types = new String[]{"table"};
	
	ResultSet rs;
	
	String filename = "";
   
	DBTable dbt = null;
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void rtsfdTest(){
		
		 
		
		try {
			 filename = new String(filename.getBytes("GB2312"), "ISO8859-1");
			 
			 
			getClass().forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			
			DatabaseMetaData dbmd = conn.getMetaData();  
			
			HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFCellStyle style = workbook.createCellStyle();
			style.setWrapText(true);
			
			HSSFSheet sheet = null;
			
			
			
			rs =  dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
			while(rs.next()){
				System.out.println("--->表名:" +rs.getString(3));//打印表类别,表模式
				dbt = new DBTable();
				dbt.setName(rs.getString(3));
				
				sheet = workbook.createSheet(dbt.getName());
				
				ArrayList<DBTableColumn> col = new ArrayList<DBTableColumn>();
				
				dbt.setDbtColumn(col);
			        
				DBTableColumn  dbtc = null;
				ResultSet inRst = dbmd.getColumns(null, null, rs.getString(3), null);
				while(inRst.next()){  
		        	System.out.println(inRst.getString("COLUMN_NAME") +
		        			", 类型=" + inRst.getInt("DATA_TYPE") + 
		        			", 列大小=" + inRst.getInt("COLUMN_SIZE") +   
		                    ", 注释=" + inRst.getString("REMARKS") +  
		                    ", 是否允许为NULL=" + inRst.getInt("NULLABLE"));  
		        	
		        	dbtc = new DBTableColumn();
		        	dbtc.setName(inRst.getString("COLUMN_NAME"));
		        	dbtc.setType(""+inRst.getInt("DATA_TYPE"));
		        	dbtc.setColumnSize(""+inRst.getInt("COLUMN_SIZE"));
		        	dbtc.setMemo(inRst.getString("REMARKS"));
		        	dbtc.setIsNull(inRst.getInt("NULLABLE") == 0?"否":"是");
		        	
		        	dbt.getDbtColumn().add(dbtc);
		        }
				
				setMaster(sheet);
			    setSlave(sheet, style);
			}
			
			FileOutputStream fileOut = null;  
	        fileOut = new FileOutputStream("e:\\仓储数据库表.xls");  
	        
	        workbook.write(fileOut);
	        workbook.close();
	        
	        fileOut.flush();
	        fileOut.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private void setMaster(HSSFSheet sheet) {
	}
	
	private void setSlave(HSSFSheet sheet, HSSFCellStyle style) {
		String[] headers = {"列名", "类型", "大小","是否为空", "描述", };
		
		ArrayList<DBTableColumn> list = dbt.getDbtColumn();
		int size = list.size();
		HSSFRow row4 = sheet.createRow(3);
		for(int i=0; i<headers.length; i++) {
			HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
		}
		for(int j=0; j<size; j++) {
			HSSFRow rowj = sheet.createRow(j+4);
			DBTableColumn outReportItem = list.get(j);
			
//			Product product = outReportItem.getProduct();

			HSSFCell cell1 = rowj.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue(outReportItem.getName());
			
			HSSFCell cell2 = rowj.createCell(1);
			cell2.setCellStyle(style);
			cell2.setCellValue(outReportItem.getType());
			
			HSSFCell cell3 = rowj.createCell(2);
			cell3.setCellStyle(style);
			cell3.setCellValue(outReportItem.getColumnSize());
			
			HSSFCell cell4 = rowj.createCell(3);
			cell4.setCellStyle(style);
			cell4.setCellValue(outReportItem.getIsNull());
			
			HSSFCell cell5 = rowj.createCell(4);
			cell5.setCellStyle(style);
			cell5.setCellValue(outReportItem.getMemo());
		}
	}

}
