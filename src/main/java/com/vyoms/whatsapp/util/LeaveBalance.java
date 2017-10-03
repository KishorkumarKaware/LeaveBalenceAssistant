package com.vyoms.whatsapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ch.qos.logback.core.net.SyslogOutputStream;

public class LeaveBalance {
public static String getLeave(String EmpId,String leaveType) throws IOException
{
	/*Pattern empidRegexPattern = Pattern.compile("[0-9]{4}");
	// agent_Im_Id.matcher(msg);
	Matcher empidRegex = empidRegexPattern.matcher(EmpId); 
	
	if(!empidRegex.matches())
	{
		return "Employee id is invalid.";
	}*/
	
	File file=new File("D:\\ExcelFIles\\leaveData.xls");
	FileInputStream fin = new FileInputStream(file);
	String result="";
	HSSFWorkbook wb = new HSSFWorkbook(fin);
	HSSFSheet ws = wb.getSheetAt(0);
	HSSFCell cell=null;	
	HSSFRow rowHeader = ws.getRow(0);

	int rowNum = ws.getLastRowNum() + 1;
	int colNum = ws.getRow(0).getLastCellNum();
	int empIdIndexHeader=0;
    String clFromExcel;
    String plFromExcel;
    String slFromExcel;
    String totalFromExcel;
    int indexType=0;
	 System.out.println("Total Number of Columns in the excel is : "+colNum);
	 System.out.println("Total Number of Rows in the excel is : "+rowNum);

	
       for (int i = 1; i <rowNum; i++)
        {
           rowHeader = ws.getRow(i);
           String empidFromExcel  = cellToString(rowHeader.getCell(empIdIndexHeader));
    
           System.out.println("EmpiIDExcel="+empidFromExcel);
         	if(EmpId.equalsIgnoreCase(empidFromExcel) )
			{
         		if(leaveType.equalsIgnoreCase("Casual Leave"))
         		{
         			indexType=2;
         			clFromExcel = rowHeader.getCell(indexType).toString();
         			System.out.println("CL value :"+clFromExcel);
         			return clFromExcel;
         		}
         		else if (leaveType.equalsIgnoreCase("Paid Leave"))
         		{
         			indexType=3;
         			slFromExcel = rowHeader.getCell(indexType).toString();
         			System.out.println("PL value :"+slFromExcel);
         			return slFromExcel;
				}
         		else if (leaveType.equalsIgnoreCase("Sick Leave"))
         		{
         			indexType=4;
         			plFromExcel = rowHeader.getCell(indexType).toString();
         			System.out.println("SL value :"+plFromExcel);
         			return plFromExcel;
				}
         		else if (leaveType.equalsIgnoreCase("Total"))
         		{
         			indexType=5;
         			totalFromExcel=rowHeader.getCell(indexType).toString();
         			System.out.println("Total leave value :"+totalFromExcel);
         			return totalFromExcel;
				}
				
              }else if(i == rowNum-1)
              {
            	  result="Sorry! Entered Employee ID is Incorrect.";
              }
         	
         	
        }
       System.out.println("Leave is="+result);
       
	return result;
}
	
			private static String cellToString(HSSFCell cell) 
			 {	
					Object result = null;
					switch (cell.getCellType()) 
					{
					case HSSFCell.CELL_TYPE_NUMERIC:
						result = Integer.valueOf( (int) cell.getNumericCellValue()).toString();
						break;
				
					case HSSFCell.CELL_TYPE_STRING:
						result = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						result = "";
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						result = cell.getCellFormula();
					}
					return result.toString();
			}	
			
			public static void main(String[] args) throws IOException
			{
				String result=LeaveBalance.getLeave("AE6789","Sick Leave");
				System.out.println("Result:"+result+" Days.");
			}
	}


