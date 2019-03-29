package com.beerboard.cp.UtilityFiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;;

public class ExcelUtils {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheets;
	public static Row headerRow,row;
	public static Cell cellvalue,rowvalue;
	public Iterator<Row> rows;
	public Iterator<Cell> cells;
	
	public FileInputStream fis;
	public FileOutputStream fos;

	
	void loadExcelfile(String excelFileName, String sheetName) throws IOException
	{
		
		fis = new FileInputStream (System.getProperty("user.dir")+ "/resources/"+ excelFileName);
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		sheets = wb.getSheet(sheetName);
		 
	}
	
/*void getObj() throws IOException
{
	
	loadPropertiesFile("config.porperties");
	String filePath = propfile.getProperty("browser");
	System.out.println(filePath);
} */
	
	
public void setCellData(String TCRow,String RSColumn,String Value) throws IOException
	{
	int rowNum =0, colNum = 0, cellNum=0;
		loadExcelfile("TestCases1.xlsx","Test");
		
		//Find the TestCases ID column and Test cases row
		headerRow= sheets.getRow(0);
		cells=headerRow.cellIterator();
		rows = sheets.iterator();
		while(cells.hasNext()) 
		{ 
			cellvalue = cells.next();
		
			if(cellvalue.getStringCellValue().equalsIgnoreCase("TestCase_ID")) 
			{
				rows.next();
				cellNum= cellvalue.getColumnIndex();
				while(rows.hasNext()) 
				{
					
					row = rows.next();
					
					if(row.getCell(cellNum).getStringCellValue().equalsIgnoreCase(TCRow))
					{
						rowNum = row.getRowNum();
						
					}
					
				}
				System.out.println(rowNum);
				
			}
					
		}
	
	//Find the result column number	
	cells=headerRow.cellIterator();
		while(cells.hasNext())
		{
			cellvalue=cells.next();
			if(cellvalue.getStringCellValue().equalsIgnoreCase(RSColumn))
			{
				colNum=cellvalue.getColumnIndex();
			}
			
			
		} 
		fis.close();
		cellvalue = sheets.getRow(rowNum).getCell(colNum);
		cellvalue.setCellValue(Value);
		fos = new FileOutputStream(System.getProperty("user.dir")+ "/resources/TestCases1.xlsx");
		wb.write(fos);
		fos.close();
		System.out.println(colNum);

	}


public void getData(String colName ,int rowIndex) throws IOException
{
	int colIndex = 0;
	
	loadExcelfile("TestData1.xlsx","TestData");
	//Find a column name
	headerRow = sheets.getRow(0);
	cells= headerRow.cellIterator();
	rows= sheets.iterator();
	
	while(cells.hasNext()) {
		cellvalue = cells.next();
		if(cellvalue.getStringCellValue().equalsIgnoreCase(colName))
		{
			colIndex=cellvalue.getColumnIndex();
		}	
	}
	
	
	while(rows.hasNext())
	{
		row = rows.next();
		cells = row.cellIterator();
		while(cells.hasNext())
		{
			cellvalue = cells.next();
			if(row.getRowNum()>rowIndex)
			{
				if(cellvalue.getColumnIndex()== colIndex)
				{
				System.out.print(cellvalue.getStringCellValue() + "\n");
				}
			}
		}
	}
	
	
}


	public static void main(String[] args) throws IOException {
		
		
		ExcelUtils eu = new ExcelUtils();
		//eu.getData("Data3", 0);
		eu.setCellData("TC_001", "Result", "FAIL");
		
	}

	
}	
		
		
