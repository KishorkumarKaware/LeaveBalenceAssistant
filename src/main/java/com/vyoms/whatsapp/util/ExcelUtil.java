package com.vyoms.whatsapp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static void main(String args[]) {
		File folder = new File(Constants.downloadTempFilepath + "\\HDFC\\48941056");
		String tmp=Constants.downloadFilepath + "\\HDFC\\48941056";
		
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles[0].isFile()) {
			ExcelUtil excelUtil = new ExcelUtil();
			excelUtil.removeFile(listOfFiles[0],tmp, "HDFC");
		}
	}

	private Sheet sheet;

	public String getCellValue(Row row, int index) {
		if (row.getCell(index) != null) {
			row.getCell(index).setCellType(Cell.CELL_TYPE_STRING);
			return row.getCell(index).getStringCellValue();
		} else {
			return "";
		}
	}

	private String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public int getRowNumber(Sheet sheet, String delimeter) {
		int rowNumber = 0;
		for (Row r : sheet) {
			if (r.getRowNum() != 0) {
				String value = getCellValue(r, 0);
				if (value.contains(delimeter)) {
					return rowNumber = r.getRowNum();
				}
			}
		}
		return rowNumber;
	}

	public void removeFile(File file, String outFilePath, String bank) {
		try {
			Workbook wb = WorkbookFactory.create(file);
			sheet = wb.getSheetAt(0);
		
			if (bank.equals("HDFC")) {
				int headerRowNumber = getRowNumber(sheet, "**");
				int footerRowNumber = getRowNumber(sheet, "STATEMENT SUMMARY");

				removeFooter(footerRowNumber - 1, sheet.getLastRowNum() + 1);
				removeHeader(headerRowNumber + 1);
			}
			if (bank.equals("AXIS")) {
				int headerRowNumber = getRowNumber(sheet, "$*");
				int footerRowNumber = getRowNumber(sheet, "STATEMENT SUMMARY");

				removeFooter(footerRowNumber - 1, sheet.getLastRowNum() + 1);
				removeHeader(headerRowNumber + 1);
			}
			File dir = new File(outFilePath);
			File tmp = new File(dir,
					new StringBuffer().append(file.getName()).append(".").append(getFileExtension(file)).toString());

			FileOutputStream outFile = new FileOutputStream(tmp);
			wb.write(outFile);
			outFile.close();			
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeFooter(int rowNumber, int lastRowNuber) {
		for (int i = rowNumber; i < lastRowNuber; i++) {
			removeRow(rowNumber);
		}
	}

	public void removeHeader(int rowNumber) {
		for (int i = 0; i < rowNumber; i++) {
			removeRow(0);
		}
	}

	public void removeRow(int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
	}
}
