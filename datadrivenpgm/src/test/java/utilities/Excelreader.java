package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Excelreader {
	
	XSSFWorkbook workbook;
	XSSFSheet worksheet;
	XSSFRow XSSFRow;
	DataFormatter formatter;
	
	
	@DataProvider(name="myownfile")
	public Object[][] getdummy(){
		//rows,no.of times,,2(0,1)..1(0)
		Object[][] ob= new Object[2][1];
		ob[0][0]="testone";
		ob[1][0]="testtwo";
		
		return ob;
	}
	
	
	
	@DataProvider(name="readexcel")
	public Object[][]  getdata() throws IOException{
		
		//String methodname=m.getName();
		FileInputStream fileInputStream= new FileInputStream(new File("D:\\Nimishafolder\\testam\\Datadrivensheet.xlsx")); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
        worksheet=workbook.getSheetAt(0);// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
     
        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum 
        Object Data[][]= new Object[RowNum-1][1]; // pass my  count data in array
         
        
            for(int i=0; i<RowNum-1; i++) //Loop work for Rows
            {  
            	Map<Object, Object> datamap = new HashMap<>();
                XSSFRow row= worksheet.getRow(i);
                 
                for (int j=0; j<ColNum; j++) //Loop work for colNum
                {
                   
                        XSSFCell cell= row.getCell(j);
                      
                            //String value=formatter.formatCellValue(cell);
                            String value=cell.getStringCellValue();         
                           //  Data[i-1][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                           
	                            datamap.put(worksheet.getRow(0).getCell(j).toString(), worksheet.getRow(i+1).getCell(j).toString()); 
                      
                }
                Data[i][0] = datamap;//how many times so i alone enough 1 column inside row its keyvalues(means 1 indx)
            }
 
        return Data;

		
		
		
		
		
	}

}
