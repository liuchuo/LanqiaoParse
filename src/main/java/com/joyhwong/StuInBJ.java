package com.joyhwong;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.BinJiang;

public class StuInBJ {

	
	public StuInBJ() {
		
		Map<String, BinJiang> students = new TreeMap<String, BinJiang>();
		File excelFile = new File("2016年软件类-江苏赛区获奖名单.xlsx");
		
		try {
			
			
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(excelFile));
	        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
	        
	        Iterator<Row> rows = xssfSheet.rowIterator();
	        rows.next();
	        rows.next();
	        rows.next();
	        
	        while (rows.hasNext()) {
				
	        	XSSFRow row = (XSSFRow) rows.next();
	        	Iterator<Cell> cells = row.cellIterator();
				cells.next();
				XSSFCell cellSchool = (XSSFCell) cells.next();
				if(cellSchool.getStringCellValue().equals("南京信息工程大学 滨江学院")){
					
					XSSFCell cell = (XSSFCell) cells.next();
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
	                String id = cell.getStringCellValue();
	                
	                String name = cells.next().getStringCellValue();
	                students.put(name, new BinJiang());
	            	String subject = cells.next().getStringCellValue();
	            	String group = "A";
	            	if (subject.equals("C/C++程序设计大学 A 组省赛")) {
	            		subject = "C/C++程序设计";
					}else if (subject.equals("C/C++程序设计大学 B 组省赛")) {
						group = "B";
						subject = "C/C++程序设计";
					}else if (subject.equals("JAVA 软件开发大学 B 组省赛")) {
						group = "B";
						subject = "JAVA 软件开发";
					}
	            	String score = cells.next().getStringCellValue();
	            	String isFinals = cells.next().getStringCellValue();
	            	
	            	BinJiang binJiang = students.get(name);
	            	binJiang.setId(id);
	            	binJiang.setName(name);
	            	binJiang.setSubject(subject);
	            	binJiang.setGroup(group);
	            	binJiang.setScore(score);
	            	binJiang.setFinals(isFinals);
	                
	            	students.put(name, binJiang);
				}		
			}
	        
	        printStuInBJ(students);
	        xssfWorkbook.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

    public void printStuInBJ(Map<String, BinJiang> students) {
		
		try {
			
			File file = new File("result.xlsx");
			String sheetName = "sheet2";
	        XSSFWorkbook xssfWorkBook = new XSSFWorkbook(new FileInputStream(file));
	        XSSFSheet xssfSheet = xssfWorkBook.createSheet(sheetName);
	        
	        int k = 0;
	        XSSFRow row;
	        XSSFCell cell;
	        row = xssfSheet.createRow(k++);
	        cell = row.createCell(0);
	        cell.setCellValue("学校名称");
	        cell = row.createCell(1);
	        cell.setCellValue("准考证号");
	        cell = row.createCell(2);
	        cell.setCellValue("考生姓名");
	        cell = row.createCell(3);
	        cell.setCellValue("科目名称");
	        cell = row.createCell(4);
	        cell.setCellValue("组别");
	        cell = row.createCell(5);
	        cell.setCellValue("奖项");
	        cell = row.createCell(6);
	        cell.setCellValue("是否进入决赛");
	        cell = row.createCell(7);
	        cell.setCellValue("导师");
	
	        for (String name : students.keySet()) {
				
	        	if (!name.equals("")) {
					
	        		BinJiang binJiang = students.get(name);
	        		row = xssfSheet.createRow(k++);
	        		cell = row.createCell(0);
	        		cell.setCellValue(binJiang.getSchool());
	        		cell = row.createCell(1);
	        		cell.setCellValue(binJiang.getId());
	        		cell = row.createCell(2);
	        		cell.setCellValue(name);
	        		cell = row.createCell(3);
	        		cell.setCellValue(binJiang.getSubject());
	        		cell = row.createCell(4);
	        		cell.setCellValue(binJiang.getGroup());
	        		cell = row.createCell(5);
	        		cell.setCellValue(binJiang.getScore());
	        		cell = row.createCell(6);
	        		cell.setCellValue(binJiang.isFinals());
				}
			}
	        
	        for (int i = 0; i < 8; i++) {
	            xssfSheet.autoSizeColumn(i);
	        }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkBook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            xssfWorkBook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
}
