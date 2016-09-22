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

			return schools;
	}
}
