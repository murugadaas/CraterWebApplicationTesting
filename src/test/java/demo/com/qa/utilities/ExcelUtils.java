package demo.com.qa.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import demo.com.qa.testcases.CraterAppTest;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell ECell;
	private static XSSFRow ERow;
	private static FileInputStream ExcelFile;

	//This method is to write in the Excel cell, Row num and Col num are the parameters
	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{
		try{
			ExcelFile = new FileInputStream(CraterAppTest.resultPath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Results");
			ERow  = ExcelWSheet.getRow(RowNum);

			ECell = ERow.getCell(ColNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (ECell == null) {
				ECell = ERow.createCell(ColNum);
				ECell.setCellValue(Result);
			} else {
				ECell.setCellValue(Result);
			}

			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(CraterAppTest.resultPath);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		}catch(Exception e){
			throw (e);
		}

	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(String SheetName,int RowNum, int ColNum) throws Exception{
		try{
			ExcelFile = new FileInputStream(CraterAppTest.dataPath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			ECell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			ECell.setCellType(CellType.STRING);
			return ECell.getStringCellValue();
			/*if(ECell.getCellType().toString().equalsIgnoreCase("STRING"))
				return ECell.getStringCellValue();
			else
			{
				System.out.println(ECell.getCellType().toString());
				ECell.setCellType(CellType.STRING);
				System.out.println(ECell.getCellType().toString());
				
				return String.valueOf(ECell.getNumericCellValue());
			}*/
				
		}catch (Exception e){
			throw (e);
		}

	}

}
