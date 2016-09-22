package com.joyhwong;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AllSchool {

	private SortedSet<String> schools = new TreeSet<String>();

	private File excelFile;

	public AllSchool() {
		excelFile = new File("/WEB-INF", "2016年软件类-江苏赛区获奖名单.xlsx");
	}

	public SortedSet<String> getAllSchools() {

		 XSSFWorkbook xssfWorkbook = null;
		
		try {
            
            xssfWorkbook = new XSSFWorkbook(new FileInputStream(excelFile));           
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);           
            
            Iterator<Row> rows = xssfSheet.rowIterator();      
            rows.next();rows.next();rows.next();
            
            while (rows.hasNext()) {
                
                XSSFRow row = (XSSFRow) rows.next();
                XSSFCell cell = row.getCell(1);
                
                if (!schools.contains(cell.getStringCellValue())) {
                	schools.add(cell.getStringCellValue());
                }
                
            }
            
            
		} catch (IOException e) {
            
			e.printStackTrace();
        } finally {
        	
        	try {
        		xssfWorkbook.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return schools;
	}
}
