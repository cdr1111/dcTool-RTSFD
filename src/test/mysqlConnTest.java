package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class mysqlConnTest {


	@Test
	public void test() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.158.209:3306/hsstorage_test?Unicode=true&amp;characterEncoding=utf8";
		String user = "hsstorage";
		String password = "26e9b91e9f34";
		
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern=null;
		String[] types = new String[]{"table"};
		
		try {
			getClass().forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			
			if(!conn.isClosed()){
				System.out.println("数据库连接成功了！");
			}else{
				return;
			}
			
			
			DatabaseMetaData dbmd = conn.getMetaData();  
	          
	        System.out.println("获取数据库产品名称 -1>"+dbmd.getDatabaseProductName());//获取数据库产品名称  
	          
	        System.out.println("获取数据库产品版本号  -1>"+dbmd.getDatabaseProductVersion());//获取数据库产品版本号  
	  
	        System.out.println("数据库用作类别和表名之间的分隔符 -1>"+dbmd.getCatalogSeparator());//获取数据库用作类别和表名之间的分隔符   如test.user  
	          
	        System.out.println("驱动版本 -1>"+dbmd.getDriverVersion());//获取驱动版本  
	          
	        System.out.println("*******************可用的数据库列表*********************"); 
	        ResultSet rs = dbmd.getCatalogs();//取可在此数据库中使用的类别名,在mysql中说白了就是可用的数据库名称，只有一列  
	        while(rs.next()){
	        	System.out.println("--2>"+rs.getString(1));
	        }
	        
	        
	        System.out.println("********************所有表********************************"); 
			rs =  dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
			while(rs.next()){
				System.out.println("---3>" +rs.getString(1)+ "," +rs.getString(2)+ "," +rs.getString(3) + "," + rs.getString(4));//打印表类别,表模式
			}
			
			System.out.println("##############################################################");
			rs = dbmd.getPrimaryKeys(null, null, "ocl_fs_product");  
	        while(rs.next()){  
	        	System.out.println(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + ","  
	                    + rs.getString(4) + "," + rs.getShort(5) + "," + rs.getString(6));  
	        }
	        
	        
	        System.out.println("#######################     表列            ########################");
	        rs = dbmd.getColumns(null, null, "ocl_fs_product", null); 
	        while(rs.next()){  
	        	System.out.println(rs.getString("COLUMN_NAME") +
	        			", 类型=" + rs.getInt("DATA_TYPE") + 
	        			", 列大小=" + rs.getInt("COLUMN_SIZE") +   
	                    ", 注释=" + rs.getString("REMARKS") +  
	                    ", 是否允许为NULL=" + rs.getInt("NULLABLE"));  
	        }
	        
	        
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
