package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.Color;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static FileInputStream fio;
	
	static FileOutputStream fop;
	//new FileInputStream(System.getProperty("user.dir")+"\\testdata\\inputdata.xlsx");
	
	static XSSFWorkbook wb;
	//new XSSFWorkbook(fio);
	
	static XSSFSheet sh;
	
	static XSSFRow rw;
	
	static XSSFCell c;
	static CellStyle cstyle;
	
	
	public static String getInput(int row, int cell, String path) throws IOException {
		fio = new FileInputStream(System.getProperty("user.dir")+path);
		wb = new XSSFWorkbook(fio);
		sh = wb.getSheetAt(0);
		return sh.getRow(row).getCell(cell).toString();
	}
	
	public static void setValue(int row, int cell, String value, String path) throws IOException {
		fio = new FileInputStream(System.getProperty("user.dir")+path);
		wb = new XSSFWorkbook(fio);
		sh = wb.getSheetAt(1);
		rw = sh.createRow(row);
		c = rw.createCell(cell);
		c.setCellValue(value);
		fop = new FileOutputStream(System.getProperty("user.dir")+path);
		wb.write(fop);
	}
	
	public static void fillRedColor(int row, int cell, String path) throws Exception {
		fio = new FileInputStream(System.getProperty("user.dir")+path);
		wb = new XSSFWorkbook(fio);
		sh = wb.getSheetAt(1);
		rw = sh.getRow(row);
		c = rw.getCell(cell);
		cstyle = wb.createCellStyle();
		
		cstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		cstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		c.setCellStyle(cstyle);
		fop = new FileOutputStream(System.getProperty("user.dir")+path);
		wb.write(fop);
		wb.close();
		fio.close();
		fop.close();
		
	}
	
	public static void fillGreenColor(int row, int cell, String path) throws Exception {
		fio = new FileInputStream(System.getProperty("user.dir")+path);
		wb = new XSSFWorkbook(fio);
		sh = wb.getSheetAt(1);
		rw = sh.getRow(row);
		c = rw.getCell(cell);
		cstyle = wb.createCellStyle();
		
		cstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		c.setCellStyle(cstyle);
		fop = new FileOutputStream(System.getProperty("user.dir")+path);
		wb.write(fop);
		wb.close();
		fio.close();
		fop.close();
		
	}
	
	
	
}
