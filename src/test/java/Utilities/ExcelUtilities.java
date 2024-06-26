package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public static FileInputStream fIn;
	public static XSSFWorkbook outBook;
	public static XSSFSheet outSheet;
	public static XSSFRow outRow;
	public static XSSFCell outCell;
	
	public void createExcelFile() throws IOException
	{
		String file = System.getProperty("user.dir") + "\\excel\\excelOutputFile.xlsx";
		FileOutputStream fOut = new FileOutputStream(file);
		XSSFWorkbook excelBook = new XSSFWorkbook();
		
		XSSFSheet sheet1 = excelBook.createSheet("user Profile");
		XSSFSheet sheet2 = excelBook.createSheet("IT News");
//		XSSFSheet sheet3 = excelBook.createSheet("IT Awards");
		
		XSSFRow sheet1HeadRow = sheet1.createRow(0);
		XSSFRow sheet2HeadRow = sheet2.createRow(0);
		
		
		
		XSSFCell nameCell = sheet1HeadRow.createCell(0);
		nameCell.setCellValue("Name");
		
		XSSFCell mailCell = sheet1HeadRow.createCell(1);
		mailCell.setCellValue("Mail");
		
//		XSSFCell newsTitleCell = sheet2HeadRow.createCell(0);
//		newsTitleCell.setCellValue("News title");
//		
//		XSSFCell newsTooltipCell = sheet2HeadRow.createCell(1);
//		newsTooltipCell.setCellValue("News Tooltip");
		
		XSSFCell newsExpectedCell = sheet2HeadRow.createCell(0);
		newsExpectedCell.setCellValue("Expected Result");
		
		XSSFCell newsActualCell = sheet2HeadRow.createCell(1);
		newsActualCell.setCellValue("Actual Result");
		
		XSSFCell newsResultCell = sheet2HeadRow.createCell(2);
		newsResultCell.setCellValue("Test Result");
		
//		XSSFCell awardTitleCell = sheet3HeadRow.createCell(0);
//		awardTitleCell.setCellValue("Award Title");
//		
//		XSSFCell awardDescripCell = sheet3HeadRow.createCell(1);
//		awardDescripCell.setCellValue("Award Description");
		
		excelBook.write(fOut);
		excelBook.close();
		fOut.close();
	}
	
	public static void updateExcel(String data, String sheetName, int rowNum, int colNum) throws IOException
	{
		String file = System.getProperty("user.dir") + "\\excel\\excelOutputFile.xlsx";
		fIn = new FileInputStream(file);
		outBook = new XSSFWorkbook(fIn);
		outSheet = outBook.getSheet(sheetName);
		
		outRow = outSheet.getRow(rowNum);
		if(outRow == null)
		{
			outRow = outSheet.createRow(rowNum);
		}
		outCell = outRow.createCell(colNum);
		outCell.setCellValue(data);
		
		FileOutputStream excelOut = new FileOutputStream(file);
		outBook.write(excelOut);
		
		outBook.close();
		fIn.close();
		excelOut.close();
		
	}
}
